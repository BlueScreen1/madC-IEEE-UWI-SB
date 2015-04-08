package bluescreen1.ieeeuwisb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import bluescreen1.ieeeuwisb.Login.LoginActivity;

public class SplashScreen extends Activity {

    private ImageView logo;
    private Intent mainMenuIntent;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        logo = (ImageView) findViewById(R.id.logo);
        mainMenuIntent = new Intent(this, LoginActivity.class);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(mainMenuIntent);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}

