package bluescreen1.ieeeuwisb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseUser;

public class Edit_User extends ActionBarActivity {
    EditText username;
    EditText email;
    EditText ieeenumber;
    Button accept;
    Button cancel;
    Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user);
        username = (EditText) findViewById(R.id.editusername);
        email = (EditText) findViewById(R.id.edituseremail);
        ieeenumber = (EditText) findViewById(R.id.editusernumber);
        accept = (Button) findViewById(R.id.acceptbutton);
        cancel = (Button) findViewById(R.id.cancelbutton);
        final ParseUser nowuser = ParseUser.getCurrentUser();
        username.setText(nowuser.getUsername());
        email.setText(nowuser.getEmail());
        try{
        ieeenumber.setText(nowuser.getString("ieeenum"));
        }catch(NullPointerException e){

        }
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().isEmpty() && email.getText().toString().isEmpty()) {
                    Toast.makeText(getBaseContext(), "Fill in the empty Fields", Toast.LENGTH_SHORT).show();
                } else if (username.getText().toString().isEmpty()) {
                    Toast.makeText(getBaseContext(), "Invalid username", Toast.LENGTH_SHORT).show();
                } else if (email.getText().toString().isEmpty() || !(email.getText().toString().contains("@"))) {
                    Toast.makeText(getBaseContext(), "Invalid email", Toast.LENGTH_SHORT).show();
                } else {
                    nowuser.setUsername(username.getText().toString());
                    nowuser.setEmail(email.getText().toString());
                    nowuser.put("ieeenum", ieeenumber.getText().toString());
                    nowuser.saveInBackground();
                    Toast.makeText(Edit_User.this, "User Details changed", Toast.LENGTH_SHORT).show();
                    intent = new Intent(v.getContext(), MainActivity.class);
                    startActivity(intent);
                }
            }


        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public Edit_User() {

    }

    @Override
    public void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onStop(){
        super.onStop();
        finish();
    }

}
