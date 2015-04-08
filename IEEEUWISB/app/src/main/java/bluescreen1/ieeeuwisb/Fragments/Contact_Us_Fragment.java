package bluescreen1.ieeeuwisb.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bluescreen1.ieeeuwisb.MainActivity;
import bluescreen1.ieeeuwisb.R;

/**
 * Created by Dane on 4/7/2015.
 */
public class Contact_Us_Fragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public Contact_Us_Fragment(){

    }

    public static Contact_Us_Fragment newInstance(int sectionNumber){
        Contact_Us_Fragment fragment = new Contact_Us_Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.contact_us_fragment, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
        catch (NullPointerException e){
            throw e;
        }
    }

}
