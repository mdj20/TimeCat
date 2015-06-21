package cs1530.timecat;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
        private static final String startString = "Start Timer";
        private static final String stopString = "Stop Timer";

    ArrayList<TimeStepInfo> timeStepInfos;


    int indexOfCurrentTask;
    int indexOfLastTask;

    // main timer
    boolean isRunningMain;
    EditText currentTaskNameOutput;
    EditText mainHour;
    EditText mainMinute;
    EditText mainSecond;


    TimeStepInfo currentTask;
    TimeStepInfo nextTask;

    LabTimer labTimerMain;






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

        indexOfCurrentTask = 0;
        indexOfLastTask = timeStepInfos.size()-1;

        // begin in stop state
        isRunningMain = false;

        // get output views
        currentTaskNameOutput = (EditText)findViewById(R.id.currentTaskNameOutput);
        mainHour = (EditText)findViewById(R.id.mainHour);
        mainMinute = (EditText)findViewById(R.id.mainMinute);
        mainSecond = (EditText)findViewById(R.id.mainSecond);

        currentTask = timeStepInfos.get(indexOfCurrentTask);
        // nextTask = timeStepInfos.get(indexOfCurrentTask+1);

        currentTaskNameOutput.setText(currentTask.getTitle());


        // initialize labTimer
        labTimerMain = startNewTimers(currentTask,mainHour,mainMinute,mainSecond);





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
    public boolean startTimer(LabTimer inLabTimer){

        inLabTimer.start();
        // this method will start the countdown
        return true;
    }

    //stops main counter
    public boolean stopTimer(LabTimer inLabTimer){

        // this method will start the countdown
        inLabTimer.stop();
        return false;
    }


    // toggle start stop return isRunning value
    public boolean startStop(View view){

        Button b = (Button)view;

        // toggle start and stop
        isRunningMain = (isRunningMain)? startTimer(labTimerMain) : stopTimer(labTimerMain);

        if(isRunningMain){
            b.setText(stopString);
        }
        else{
            b.setText(startString);
        }

        return isRunningMain;
    }

    public LabTimer startNewTimers(TimeStepInfo next, EditText h , EditText m , EditText s){

        return new LabTimer(next.getDuration(),h,m,s);

    }


    // iterates through timers returns false if the last timer has compleated
    public boolean iterateTimers(){

        boolean result = false;

        // If not the last step, push next to current
        if( indexOfCurrentTask < indexOfLastTask ){

            currentTask = nextTask;

            result = true;
            // update index
            indexOfCurrentTask++;

            // repeat for the next task
            if ( (indexOfCurrentTask) < indexOfLastTask){

                nextTask = timeStepInfos.get(indexOfCurrentTask+1);


                // if there is no next set as null...
            }else{

                nextTask = null;
            }



        } else {

            currentTask = null;
            // set as null

        }


        return result;

    }

    public void alarmEvent(){

        //alarm event and switch




    }
}
