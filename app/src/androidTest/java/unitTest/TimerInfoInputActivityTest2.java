package cs1530.timecat;

import android.content.Intent;
import android.test.ActivityUnitTestCase;

import cs1530.timecat.R;
import cs1530.timecat.TimerInfoInputActivity;
import cs1530.timecat.TimerInfoInputActivityTest;
import android.widget.Button;

public class TimerInfoInputActivityTest2 extends ActivityUnitTestCase<TimerInfoInputActivity> {

    private Intent checkIntent;
    private Button buttonDone;


    public TimerInfoInputActivityTest2() {
        super(TimerInfoInputActivity.class);
    }
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        //setup intent from TimerInfoInputActivity class
        Intent checkIntent = new Intent(getInstrumentation().getTargetContext(), TimerInfoInputActivity.class);
        startActivity(checkIntent, null, null);

        //setup button

        buttonDone = (Button) getActivity().findViewById(R.id.buttonDone);

    }

    public void testNextActivityIntent() {

        startActivity(checkIntent, null, null);
        buttonDone = (Button) getActivity().findViewById(R.id.buttonDone);
        buttonDone.performClick();

        //get intent
        final Intent launchIntent = getStartedActivityIntent();
        assertNotNull("Intent was null", launchIntent);
        assertTrue(isFinishCalled());

        // check if it worked

        // maybe check the output string from button corresponding to method


    }


}