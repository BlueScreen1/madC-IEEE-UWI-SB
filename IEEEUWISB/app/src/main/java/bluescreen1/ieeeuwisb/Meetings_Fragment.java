package bluescreen1.ieeeuwisb;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Jus on 3/24/2015.
 */
public class Meetings_Fragment  extends Fragment {

    public static Meetings_Fragment newInstance() {
        Meetings_Fragment fragment = new Meetings_Fragment();
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
    }
}
