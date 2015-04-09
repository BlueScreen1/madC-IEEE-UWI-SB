package bluescreen1.ieeeuwisb.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import bluescreen1.ieeeuwisb.MainActivity;
import bluescreen1.ieeeuwisb.R;

public class IEEE_Fragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public static IEEE_Fragment newInstance(int sectionNumber) {
        IEEE_Fragment fragment = new IEEE_Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ieee , container , false);
        Button joinButton = (Button)v.findViewById(R.id.join_button);
        Button renewButton = (Button)v.findViewById(R.id.renew_button);
        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String joinUri = getString(R.string.join_url);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(joinUri));
                startActivity(intent);
            }
        });
        renewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String renewUrl = getString(R.string.renew_url);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(renewUrl));
                startActivity(intent);
            }
        });
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }

    public IEEE_Fragment(){
    }
}