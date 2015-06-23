package cs1530.timecat;

import junit.framework.TestCase;

/**
 * Created by matthew on 6/23/15.
 */
public class TimeStepInfoTest extends TestCase {

    public void testSetNotes() throws Exception {

    }

    public void testGetNotes() throws Exception {

    }

    public void testSetTitle() throws Exception {

    }

    public void testGetTitle() throws Exception {

    }

    public void testSetDuration() throws Exception {

    }

    public void testGetDuration() throws Exception {

    }

    public void testSetPriority() throws Exception {

        TimeStepInfo tsi = new TimeStepInfo(0,0,0,"None","none");


        for(int i = 0 ; i < 100 ; i++){
            tsi.setPriority(i);
            assertEquals(tsi.getPriority(),i);
        }

    }

    public void testGetPriority() throws Exception {



        TimeStepInfo tsi = new TimeStepInfo(0,0,0,"None","none");

        for(int i = 0 ; i < 100 ; i++){
            tsi.setPriority(i);
            assertEquals(tsi.getPriority(),i);
        }


    }

    public void testCompareTo() throws Exception {

            TimeStepInfo low = new TimeStepInfo(0,0,0,"null","null");
            TimeStepInfo high = new TimeStepInfo(0,0,0,"null","null");

        for (int i = 1 ; i < 1024 ; i++){

            assertTrue(low.compareTo(high) < 0);

        }


    }

    public void testDescribeContents() throws Exception {

    }

    public void testWriteToParcel() throws Exception {

    }
}