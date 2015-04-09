package bluescreen1.ieeeuwisb.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import bluescreen1.ieeeuwisb.MainActivity;
import bluescreen1.ieeeuwisb.R;

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
        LinearLayout email = (LinearLayout) rootView.findViewById(R.id.contact_us_email);
        LinearLayout phone = (LinearLayout) rootView.findViewById(R.id.contact_us_phone);
        final TextView emailadd = (TextView) rootView.findViewById(R.id.branch_email_address);
        final TextView phonenum = (TextView) rootView.findViewById(R.id.branch_phone_number);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", emailadd.getText().toString(), null));
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + phonenum.getText().toString()));
                startActivity(phoneIntent);
            }
        });
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