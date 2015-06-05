package cs1530.timecat;

import android.os.CountDownTimer;
import android.view.View;

/**
 * Created by matthew on 6/3/15.
 */
public class LabTimer
{

    private CountDownTimer countdowntimer;
    private Integer startTime;
    private View view;





    // start time currently in seconds
    LabTimer(Integer time, View outPut){

        //change seconds to miliseconds
        long  miliTime = 1000*time;

        //set private variable as passed in View
        view = outPut;


        countdowntimer = new CountDownTimer(miliTime,1000) {

            @Override
            public void onTick(long millisUntilFinished) {

              //  view


            }

            @Override
            public void onFinish() {

            }
        };


    }


}
