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






    // start time currently in seconds
    LabTimer(Integer inTime, EditText h, EditText m , EditText s){

        initialTime = inTime;

        //output fields
        hourEditText = h;
        minuteEditText = m;
        secondEditText =s ;


        remainingTime = initialTime;

        elapsedTime=0;

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


            }

            @Override
            public void onFinish() {
                // gotta figure this out
            }
        };

        countdowntimer.start();

    }

    public void stop(){
        countdowntimer.cancel();

        // update remainingTime
        remainingTime -= elapsedTime;
    }


    /*  This may not be the right way to do it.
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

    */

    public void updateOutputs(){
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



}
