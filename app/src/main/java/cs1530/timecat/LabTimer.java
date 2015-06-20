package cs1530.timecat;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;

/**
 * Created by matthew on 6/3/15.
 */
public class LabTimer
{

    private CountDownTimer countdowntimer;
    private Integer startTime;
    private EditText hourEditText;
    private EditText minuteEditText;
    private EditText secondEditText;

    private int hour;
    private int minute;
    private int second;






    // start time currently in seconds
    LabTimer(Integer time, EditText h, EditText m , EditText s){

        hourEditText = h;
        minuteEditText = m;
        secondEditText =s ;

        //change seconds to miliseconds
        long  miliTime = 1000*time;




        countdowntimer = new CountDownTimer(miliTime,1000) {

            @Override
            public void onTick(long millisUntilFinished) {

              subtractSecond();


            }

            @Override
            public void onFinish() {

            }
        };

        //set hours
        hour = time/3600;

        //set minutes
        time = time%3600;
        minute = time/60;

        // set seconds
        second = time%60;


        //update editTexts;
        updateOutputs();





    }

    public void start(){

        countdowntimer.start();
    }

    public void stop(){
        countdowntimer.cancel();
    }

    public void subtractSecond(){

        if (second > 0){
            second -= 1;

        }
        else if (subtractMinute()){
            second = 59;
        }
    }

    private boolean subtractHour(){
        if (hour > 0){
            hour-=1;
            return true;
        } else {
            return false;
        }
    }
    private boolean subtractMinute(){
        if (minute > 0){
            minute-=1;
            return true;
        } else if (subtractHour()){
            minute = 59;
            return true;
        }
            return false;



    }

    public void updateOutputs(){
        hourEditText.setText(hour);
        minuteEditText.setText(minute);
        secondEditText.setText(second);
    }



}
