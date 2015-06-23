package cs1530.timecat;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

/**
 * Created by Brandon on 6/21/2015.
 */
public class TimerDisplayActivityTest extends ActivityInstrumentationTestCase2<TimerDisplayActivity> {

    public TimerDisplayActivityTest() {
        super(TimerDisplayActivity.class);
    }

    //default check for activity
    public void testActivityExists() {
        TimerDisplayActivity activity = getActivity();
        TimerDisplayActivity check = new TimerDisplayActivity();
        assertNotNull(check);
        assertNotNull(activity);
    }


}