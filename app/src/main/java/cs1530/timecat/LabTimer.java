package cs1530.timecat;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;

/**
 * Created by matthew on 6/3/15.
 *
 *      This class will do the actual count down. It must be created with the Views in the constructor.
 *
 *
 */
public class LabTimer
{

    private TimerDisplayActivity timerDisplayActivity;
    private CountDownTimer countdowntimer;
    private int initialTime;
    private int remainingTime;
    private int elapsedTime;
    private EditText hourEditText;
    private EditText minuteEditText;
    private EditText secondEditText;

    private int hour;
    private int minute;
    private int second;


    // this will be used for toggling the alarm events and setting the intervlas thagt they occure
    // for now Sprint on (6/22) they are hard coded
    private boolean textAlarmEnabled = true;
    private int textAlarmThreshold = 60  ;

    private boolean audioAlarmEnabled = true;





    // start time currently in seconds
    LabTimer(Integer inTime, EditText h, EditText m , EditText s, TimerDisplayActivity tda){

        timerDisplayActivity = tda;
        initialTime = inTime;

        //output fields
        hourEditText = h;
        minuteEditText = m;
        secondEditText =s ;


        remainingTime = initialTime;

        elapsedTime = 0;

        updateOutputs();


    }

    public void start(){




        updateOutputs();

        // set countdown timer. must be recreated every start
        long  miliTime = 1000*remainingTime;
        countdowntimer = new CountDownTimer(miliTime,1000) {

            @Override
            public void onTick(long millisUntilFinished) {


                elapsedTime++;
                remainingTime--;
                updateOutputs();

                if (remainingTime == textAlarmThreshold){

                }

            }

            @Override
            public void onFinish() {
                    sendAlarm(0);
            }
        };

        countdowntimer.start();

    }

    public void stop(){
        countdowntimer.cancel();

    }



    // takes remaining time this may
    private void updateOutputs(){
        hour = remainingTime/3600;

        //set minutes
        int tempTime = remainingTime%3600;
        minute = tempTime/60;

        // set seconds
        second = tempTime%60;

        hourEditText.setText(String.valueOf(hour));
        minuteEditText.setText(String.valueOf(minute));
        secondEditText.setText(String.valueOf(second));
    }

    // sets new output EditTexts
    public void setOutputTargets(EditText h, EditText m, EditText s){

        this.hourEditText = h;
        this.minuteEditText = m;
        this.secondEditText = s;
        updateOutputs();


    }

    public void sendAlarm(int timeToSend){

        timerDisplayActivity.alarmEvent(timeToSend);
    }


}
