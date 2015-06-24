package cs1530.timecat;

import android.content.Intent;
import android.media.MediaPlayer;
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

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.EventListener;


public class TimerDisplayActivity extends ActionBarActivity implements EventListener {


    // this string is required, used as the key identifier for the time values when passing to
        private static final String timeValuesID = "procedureBuilder";
        private static final String startString = "Start Timer";
        private static final String stopString = "Stop Timer";



    ArrayList<TimeStepInfo> timeStepInfos;
    MediaPlayer mediaPlayer;
    TextView textAlarmEditText;

    // main timer
    boolean isRunningMain;
    EditText currentTaskNameOutput;
    EditText mainHour;
    EditText mainMinute;
    EditText mainSecond;

    int indexOfCurrentTask;
    TimeStepInfo currentTask;
    LabTimer labTimerMain;

    //secondary display/timer
    EditText nextTaskNameOutput;
    EditText nextHour;
    EditText nextMinute;
    EditText nextSecond;

    TimeStepInfo nextTask;
    int indexOfLastTask;
    LabTimer labTimerNext;

    Button startStop;




    // boiler plate code
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_display);
        // new code below

        mediaPlayer = MediaPlayer.create(getApplication(),R.raw.alarm);

        // get intent from previous Activity
        Intent intent = getIntent();

        // get arrayList of TimeStepInfos
        timeStepInfos = intent.getParcelableArrayListExtra(timeValuesID);
        textAlarmEditText = (TextView) findViewById(R.id.textAlarmEditText);

        textAlarmEditText.setText("");

        indexOfCurrentTask = 0;
        indexOfLastTask = timeStepInfos.size()-1;

        // begin in stop state
        isRunningMain = false;

        // get output views Main
        currentTaskNameOutput = (EditText)findViewById(R.id.currentTaskNameOutput);
        mainHour = (EditText)findViewById(R.id.mainHour);
        mainMinute = (EditText)findViewById(R.id.mainMinute);
        mainSecond = (EditText)findViewById(R.id.mainSecond);

        // get output views Next
        nextTaskNameOutput = (EditText)findViewById(R.id.nextTaskNameOutput);
        nextHour = (EditText)findViewById(R.id.nextHour);
        nextMinute = (EditText)findViewById(R.id.nextMinute);
        nextSecond = (EditText)findViewById(R.id.nextSecond);




        startStop = (Button)findViewById(R.id.startStopButton);

        //currentTask = timeStepInfos.get(indexOfCurrentTask);
        // nextTask = timeStepInfos.get(indexOfCurrentTask+1);

        // initialize labTimer on main output
        labTimerMain = initTimer(timeStepInfos.get(indexOfCurrentTask),mainHour,mainMinute,mainSecond,currentTaskNameOutput);

        if ((indexOfCurrentTask+1) < timeStepInfos.size()){
            labTimerNext = initTimer(timeStepInfos.get(indexOfCurrentTask+1),nextHour,nextMinute,nextSecond,nextTaskNameOutput);
        }



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

    // this method will start the countdown
    public boolean startTimer(LabTimer inLabTimer){

        inLabTimer.start();

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

        //Button b = (Button)view;

        // toggle start and stop
        isRunningMain = (isRunningMain)? stopTimer(labTimerMain) : startTimer(labTimerMain);

        /*
        if(isRunningMain){
            b.setText(stopString);
        }
        else{
            b.setText(startString);
        }


        */
        return isRunningMain;
    }



    public void skip(View view){

        // must stop or it will keep running
        labTimerMain.stop();

        iterateTimers();

    }



    // iterates through timers returns false if the last timer has compleated
    public boolean iterateTimers(){

        boolean result = false;

        // If not the last step, push next to current
        if( indexOfCurrentTask < indexOfLastTask ){



            nextToMain(timeStepInfos.get(indexOfCurrentTask+1),labTimerNext);



            result = true;
            // update index


            // repeat for the next task
            if ( (indexOfCurrentTask+1) < indexOfLastTask){

                nextTask = timeStepInfos.get(indexOfCurrentTask+2);


                // if there is no next set as null...
            }else{

                labTimerNext = initTimer(new TimeStepInfo(0,0,0,"NONE","Null Object"),nextHour,nextMinute,nextSecond,nextTaskNameOutput);
            }

            indexOfCurrentTask++;


        } else {


            // end timer
            currentTask = null;
            // set as null

        }


        return result;

    }



    private LabTimer initTimer(TimeStepInfo tsi, EditText h, EditText m, EditText s, EditText name){

        name.setText(tsi.getTitle());

        return new LabTimer(tsi.getDuration(),h,m,s,this);


    }


    // sets next (Will use Null object for the empty case)
    private void setNext(TimeStepInfo tsi){
        labTimerNext = initTimer(tsi,nextHour,nextMinute,nextSecond,nextTaskNameOutput);
    }

    // sets main
    private void setMain(TimeStepInfo tsi){

        labTimerMain = initTimer(tsi,mainHour,mainMinute,mainSecond,currentTaskNameOutput);
    }

    //slides nest to main
    private void nextToMain(TimeStepInfo nextTsi,LabTimer next){
        currentTaskNameOutput.setText(nextTsi.getTitle());
        next.setOutputTargets(mainHour,mainMinute,mainSecond);
        labTimerMain = labTimerNext;

    }

    public void alarmEvent(int timeRemaining){

        // if there is more than 5 seconds left then its just a txt notification
        if (timeRemaining > 5 ){


            showMessage(new String("Step  is almost finished!!!"), textAlarmEditText);


            // text alarm (Do Nothing for now...)
        }
        else{

            mediaPlayer.setLooping(false);
            mediaPlayer.start();

            isRunningMain = false;
            iterateTimers();


            showMessage("", textAlarmEditText);

            //audible alarm event

        }
        //alarm event and switch


    }


    private void showMessage(String message, TextView target){

            target.setText(message);

    }

}
