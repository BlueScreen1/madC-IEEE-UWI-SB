package bluescreen1.ieeeuwisb;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import java.util.ArrayList;

public class IEEE_Fragment extends Fragment {


    public static IEEE_Fragment newInstance() {
        IEEE_Fragment fragment = new IEEE_Fragment();
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
    }

    public IEEE_Fragment(){
    }
}
