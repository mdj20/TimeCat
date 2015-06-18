package cs1530.timecat;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.NumberPicker;

import cs1530.timecat.R;

public class TimerInfoInputActivity extends ActionBarActivity {

    NumberPicker hourPicker;
    NumberPicker minutePicker;
    NumberPicker secondPicker;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_info_input);

        setNumberPickerValues();






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


    // initalize numberpickers and sets min max value parameters
    private void setNumberPickerValues(){

        // initialize with Application environment
        hourPicker = new NumberPicker(getApplication());
        minutePicker = new NumberPicker(getApplication());
        secondPicker = new NumberPicker(getApplication());

        setMinMax(hourPicker,0,100);
        setMinMax(minutePicker,0,59);
        setMinMax(secondPicker,0,59);

    }

    private void setMinMax(NumberPicker np, int min ,int max){
        np.setMinValue(min);
        np.setMaxValue(max);
    }

}
