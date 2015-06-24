package cs1530.timecat;

import junit.framework.TestCase;




import android.test.mock.MockContext;
import android.widget.EditText;


public class LabTimerTest extends TestCase {




public void testSplitTime() throws Exception {

                //
                //
        MockContext mockContext = new MockContext();

        TimerDisplayActivity tda = new TimerDisplayActivity();

        EditText h = new EditText(tda.getApplicationContext());
        EditText m = new EditText(tda.getApplicationContext());
        EditText s = new EditText(tda.getApplicationContext());

        LabTimer lt = new LabTimer(10000,h,m,s,new TimerDisplayActivity());

        int intArray[];
        int temp ;
        for (int i = 0 ; i < 10000 ; i++){


                    intArray = lt.splitTime(i);
                    //check hours
                    temp = i;
                    assertEquals(temp/3600,intArray[0]);

                    //check minutes
                    temp = temp%3600;
                    assertEquals(temp,intArray[1]);

                    //check seconds
                    temp = temp%60;
                    assertEquals(temp,intArray[2]);
        }

    }
}


