package bluescreen1.ieeeuwisb;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Dane on 3/2/2015.
 */
public class LoginActivity extends FragmentActivity {

    ImageView login_image;
    private static int logo_state = 0;
    Button login, signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.login_button);
        signup = (Button) findViewById(R.id.sign_up_button);
        login_image = (ImageView) findViewById(R.id.logo_image);
        login_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(logo_state == 0) {
                    login_image.setBackgroundResource(R.drawable.uwi_on_final);

                } else {
                    login_image.setBackgroundResource(R.drawable.uwi_off_final);
                }
                logo_state += 1;
                logo_state %=2;

            }
        });
        final DialogFragment dialog = new Sign_In_Fragment();
        final DialogFragment sign_up_dialog = new Sign_Up_Fragment();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show(getSupportFragmentManager(),"SIGNIN");

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_up_dialog.show(getSupportFragmentManager(),"Sign Up");
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
