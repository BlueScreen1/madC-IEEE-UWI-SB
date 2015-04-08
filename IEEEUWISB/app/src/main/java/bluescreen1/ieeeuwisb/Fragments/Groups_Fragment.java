package bluescreen1.ieeeuwisb.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

import bluescreen1.ieeeuwisb.MainActivity;
import bluescreen1.ieeeuwisb.R;

public class Groups_Fragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    ListView groupslistview;
    ArrayList<ParseObject> groups = new ArrayList<>();

    public Groups_Fragment() {
    }
    public static Groups_Fragment newInstance(int sectionNumber) {
        Groups_Fragment fragment = new Groups_Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.groups_layout, container, false);
        groupslistview = (ListView) rootView.findViewById(R.id.groups_listview);
        ParseQuery query = new ParseQuery("Groups");
        query.addAscendingOrder("name");
        query.findInBackground(new FindCallback() {
            @Override
            public void done(List list, ParseException e) {
                if(e == null) {
                    set_data(list);
                }
            }
        });
        groupslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ParseUser currentUser = ParseUser.getCurrentUser();
                    ArrayList<String> groups2 = new ArrayList<String>();
                    groups2 = (ArrayList<String>)currentUser.get("Groups");
                    groups2.add(groups.get(position).getString("name"));
                    currentUser.put("Groups",groups2);
                    currentUser.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            Toast.makeText(getActivity(), "Updated", Toast.LENGTH_LONG).show();
                        }
                    });

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

    public void set_data(List<ParseObject> lst){
        for(ParseObject p: lst){
            groups.add(p);
        }
        groupslistview.setAdapter(new GroupsListAdapter(getActivity(),R.layout.groups_list_item,groups));
    }

    private class GroupsListAdapter extends ArrayAdapter<ParseObject> {
        private Context con;
        private ArrayList<ParseObject> values;
        private LayoutInflater inflater;
        public GroupsListAdapter(Context context, int resource, ArrayList<ParseObject> objects) {
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
                vi = inflater.inflate(R.layout.groups_list_item, null);
            }
            TextView name = (TextView) vi.findViewById(R.id.groups_name);
            name.setText(getItem(position).getString("name"));
            TextView id = (TextView) vi.findViewById(R.id.groups_id);
            id.setText(getItem(position).getObjectId());
            return vi;
        }
    }
}