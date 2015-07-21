package cs1530.timecat;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 *  Created by matthew on 6/3/15.
 *
 *      This class will do the actual count down. It must be created with the Views in the constructor.
 *
 *      The views CAN be updated.
 *
 */
public class LabTimer
{

    private TimerDisplayActivity timerDisplayActivity;
    private CountDownTimer countdowntimer;
    private int initialTime;
    private int remainingTime;
    private int elapsedTime;
    private TextView hourEditText;
    private TextView minuteEditText;
    private TextView secondEditText;

    private boolean running;


    // this will be used for toggling the alarm events and setting the intervlas thagt they occure
    // for now Sprint on (6/22) they are hard coded
    private boolean textAlarmEnabled = true;
    private int textAlarmThreshold = 60  ;

    private boolean audioAlarmEnabled = true;

    // start time currently in seconds
    LabTimer(Integer inTime, TextView h, TextView m , TextView s, TimerDisplayActivity tda){

        timerDisplayActivity = tda;
        initialTime = inTime;
        running = false;

        //output fields
        hourEditText = h;
        minuteEditText = m;
        secondEditText =s ;

        // sets remaining time
        remainingTime = initialTime;

        elapsedTime = 0;

        updateOutputs(remainingTime);
    }

    public CountDownTimer start(){


        running = true;

        updateOutputs(remainingTime);

        // set countdown timer. must be recreated every start
        long  miliTime = 1000*remainingTime;
        countdowntimer = new CountDownTimer(miliTime,1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                elapsedTime++;
                remainingTime--;
                updateOutputs(remainingTime);

                if (remainingTime == textAlarmThreshold){

                    sendAlarm(remainingTime);

                }
            }

            @Override
            public void onFinish() {
                    sendAlarm(0);
            }
        };

        countdowntimer.start();

        return countdowntimer;

    }
    public void stop(){

        running = false;

        countdowntimer.cancel();
    }

    // split integer representation of time (in seconds) to array of hour/min/sec
    public int[] splitTime(int inTime){

        int splitTimes[] = new int[3];

        splitTimes[0] = inTime/3600;

        //set minutes
        inTime = inTime%3600;
        splitTimes[1] = inTime/60;

        // set seconds
        splitTimes[2] = inTime%60;

        return splitTimes;
    }

    // takes remaining time and updates outputs
    private void updateOutputs(int inTime){

        int splitTimes[] = splitTime(inTime);

        hourEditText.setText(String.valueOf(splitTimes[0]));
        minuteEditText.setText(String.valueOf(splitTimes[1]));
        secondEditText.setText(String.valueOf(splitTimes[2]));

    }

    // sets new output EditTexts
    public void setOutputTargets(TextView h, TextView m, TextView s){

        this.hourEditText = h;
        this.minuteEditText = m;
        this.secondEditText = s;
        updateOutputs(remainingTime);
    }

    public void sendAlarm(int timeToSend){

        timerDisplayActivity.alarmEvent(timeToSend);
    }

        public boolean isRunning(){
            return running;
        }

}
