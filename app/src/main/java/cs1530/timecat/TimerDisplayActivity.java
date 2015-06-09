package cs1530.timecat;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;


public class TimerDisplayActivity extends ActionBarActivity {


    // this string is required, used as the key identifier for the time values when passing to
    private static final String timeValuesID = "timeValues";


    // boiler plate code
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_display);


        // new code below
        Intent intent = getIntent();

        // get AarrayList of Integers
        ArrayList<Integer> timeValueArray = intent.getIntegerArrayListExtra(timeValuesID);

        // create ArrayList for Views
        ArrayList<TextView> timeOutViews = new ArrayList<TextView>();

        // add views to ArrayList, views need to be cast as TextView
        timeOutViews.add((TextView) findViewById(R.id.time_out0));
        timeOutViews.add((TextView) findViewById(R.id.time_out1));
        timeOutViews.add((TextView) findViewById(R.id.time_out2));
        timeOutViews.add((TextView) findViewById(R.id.time_out3));
        timeOutViews.add((TextView) findViewById(R.id.time_out4));


        // method call for text update
        for(int i = 0 ; i < 5 ; i++){
            updateTime(timeOutViews.get(i),timeValueArray.get(i));
        }



    }

    //  Changes the text of tv to the value of i
    private void updateTime(TextView tv, Integer i){

        tv.setText(i.toString());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timer_display, menu);
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
}
