package cs1530.timecat;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;


public class ProfileSetupActivity extends ActionBarActivity {


    private static final String timeValuesID = "timeValues";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(cs1530.timecat.R.layout.activity_profile_setup);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(cs1530.timecat.R.menu.menu_profile_setup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == cs1530.timecat.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // called when user hits set button
    // must hava a value in all integer fields
    public void setValues(View view) {


        // editTexts from layouts
        View val0 = findViewById(cs1530.timecat.R.id.time_input_0);
        View val1 = findViewById(cs1530.timecat.R.id.time_input_1);
        View val2 = findViewById(cs1530.timecat.R.id.time_input_2);
        View val3 = findViewById(cs1530.timecat.R.id.time_input_2);
        View val4 = findViewById(cs1530.timecat.R.id.time_input_4);

        // arraylist for integer values
        ArrayList<Integer> timeValueArrayList = new ArrayList<Integer>();

        //add Integers to arraylists
        timeValueArrayList.add( new Integer(val0.toString()));
        timeValueArrayList.add( new Integer(val1.toString()));
        timeValueArrayList.add( new Integer(val2.toString()));
        timeValueArrayList.add( new Integer(val3.toString()));
        timeValueArrayList.add( new Integer(val4.toString()));

        // create new Intent to start time display activity connects this to next
        Intent timerIntent = new Intent(this, TimerDisplayActivity.class);

        // add values to intent
        timerIntent.putExtra(timeValuesID, timeValueArrayList);

        // start Start activity
        startActivity(timerIntent);


    }

}
