package cs1530.timecat;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import cs1530.timecat.R;

public class TimerInfoInputActivity extends ActionBarActivity {

    // keys used for intent extra ID
    private static final String timeValuesID = "procedureBuilder";
    private static final String numOfStepsKey = "numOfSteps";

    // Global view variables
    NumberPicker numberPickers[];
    EditText name;
    EditText info;
    ProcedureBuilder procedureBuilder;
    int numOfSteps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_info_input);

        Intent launchIntent = getIntent();
        numOfSteps = launchIntent.getIntExtra(numOfStepsKey,0);

        //initialise parameters and identify views
        setNumberPickerParameters();

        name = (EditText)findViewById(R.id.nameEditText);
        info = (EditText)findViewById(R.id.infoEditText);

        procedureBuilder = new ProcedureBuilder();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timer_info_input, menu);
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


    // called when user wants to add another step.
    public void saveAndNext(View view){

        TimeStepInfo tsi = valuesToObject();
        procedureBuilder.add(tsi);
        resetAllInput();
    }


    // called when user is finished adding steps
    public void saveAndFinish(View view)
    {


        // collect last values
        saveAndNext(view);

        // set new intent
        Intent timerDisplayIntent = new Intent(this,TimerDisplayActivity.class);

        // put timer values objects into intent
        timerDisplayIntent.putParcelableArrayListExtra(timeValuesID,procedureBuilder);

        startActivity(timerDisplayIntent);




    }


    // constructs TimeStepInfo from view values
    private TimeStepInfo valuesToObject(){

        //get time values
        int hours   = numberPickers[0].getValue();
        int minutes = numberPickers[1].getValue();
        int seconds = numberPickers[2].getValue();

        //converts values to seconds
        int durationInSeconds = ((hours*60)+minutes)*60+seconds;

        TimeStepInfo timeStepInfo = new TimeStepInfo(durationInSeconds,procedureBuilder.size(),procedureBuilder.size(),name.getText().toString(),info.getText().toString());

        return timeStepInfo;
    }


    // initialize numberPickers and sets min max value parameters
    private void setNumberPickerParameters(){

        numberPickers = new NumberPicker[3];

        numberPickers[0] = (NumberPicker)findViewById(R.id.hourPicker);
        numberPickers[1] = (NumberPicker)findViewById(R.id.minutePicker);
        numberPickers[2] = (NumberPicker)findViewById(R.id.secondPicker);

        // initialize with Application environment

        // max hours is 10 for now...
        setMinMax(numberPickers[0],0,10);
        setMinMax(numberPickers[1],0,59);
        setMinMax(numberPickers[2],0,59);

        // makes number picker wrap contents
        for (int i = 0 ; i < 3 ; i ++ ){
            numberPickers[i].setWrapSelectorWheel(true);
        }
    }



    // sets min and max values
    private void setMinMax(NumberPicker np, int min ,int max){
        np.setMinValue(min);
        np.setMaxValue(max);
    }

    // resets input views
    private void resetAllInput(){

        name.setText("");
        info.setText("");

        for (int i = 0 ; i < 3 ; i++){
            numberPickers[i].setValue(0);
        }
    }

}
