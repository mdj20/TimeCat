package cs1530.timecat;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;


public class ProfileSetupActivity extends ActionBarActivity {


    private static final String timeValuesID = "timeValues";
    private static final String numOfStepsKey = "numOfSteps";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile_setup, menu);
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


    // called when user hits set button
    // must hava a value in all integer fields
    public void setValues(View view) {

        ArrayList<EditText> timeInputViews = new ArrayList<EditText>();



        // editTexts from layouts
        timeInputViews.add( (EditText) findViewById(R.id.time_input_0));
        timeInputViews.add( (EditText) findViewById(R.id.time_input_1));
        timeInputViews.add( (EditText) findViewById(R.id.time_input_2));
        timeInputViews.add( (EditText) findViewById(R.id.time_input_2));
        timeInputViews.add( (EditText) findViewById(R.id.time_input_4));


        // null value check ; will only execute below code if timeInputViews doesn't contain and nulls
        if (containsNullValue(timeInputViews)==false) {

            // ArrayList for integer values
            ArrayList<Integer> timeValueArrayList = new ArrayList<Integer>();



            for (EditText et : timeInputViews) {
                timeValueArrayList.add(new Integer( et.getText().toString()));
            }

            // create new Intent to start time display activity connects this to next
            Intent timerIntent = new Intent(this, TimerDisplayActivity.class);

            // add values to intent
            timerIntent.putExtra(timeValuesID, timeValueArrayList);

            // start Start activity
            startActivity(timerIntent);
        }

    }

    //returns true if editText is blank
    public boolean containsNullValue(ArrayList<EditText> editTextList ){

        boolean result = false;

        for(EditText et : editTextList){


            result = et.getText().toString().equals("") || result;


        }



        return result;
    }

    //returns true if editText is blank
    public boolean containsNullValueString(ArrayList<String> inStringList ){

        boolean result = false;

        for(String s : inStringList){


            result = s.equals("") || result;


        }



        return result;
    }


    public void buildNewProcedure(View view){

        Intent procedureIntent = new Intent(this,TimerInfoInputActivity.class);

        procedureIntent.putExtra(numOfStepsKey,0);

        startActivity(procedureIntent);


    }
}
