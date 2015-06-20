package cs1530.timecat;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.EventListener;


public class TimerDisplayActivity extends ActionBarActivity implements EventListener {


    // this string is required, used as the key identifier for the time values when passing to
        private static final String timeValuesID = "procedureBuilder";

    ArrayList<TimeStepInfo> timeStepInfos;
    boolean isRunning;
    EditText currentTaskNameOutput;
    EditText mainHour;
    EditText mainMinute;
    EditText mainSecond;

    int indexOfCurrentTask;
    int indexOfLastTask;

    TimeStepInfo  currentTask;
    TimeStepInfo nextTask;

    LabTimer labTimer;






    // boiler plate code
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_display);
        // new code below

        // get intent from previous Activity
        Intent intent = getIntent();

        // get arrayList of
        timeStepInfos = intent.getParcelableArrayListExtra(timeValuesID);

        indexOfCurrentTask =0;
        indexOfLastTask = timeStepInfos.size()-1;

        //begin in stop state;
        isRunning = false;

        // get output views
        currentTaskNameOutput = (EditText)findViewById(R.id.currentTaskNameOutput);
        mainHour = (EditText)findViewById(R.id.mainHour);
        mainMinute = (EditText)findViewById(R.id.mainMinute);
        mainSecond = (EditText)findViewById(R.id.mainSecond);

        currentTask = timeStepInfos.get(indexOfCurrentTask);
        //nextTask = timeStepInfos.get(indexOfCurrentTask+1);

        currentTaskNameOutput.setText("CurrentTask "+ currentTask.getTitle());

       // mainHour.setText(String.valueOf(currentTask.getDuration()));
        //mainMinute.setText(String.valueOf(currentTask.getDuration()));
        //mainSecond.setText(String.valueOf(currentTask.getDuration()));
        //initializze labTimer
        labTimer = new LabTimer(currentTask.getDuration(),mainHour,mainMinute,mainSecond);





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

    //strats main counter
    public boolean startMain(){

        // this method will start the countdown
        return true;
    }

    //stops main counter
    public boolean stopMain(){

        // this method will start the countdown
        return false;
    }


    // toggle start stop return isRunning value
    public boolean startStop(View view){
        isRunning = (isRunning)?stopMain():startMain();
        return isRunning;
    }

    public void alarmEvent(){

    }
}
