package bluescreen1.ieeeuwisb.Fragments;

import android.app.Activity;
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
    TextView timer;
    ArrayList<String> datesPast = new ArrayList<>();
    //ArrayList<String> datesUpc = new ArrayList<>();

    private static final String ARG_SECTION_NUMBER = "section_number";

    private void get_data(List<ParseObject> parseList){

        for(ParseObject p: parseList){

            datesPast.add(p.getString("topic"));
            datesPast.add(p.getDate("date").toString());
        }

        pastMeetings.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, datesPast));

    }

/*    private void get_data2(List<ParseObject> parseList){

//        for(ParseObject p: parseList){

            upcomingMeetings.setText(query);
            datesUpc.add(p.getString("topic"));
            datesUpc.add(p.getDate("date").toString());
            counter = new CountDownTimer(p.getDate("date").getTime(),1000){
                @Override
                public void onTick(long millsUntilFinished){
                    timer.setText((millsUntilFinished/(1000*60*60)%24) + " hours remaining");
                }
                @Override
                public void onFinish(){
                    timer.setText("Happening now");
                }
            };
            counter.start();

        }*/
     //   upcomingMeetings.setAdapter(new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,datesUpc));
   // }

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
        final int[] hours = new int[1];
        final int[] minutes = new int[1];
        final int[] seconds = new int[1];
        timer = (TextView) rootView.findViewById(R.id.timer);
        ParseQuery query = new ParseQuery("Meetings");
        ParseQuery query2 = new ParseQuery("Meetings");
        query2.whereGreaterThan("date", new Date());
        query.whereLessThan("date", new Date());
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
                for (ParseObject p : (List<ParseObject>)list) {
                    upcomingMeetings.setText(p.getString("topic") +" at " + p.getString("location") + " on " + p.getDate("date").toString());

                    counter = new CountDownTimer(p.getDate("date").getTime(), 1000) {
                        @Override
                        public void onTick(long millsUntilFinished) {
                            seconds[0] =(int) millsUntilFinished/1000;
                            minutes[0] = seconds[0] /60;
                            seconds[0] %= 60;
                            hours[0] = minutes[0]/60;
                            minutes[0] %= 60;
                            hours[0] %= 24;

                            timer.setText(hours[0] + " hours, " + minutes[0] + " minutes, " +seconds[0] + " seconds");
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
}
