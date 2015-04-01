package bluescreen1.ieeeuwisb.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import bluescreen1.ieeeuwisb.MainActivity;
import bluescreen1.ieeeuwisb.R;

public class Feed_Fragment extends Fragment implements AdapterView.OnItemSelectedListener{
    private ListView feedView;
    private int option;
    private String filter;
    private ArrayList<ParseObject> feedItems =  new ArrayList<>();

    private void setFilter(int option, String filter){
        this.option = option;
        this.filter = filter;
    }
    private void setData(List<ParseObject> hi){
        for(ParseObject x: hi){

            feedItems.add(x);
        }
        feedView.setAdapter(new FeedAdapter(getActivity(), R.layout.feed_item, feedItems ));

    }

    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Feed_Fragment newInstance(int sectionNumber) {
        Feed_Fragment fragment = new Feed_Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    public Feed_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.feed_layout, container, false);
        Spinner spinner = (Spinner) rootView.findViewById(R.id.feed_spinner);

        feedView = (ListView) rootView.findViewById(R.id.feed_list_view);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.feed_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
/* Apply the adapter to the spinner */
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Messages");
        switch(option){
            case 0:
                break;
            default:
                query.whereEqualTo("group", filter);
                break;

        }
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> fList, ParseException e) {
                if (e == null) {
                    //Log.d("score", "Retrieved " + fList.size() + " scores");
                    setData(fList);
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });


        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(),""+parent.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();
        setFilter(position,parent.getItemAtPosition(position).toString() );
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private class FeedAdapter extends ArrayAdapter<ParseObject> {
        private Context con;

        private ArrayList<ParseObject> values;
        private LayoutInflater inflater;

       public FeedAdapter(Context context, int res, ArrayList<ParseObject> values){
           super(context, res, values);

           this.con = context;
           this.values = values;
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
               vi = inflater.inflate(R.layout.feed_item, null);
           }


            TextView title = (TextView) vi.findViewById(R.id.feed_item_title);

            title.setText(getItem(position).get("title").toString());

            TextView desc = (TextView) vi.findViewById(R.id.feed_item_desc);
            desc.setText(getItem(position).get("content").toString());

            return vi;
        }
   }

}
