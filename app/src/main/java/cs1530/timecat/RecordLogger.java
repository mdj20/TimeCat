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


    //creates ne record requires procedure name
    public RecordLogger(){


        logger = new ArrayList<Log>();
        timeCreated = new Date();


    }


    //add a record to the logger. The Type defines the nature of the evelt
    // 1 = user start
    // 2 = user stop/pause
    // 3 = equals stop by end of timer
    // 4 = stop due to skip
    public void addRecord(TimeStepInfo tsi, int type){

        // if procedure name hasn't been set, set procedure field
        if(procedure == null){
            procedure = tsi.getProcedure();
        }

        // add new record
        logger.add(new Log(tsi,type,new Date()));
    }


    public ArrayList<Log> getLogArrayList(){
        return logger;
    }

    public Date getTimeCreated(){
        return timeCreated;
    }

    public String getProcedure(){
        return procedure;
    }




}
