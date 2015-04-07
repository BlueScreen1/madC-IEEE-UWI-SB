package bluescreen1.ieeeuwisb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.text.ParseException;

import bluescreen1.ieeeuwisb.Fragments.Account_Fragment;
import bluescreen1.ieeeuwisb.Login.LoginActivity;

public class Edit_User extends ActionBarActivity {
    EditText username;
    EditText email;
    EditText ieeenumber;
    private String password = "derp";
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
        ieeenumber.setText(nowuser.getString("ieeenum"));
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
/*                    password = nowuser.
                    Log.d(password,"password");
                    try {
                        nowuser.delete();
                    } catch (com.parse.ParseException e) {
                        e.printStackTrace();
                    }
                    ParseUser user = new ParseUser();*/
                    nowuser.setUsername(username.getText().toString());
                    nowuser.setEmail(email.getText().toString());
                    nowuser.put("ieeenum", ieeenumber.getText().toString());
                    /*nowuser.signUpInBackground(new SignUpCallback() {
                        public void done(com.parse.ParseException e) {
                            if (e == null) {
                                Toast.makeText(getBaseContext(), "Yay you are registered", Toast.LENGTH_LONG).show();
                            } else {
                                // Sign up didn't succeed. Look at the ParseException
                                // to figure out what went wrong
                            }
                        }
                    });*/
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
