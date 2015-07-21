package cs1530.timecat;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 *  Created by matthew on 7/16/15.
 *
 *  This class will build the portable records for the user
 *
 */

public class RecordLogger {

    private ArrayList<Log> logger;
    private String procedure = null;
    private Date timeCreated;
    private Log started = null;
    private boolean logStarted;
    private int numOfFinishedLogs;


    //creates new record
       public RecordLogger(){

        logger = new ArrayList<Log>();
        timeCreated = new Date();
        logStarted = false;
           numOfFinishedLogs = 0;

    }

    public void StartLog(TimeStepInfo tsi){

        // if procedure name hasn't been set, set procedure field
        if (procedure == null){
            procedure = tsi.getProcedure();
        }

        if (logStarted){
           endLog();
        }
        // add new record
        started = new Log(tsi,new Date());
        logStarted = true;
    }

    public void endLog(){

        if (logStarted){

            started.setTimeFinished(new Date());
            logger.add(started);
            logStarted = false;
            numOfFinishedLogs++;
        }

    }

    // close any non finished log in the logger ARrayList
    public void finishLogger(){

        if (numOfFinishedLogs < logger.size()) {

            for (Log l : logger) {

                if (!l.isFinished()) {
                    l.setTimeFinished(new Date());
                }
            }
        }
    }
    public ArrayList<Log> getLogArrayList(){

        finishLogger();
        return logger;
    }

    public Date getTimeCreated(){
        return timeCreated;
    }

    public String getProcedure(){
        return procedure;
    }

}
