package cs1530.timecat;

import junit.framework.TestCase;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by matthew on 7/22/15.
 *
 *
 *
 */

public class LogTest extends TestCase {


    String procedure = "TEST PROCEDURE";

    ArrayList<TimeStepInfo> testTSIs ;

    ArrayList<Log> logs;

    ArrayList<Date> startDates;
    ArrayList<Date> endDates ;



    public void testGetTimeStepInfo() throws Exception {


        int n = 100;
        int startMultiplier = 5000;
        int endMultiplier = 2;

        ArrayList<TimeStepInfo> testTSIs  = initTimeStepInfos(n);
        ArrayList<Date> startDates = initDates(startMultiplier,n);
        ArrayList<Date> endDates = initDates(endMultiplier, n);

        ArrayList<Log> logs = initLogs(testTSIs,startDates,n);

        logs = finishLogs(logs,endDates,n);

        for (int i = 0 ; i < n ; i++) {

            Date end = endDates.get(i);
            Date start = startDates.get(i);

            assertEquals((logs.get(i)).getInterval(), (end.getTime() - start.getTime()) * 1000);

        }


    }

    public void testGetTimeStarted() throws Exception {




        int n = 100;
        int startMultiplier = 5000;
        int endMultiplier = 2;

        ArrayList<TimeStepInfo> testTSIs  = new ArrayList<TimeStepInfo>();
        ArrayList<Date> startDates = initDates(startMultiplier,n);
        ArrayList<Date> endDates = initDates(endMultiplier,n);

        ArrayList<Log> logs = initLogs(testTSIs, startDates, n);

        logs = finishLogs(logs,endDates,n);



        for (int i = 0 ; i < n ; i++) {

            Date end = endDates.get(i);
            Date start = startDates.get(i);


            assertEquals((logs.get(i)).getInterval(), (end.getTime() - start.getTime()) * 1000);

        }

    }

    public void testGetTimeFinished() throws Exception {


        int n = 100;
        int startMultiplier = 5000;
        int endMultiplier = 2;

        ArrayList<TimeStepInfo> testTSIs  = initTimeStepInfos(n);
        ArrayList<Date> startDates = initDates(startMultiplier,n);
        ArrayList<Date> endDates = initDates(endMultiplier, n);

        ArrayList<Log> logs = initLogs(testTSIs,startDates,n);

        logs = finishLogs(logs,endDates,n);



    }

    public void testGetInterval() throws Exception {


        int n = 100;
        int startMultiplier = 5000;
        int endMultiplier = 2;

        ArrayList<TimeStepInfo> testTSIs  = initTimeStepInfos(n);
        ArrayList<Date> startDates = initDates(startMultiplier,n);
        ArrayList<Date> endDates = initDates(endMultiplier, n);

        ArrayList<Log> logs = initLogs(testTSIs,startDates,n);

        logs = finishLogs(logs,endDates,n);

        for (int i = 0 ; i < n ; i++) {

            Date end = endDates.get(i);
            Date start = startDates.get(i);


            assertEquals((logs.get(i)).getInterval(), (end.getTime() - start.getTime()) * 1000);

        }

    }

    public void testIsFinished() throws Exception {

        ;
        int n = 100;
        int startMultiplier = 5000;
        int endMultiplier = 2;

        ArrayList<TimeStepInfo> testTSIs  = initTimeStepInfos(n);
        ArrayList<Date> startDates = initDates(startMultiplier,n);
        ArrayList<Date> endDates = initDates(endMultiplier, n);

        ArrayList<Log> logs = initLogs(testTSIs,startDates,n);

        logs = finishLogs(logs,endDates,n);

        for (int i = 0 ; i < n ; i++) {

            Date end = endDates.get(i);
            Date start = startDates.get(i);


            assertEquals((logs.get(i)).getInterval(), (end.getTime() - start.getTime()) * 1000);

        }

    }

    public void testSetTimeFinished() throws Exception {

        int n = 100;
        int startMultiplier = 5000;
        int endMultiplier = 2;


        ArrayList<TimeStepInfo> testTSIs  = initTimeStepInfos(n);
        ArrayList<Date> startDates = initDates(startMultiplier,n);
        ArrayList<Date> endDates = initDates(endMultiplier, n);

        ArrayList<Log> logs = initLogs(testTSIs,startDates,n);

        logs = finishLogs(logs,endDates,n);


    }


    // utility code below

    public ArrayList<Log>  initLogs(ArrayList<TimeStepInfo> tsis,  ArrayList<Date> starts, int n){

        ArrayList<Log> returnLogs = new ArrayList<Log>();

        for (int i = 0 ; i < n ; i ++ ) {
            returnLogs.add(new Log(testTSIs.get(i), startDates.get(i)));
        }

        return returnLogs;
    }


    public ArrayList<Log>  finishLogs(ArrayList<Log> inLogs,  ArrayList<Date> ends, int n){



        for (int i = 0 ; i < n ; i ++ ) {
            inLogs.get(i).setTimeFinished(ends.get(i));
        }

        return inLogs;
    }

    public ArrayList<Date> initDates(int multiplier, int n){

        ArrayList<Date> returnDates = new ArrayList<Date>();

        for (int i = 0 ; i < n ; i ++ ) {
            returnDates.add(new Date(i * multiplier));
        }

        return returnDates;

    }

    public ArrayList<TimeStepInfo> initTimeStepInfos(int n){

        ArrayList<TimeStepInfo> retTSI = new ArrayList<TimeStepInfo>();

        for (int i = 0 ; i < n ; i ++ ) {
            testTSIs.add(new TimeStepInfo(100 * i, i, procedure, "name", "info"));
        }

        return retTSI;
    }
}