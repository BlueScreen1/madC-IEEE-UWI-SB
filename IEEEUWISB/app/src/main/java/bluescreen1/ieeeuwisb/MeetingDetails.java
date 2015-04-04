package bluescreen1.ieeeuwisb;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MeetingDetails extends ActionBarActivity {

    TextView topic,date, desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_details);
        topic = (TextView) findViewById(R.id.meeting_details_topic);
        date = (TextView) findViewById(R.id.meeting_details_date);
        desc = (TextView) findViewById(R.id.meeting_details_desc);
        topic.setText(getIntent().getStringExtra("topic"));
        date.setText(getIntent().getStringExtra("Date"));
        desc.setText(getIntent().getStringExtra("desc"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_meeting_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onStop(){
        super.onStop();
        finish();
    }
}
