package bluescreen1.ieeeuwisb.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;

import bluescreen1.ieeeuwisb.MainActivity;
import bluescreen1.ieeeuwisb.R;

/**
 * Created by Dane on 4/1/2015.
 */
public class Groups_Fragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    ListView groupslistview;
    ArrayList<ParseObject> groups;

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
        //username.setText(user.getUsername());
        ParseQuery query = new ParseQuery("Groups");


        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}


