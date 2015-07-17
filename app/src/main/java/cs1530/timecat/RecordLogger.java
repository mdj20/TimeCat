package cs1530.timecat;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 *  Created by matthew on 7/16/15.
 *
 *  This class will build the protable records for the user
 *
 */

public class RecordLogger {

    private static RecordLogger instance = null;
    private HashMap<String, ArrayList<Log>> logger;
    private Date timeCreated;

    protected RecordLogger(){

        logger = new HashMap<String,ArrayList<Log>>();
        timeCreated = new Date();
        // singleton constructer

    }

    public static RecordLogger getInstance(){

        if (instance == null) {

            synchronized (RecordLogger.class){

                if (instance == null)
                    instance = new RecordLogger();

            }

        }

        return instance;
    }

    public void reset(){

        logger  = new HashMap<String,ArrayList<Log>>();
        timeCreated = new Date();

    }

    public void addProcedure(String s) {

        if ( ! logger.containsKey(s)){
            logger.put(s,new ArrayList<Log>());
        }

    }




    // log subclass
    class Log {

        TimeStepInfo tsi;
        int type;
        Date date;


        private Log(TimeStepInfo inTsi, int inType , Date inDate){

            tsi = inTsi;
            type = inType;
            date = inDate;


        }

    }





}
