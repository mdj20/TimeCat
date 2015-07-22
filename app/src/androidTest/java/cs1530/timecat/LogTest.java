package cs1530.timecat;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by matthew on 7/22/15.
 *
 *
 *
 */

public class LogTest extends TestCase {




    public void testGetTimeStepInfo() throws Exception {

        String procedure = "TEST PROCEDURE";

        ArrayList<TimeStepInfo> testTSIs  = new ArrayList<TimeStepInfo>();

        ArrayList<Log> logs = new ArrayList<Log>();

        ArrayList<Date> startDates = new ArrayList<Date>();
        ArrayList<Date> endDates = new ArrayList<Date>();

        int startMultiplier = 5000;
        int endMultiplier = 2;

        // initialise test objects
        for (int i = 0 ; i < 100 ; i++){

            testTSIs.add(new TimeStepInfo(100*i,i,procedure,"name","info") );
            startDates.add(new Date(i*startMultiplier));
            endDates.add(new Date(i*startMultiplier*endMultiplier));

        }

        for (Date d : startDates) {
            System.out.println(d);
        }

        assertTrue(true);

    }

    public void testGetTimeStarted() throws Exception {



    }

    public void testGetTimeFinished() throws Exception {



    }

    public void testGetInterval() throws Exception {

    }

    public void testIsFinished() throws Exception {

    }

    public void testSetTimeFinished() throws Exception {

    }
}