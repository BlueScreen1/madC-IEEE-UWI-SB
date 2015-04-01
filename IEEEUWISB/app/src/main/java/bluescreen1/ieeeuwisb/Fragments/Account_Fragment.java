package bluescreen1.ieeeuwisb.Fragments;

import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

import bluescreen1.ieeeuwisb.MainActivity;
import bluescreen1.ieeeuwisb.R;

public class Account_Fragment extends Fragment implements Edit_User_Fragment.EditUserDialogListener {
    private String newname;
    private String newpassword;
    private String newnumber;
    private String newemail;

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
        ImageButton editUser = (ImageButton) rootView.findViewById(R.id.editUser);
        final DialogFragment dialog = new Edit_User_Fragment();
        editUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
            //    dialog.show(getFragmentManager(), "EditUser");
            }
        });
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // do stuff with the user
            username.setText(currentUser.getUsername());
            email.setText(currentUser.getEmail());

        } else {
            // show the signup or login screen
            Toast.makeText(getActivity(),"You aren't signed in",Toast.LENGTH_LONG).show();
        }
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity)activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onEditUserDialogPositiveClick(DialogFragment dialog) {
/*        ParseUser currentUser = ParseUser.getCurrentUser();
        currentUser.setUsername(newname);
        currentUser.setPassword(newpassword);
        currentUser.setEmail(newemail);
        currentUser.put("ieeenum", newnumber);*/
    }

    @Override
    public void onEditUserDialogNegativeClick(DialogFragment dialog) {
     //   Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_LONG).show();

    }

 /*   public void editData(String username, String password, String ieeenum, String email){
        newname = username;
        newpassword = password;
        newnumber = ieeenum;
        newemail = email;
    }
    */
}
