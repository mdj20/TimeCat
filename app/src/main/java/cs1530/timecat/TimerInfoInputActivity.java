package cs1530.timecat;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.NumberPicker;

import cs1530.timecat.R;

public class TimerInfoInputActivity extends ActionBarActivity {

    private static final String timeValuesID = "profileBuilder";

    NumberPicker numberPickers[];






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_info_input);

        Intent intent = getIntent();

        

        //initialise parameters

        setNumberPickerParameters();



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


    private void saveValues(){

    }


    // initialize numberPickers and sets min max value parameters
    private void setNumberPickerParameters(){

        numberPickers[0] = (NumberPicker) findViewById(R.id.hourPicker);
        numberPickers[1] = (NumberPicker) findViewById(R.id.minutePicker);
        numberPickers[2] = (NumberPicker) findViewById(R.id.secondPicker);

        // initialize with Application environment

        // max hours is 100/
        setMinMax(numberPickers[0],0,100);
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

}
