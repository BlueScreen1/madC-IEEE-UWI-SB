package bluescreen1.ieeeuwisb.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import bluescreen1.ieeeuwisb.MainActivity;
import bluescreen1.ieeeuwisb.R;

public class Feed_Fragment extends Fragment implements AdapterView.OnItemSelectedListener{

    private ListView feedView;
    private int option = 0;
    private String filter;
    ArrayList<String> groups = new ArrayList<>();
    private ArrayList<ParseObject> feedItems =  new ArrayList<>();
    private final String FEED_LABEL = "feed";
    private FeedAdapter feedAdapter;
    private static final String ARG_SECTION_NUMBER = "section_number";

    private void setFilter(int option, String filter){
        this.option = option;
        this.filter = filter;
    }
    private void setData(final List<ParseObject> hi){
        feedItems.clear();
        for(ParseObject x: hi){
            feedItems.add(x);
        }
        ParseObject.unpinAllInBackground(FEED_LABEL, hi, new DeleteCallback() {
            public void done(ParseException e) {
                if (e != null) {
                    // There was some error.
                    return;
                }
                // Add the latest results for this query to the cache.
                ParseObject.pinAllInBackground(FEED_LABEL, hi);
            }
        });
        feedAdapter.notifyDataSetChanged();
        feedView.setAdapter(feedAdapter);
    }
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.feed_layout, container, false);
        Spinner spinner = (Spinner) rootView.findViewById(R.id.feed_spinner);
        feedView = (ListView) rootView.findViewById(R.id.feed_list_view);
        feedAdapter = new FeedAdapter(getActivity(), R.layout.feed_item, feedItems );
        feedView.setAdapter(feedAdapter);
        ParseUser user = ParseUser.getCurrentUser();
        try {
            for(Object x: user.getList("Groups")){

                groups.add((String)x);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item, groups);
// Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

/* Apply the adapter to the spinner */
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
        }catch(NullPointerException e) {
        }
        queryDatabase();
        return rootView;
    }

    public void queryDatabase(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Messages");
        switch(option){
            case 0:
                break;
            default:
                query.whereEqualTo("group", filter);
                query.fromLocalDatastore();
                Toast.makeText(getActivity(), "" + filter, Toast.LENGTH_LONG).show();
                break;
        }
        if(!isNetworkAvailable()){
            query.fromLocalDatastore();
        }
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> fList, ParseException e) {
                if (e == null) {
                    setData(fList);
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        setFilter(position,parent.getItemAtPosition(position).toString() );
        queryDatabase();
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
        public View getView(int position, View convertView, ViewGroup parent){
           View vi = convertView;
           if(convertView == null){
               vi = inflater.inflate(R.layout.feed_item, null);
           }
            TextView title = (TextView) vi.findViewById(R.id.feed_item_title);
            title.setText(getItem(position).get("title").toString());
            TextView desc = (TextView) vi.findViewById(R.id.feed_item_desc);
            desc.setText(getItem(position).get("content").toString());
            TextView group = (TextView) vi.findViewById(R.id.feed_item_group);
            try {
                group.setText(getItem(position).getString("group"));
            }catch(NullPointerException e){
                Toast.makeText(getActivity(),"Null",Toast.LENGTH_LONG).show();
            }
            return vi;
        }
   }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void setGroups(List<Object> a){
        for(Object x: a){
            groups.add((String)x);
        }
    }
}