package cs1530.timecat;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class TimerDisplayActivity extends ActionBarActivity {


    private static final String timeValuesID = "timeValues";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(cs1530.timecat.R.layout.activity_timer_display);

        Intent intent = getIntent();


        ArrayList<Integer> timeValueArray = intent.getIntegerArrayListExtra(timeValuesID);







    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(cs1530.timecat.R.menu.menu_timer_display, menu);
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
}
