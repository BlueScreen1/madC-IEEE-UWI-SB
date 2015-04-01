package bluescreen1.ieeeuwisb.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import bluescreen1.ieeeuwisb.MainActivity;
import bluescreen1.ieeeuwisb.R;

/**
 * Created by Jus on 3/24/2015.
 */
public class Meetings_Fragment  extends Fragment {

    CountDownTimer counter;
    ListView pastMeetings;
    TextView upcomingMeetings;
    TextView upcoming_meetings_details;
    TextView timer;
    ArrayList<ParseObject> datesPast = new ArrayList<>();

    private static final String ARG_SECTION_NUMBER = "section_number";

    private void get_data(List<ParseObject> parseList){

        for(ParseObject p: parseList){

            datesPast.add(p);
        }

        pastMeetings.setAdapter(new PastListAdapter(getActivity(), android.R.layout.simple_list_item_1, datesPast));

    }


    public static Meetings_Fragment newInstance(int sectionNumber) {
        Meetings_Fragment fragment = new Meetings_Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    public Meetings_Fragment(){

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.meetings_layout, container, false);
        upcomingMeetings = (TextView) rootView.findViewById(R.id.upmeeting);
        pastMeetings = (ListView) rootView.findViewById(R.id.pastmeetings_list);
        upcoming_meetings_details = (TextView) rootView.findViewById(R.id.upMeetings_details);
        final int[] hours = new int[1];
        final int[] minutes = new int[1];
        final int[] seconds = new int[1];
        final int[] days = new int[1];
        timer = (TextView) rootView.findViewById(R.id.timer);
        ParseQuery query = new ParseQuery("Meetings");
        ParseQuery query2 = new ParseQuery("Meetings");
        query2.whereGreaterThan("date", new Date());

        query.whereLessThan("date", new Date());
        query.addDescendingOrder("date");
        query2.setLimit(1);

        query.findInBackground(new FindCallback() {
            @Override
            public void done(List list, ParseException e) {
                get_data(list);
            }
        });
        query2.findInBackground(new FindCallback() {
            @Override
            public void done(List list, ParseException e) {
                for (final ParseObject p : (List<ParseObject>)list) {
                    //upcomingMeetings.setText(p.getString("topic") +" at " + p.getString("location") + " on " + (p.getDate("date")));
                                        //p.getDate("date").getTime(),
                    upcomingMeetings.setText(p.getString("topic"));
                    upcoming_meetings_details.setText(p.getString("location"));
                    Date now = new Date();
                    counter = new CountDownTimer(p.getDate("date").getTime()-now.getTime(), 1000) {
                        @Override
                        public void onTick(long millsUntilFinished) {

                            seconds[0] =(int) millsUntilFinished/1000;
                            minutes[0] = seconds[0] /60;
                            seconds[0] %= 60;
                            hours[0] = minutes[0]/60;
                            minutes[0] %= 60;
                            days[0] = hours[0]/24;
                            hours[0] %= 24;

                            Formatter formatter = new Formatter();
                            timer.setText(formatter.format("%02d:%02d:%02d:%02d", days[0], hours[0], minutes[0], seconds[0]).toString());
                        }

                        @Override
                        public void onFinish() {
                            timer.setText("Happening now");
                        }
                    };
                    counter.start();
                }
            }

        });
       /* query2.findInBackground(new FindCallback() {
            @Override
            public void done(List list, ParseException e) {
                get_data2(list);
            }
        });*/
        return rootView;

    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }


    private class PastListAdapter extends ArrayAdapter<ParseObject> {
        private Context con;

        private ArrayList<ParseObject> values;
        private LayoutInflater inflater;

        public PastListAdapter(Context context, int resource, ArrayList<ParseObject> objects) {
            super(context, resource, objects);
            this.con = context;
            this.values = objects;
            this.inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        }


        @Override
        public int getCount() {
            return values.size();
        }

        @Override
        public ParseObject getItem(int position) {
            return values.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View vi = convertView;
            if(convertView == null){
                vi = inflater.inflate(R.layout.past_meetings_listitem, null);
            }


            TextView title = (TextView) vi.findViewById(R.id.pastmeetings_item_topic);

            title.setText(getItem(position).get("topic").toString());

            TextView desc = (TextView) vi.findViewById(R.id.pastmeetings_item_date);
            Formatter formatter = new Formatter();
            desc.setText(formatter.format("%tF",getItem(position).getDate("date")).toString());

            return vi;
        }

    }
}
