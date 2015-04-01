package bluescreen1.ieeeuwisb;

import android.app.Application;

import com.parse.Parse;


public class main_application extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "WBRlhqorpX6TnA5e6VuM0236Oywvpy2YYLneqgjM", "lUrZvMy10A9pEulwKmeEvgsrKyTLmy6TZLolb1SO");

    }
}
