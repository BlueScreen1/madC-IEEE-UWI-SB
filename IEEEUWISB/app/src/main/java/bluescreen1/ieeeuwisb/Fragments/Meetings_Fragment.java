package bluescreen1.ieeeuwisb.Fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import bluescreen1.ieeeuwisb.MainActivity;
import bluescreen1.ieeeuwisb.R;

/**
 * Created by Jus on 3/24/2015.
 */
public class Meetings_Fragment  extends Fragment {


    private static final String ARG_SECTION_NUMBER = "section_number";

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
        ListView upcomingMeetings = (ListView) rootView.findViewById(R.id.upmeetings_list);
        ListView pastMeetings = (ListView) rootView.findViewById(R.id.pastmeetings_list);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
