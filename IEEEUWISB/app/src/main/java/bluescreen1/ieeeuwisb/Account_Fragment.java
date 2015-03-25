package bluescreen1.ieeeuwisb;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;

/**
 * Created by Jus on 3/24/2015.
 */
public class Account_Fragment extends Fragment {

    ParseUser user;

    public Account_Fragment() {

    }
    public static Account_Fragment newInstance() {
        Account_Fragment fragment = new Account_Fragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.account_layout, container, false);
        TextView username = (TextView) rootView.findViewById(R.id.userName);
        username.setText(user.getUsername());
        Button editUser = (Button) rootView.findViewById(R.id.editUser);
        editUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}
