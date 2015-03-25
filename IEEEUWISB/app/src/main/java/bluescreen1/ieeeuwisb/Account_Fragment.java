package bluescreen1.ieeeuwisb;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseUser;

/**
 * Created by Jus on 3/24/2015.
 */
public class Account_Fragment extends MainActivity.PlaceholderFragment {

    ParseUser user;
    TextView username;
    private static final String ARG_SECTION_NUMBER = "section_number";

    public Account_Fragment() {

    }
    public static Account_Fragment newInstance(int sectionNumber) {
        Account_Fragment fragment = new Account_Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.account_layout, container, false);
        username = (TextView) rootView.findViewById(R.id.userName);
        username.setText(user.getUsername());
        return rootView;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
