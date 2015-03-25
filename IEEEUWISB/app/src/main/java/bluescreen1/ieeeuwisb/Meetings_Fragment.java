package bluescreen1.ieeeuwisb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Jus on 3/24/2015.
 */
public class Meetings_Fragment  extends MainActivity.PlaceholderFragment {


    private static final String ARG_SECTION_NUMBER = "section_number";

    public Meetings_Fragment(){

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.feed_layout, container, false);
        ListView upcomingMeetings = (ListView) rootView.findViewById(R.id.upmeetings_list);
        ListView pastMeetings = (ListView) rootView.findViewById(R.id.pastmeetings_list);
        return rootView;
    }
}
