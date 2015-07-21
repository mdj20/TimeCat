package cs1530.timecat;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.EventListener;


public class TimerDisplayActivity extends ActionBarActivity implements EventListener {


    // this string is required, used as the key identifier for the time values when passing to
        private static final String timeValuesID = "procedureBuilder";
        private static final String startString = "Start Timer";
        private static final String stopString = "Stop Timer";

    RecordLogger logger;



    ArrayList<TimeStepInfo> timeStepInfos;
    MediaPlayer mediaPlayer;
    TextView textAlarmTextView;

    // main timer
    boolean isRunningMain;
    TextView currentTaskNameOutput;
    TextView mainHour;
    TextView mainMinute;
    TextView mainSecond;

    int indexOfCurrentTask;
    TimeStepInfo currentTask;
    LabTimer labTimerMain;

    //secondary display/timer
    TextView nextTaskNameOutput;
    TextView nextHour;
    TextView nextMinute;
    TextView nextSecond;

    TimeStepInfo nextTask;
    int indexOfLastTask;
    LabTimer labTimerNext;

    Button startStop;

    private DbHelper db;




    // boiler plate code
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_display);


        // new code below

        db = new DbHelper(this);

        mediaPlayer = MediaPlayer.create(getApplication(),R.raw.cat_meow);

        // get intent from previous Activity
        Intent intent = getIntent();

        // get arrayList of TimeStepInfos
        timeStepInfos = intent.getParcelableArrayListExtra(timeValuesID);
        textAlarmTextView = (TextView) findViewById(R.id.textAlarmTextView);

        textAlarmTextView.setText("");

        indexOfCurrentTask = 0;
        indexOfLastTask = timeStepInfos.size()-1;

        // begin in stop state
        isRunningMain = false;

        // get output views Main
        currentTaskNameOutput = (TextView)findViewById(R.id.currentTaskNameOutput);
        mainHour = (TextView)findViewById(R.id.mainHour);
        mainMinute = (TextView)findViewById(R.id.mainMinute);
        mainSecond = (TextView)findViewById(R.id.mainSecond);

        // get output views Next
        nextTaskNameOutput = (TextView)findViewById(R.id.nextTaskNameOutput);
        nextHour = (TextView)findViewById(R.id.nextHour);
        nextMinute = (TextView)findViewById(R.id.nextMinute);
        nextSecond = (TextView)findViewById(R.id.nextSecond);




        startStop = (Button)findViewById(R.id.startStopButton);

        logger = new RecordLogger();


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

        logger.StartLog(timeStepInfos.get(indexOfCurrentTask));


        return true;
    }

    //stops main counter
    public boolean stopTimer(LabTimer inLabTimer){

        // this method will start the countdown

        logger.endLog();

        inLabTimer.stop();
        return false;
    }


    // toggle start stop return isRunning value
    public boolean startStop(View view){

        //Button b = (Button)view;

        // toggle start and stop

        if (labTimerMain.isRunning()){
            stopTimer(labTimerMain);
        }
               else{
            startTimer(labTimerMain);
        }




        //isRunningMain = (isRunningMain)? stopTimer(labTimerMain) : startTimer(labTimerMain);

        return isRunningMain;
    }



    public void skip(View view){

        if (indexOfCurrentTask != indexOfLastTask) {
                       // must stop or it will keep running

                     if (labTimerMain.isRunning()){

                         labTimerMain.stop();

                         logger.endLog();
                            }
                        iterateTimers();

                   }
    }



    // iterates through timers returns false if the last timer has compleated
    public boolean iterateTimers(){

        clearMessageAlarm();

        boolean result = false;

        // If not the last step, push next to current
        if( indexOfCurrentTask < indexOfLastTask ){


            result = true;
            nextToMain(timeStepInfos.get(indexOfCurrentTask+1),labTimerNext);




            // update index

            setNext(indexOfCurrentTask+1);

        } else {

            // end timer
            currentTask = null;
            // set as null

            //ADD return to the begining  code

        }


        return result;

    }



    private LabTimer initTimer(TimeStepInfo tsi, TextView h, TextView m, TextView s, TextView name){
        name.setText(tsi.getTitle());
        return new LabTimer(tsi.getDuration(),h,m,s,this);
    }


    // sets next (Will use Null object for the empty case)
    private void setNext(int i){
        if ( i < indexOfLastTask ) {

            labTimerNext = initTimer(timeStepInfos.get(i), nextHour, nextMinute, nextSecond, nextTaskNameOutput);
        }
        else{
            labTimerNext = initTimer(new TimeStepInfo(0,0,"End","NONE","Null Object"),nextHour,nextMinute,nextSecond,nextTaskNameOutput);
        }
    }

    // sets main
    private void setMain(int i){
       labTimerMain = initTimer(timeStepInfos.get(i),mainHour,mainMinute,mainSecond,currentTaskNameOutput);
        indexOfCurrentTask = i;
    }

    //slides nest to main
    private void nextToMain(TimeStepInfo nextTsi,LabTimer next){
        currentTaskNameOutput.setText(nextTsi.getTitle());
        next.setOutputTargets(mainHour, mainMinute, mainSecond);
        labTimerMain = labTimerNext;
        indexOfCurrentTask++;
    }

    public void alarmEvent(int timeRemaining){

        // if there is more than 5 seconds left then its just a txt notification
        if (timeRemaining > 5 ){


            showMessage(new String("Step is almost finished!!!"), textAlarmTextView);


            // text alarm (Do Nothing for now...)
        }
        else{

            mediaPlayer.setLooping(false);
            mediaPlayer.start();

            isRunningMain = false;

            logger.endLog();
            iterateTimers();


            showMessage("", textAlarmTextView);

            //audible alarm event

        }
        //alarm event and switch


    }



    // mthod that creates the menu for step skipping
    public void menuButtonClick(View inView){

        Button menuButton = (Button)inView;


        PopupMenu popupMenu = new PopupMenu(this, menuButton);

        for (TimeStepInfo tsi : timeStepInfos){
            popupMenu.getMenu().add(Menu.NONE,tsi.getPriority(),tsi.getPriority(),tsi.getTitle());
        }


        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {


                if ( indexOfCurrentTask != item.getItemId()) {

                    // pointer to current Lab timer. Must be stopped manually.
                    if ( labTimerMain.isRunning() == true) {
                        labTimerMain.stop();
                    }

                    clearMessageAlarm();
                    setMain(item.getItemId());
                    setNext(item.getItemId() + 1);



                }

                return true;
            }
        });


        popupMenu.show();

    }


    private void clearMessageAlarm(){
        showMessage("",textAlarmTextView);
    }

    private void showMessage(String message, TextView target){

            target.setText(message);

    }

}
