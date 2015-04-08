package bluescreen1.ieeeuwisb.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

import bluescreen1.ieeeuwisb.Edit_User;
import bluescreen1.ieeeuwisb.MainActivity;
import bluescreen1.ieeeuwisb.R;

public class Account_Fragment extends Fragment {

    Intent intent;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.account_layout, container, false);
        TextView username = (TextView) rootView.findViewById(R.id.userName);
        TextView email = (TextView) rootView.findViewById(R.id.userEmail);
        TextView number = (TextView) rootView.findViewById(R.id.userIEEE);
        TextView groups = (TextView) rootView.findViewById(R.id.userGroups);
        ImageButton editUser = (ImageButton) rootView.findViewById(R.id.editUser);
        editUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                intent = new Intent(getActivity(),Edit_User.class);
                startActivity(intent);
            }
        });
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
            username.setText(currentUser.getUsername());
            email.setText(currentUser.getEmail());
            number.setText(currentUser.getString("ieeenum"));
            groups.setText(currentUser.getList("Groups").toString());
        } else {
            // show the signup or login screen
            Toast.makeText(getActivity(),"You aren't signed in",Toast.LENGTH_LONG).show();
        }
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
        }
        catch (NullPointerException e){
            throw e;
        }
    }
}