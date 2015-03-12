package bluescreen1.ieeeuwisb;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Dane on 3/9/2015.
 */
public class test_main extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.temp_main);

        TextView username = (TextView) findViewById(R.id.textView);
        username.setText("Username: " + getIntent().getStringExtra("username"));
        TextView password = (TextView) findViewById(R.id.textView2);
        password.setText("Password: " + getIntent().getStringExtra("password"));


    }
}



