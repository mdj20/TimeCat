package cs1530.timecat;

import java.util.ArrayList;

/**
 * Created by Matthew on 7/21/15.
 */
public class ReportBuilder {

    ArrayList<Log> logs;
    ArrayList<String> logStrings;


    ReportBuilder(ArrayList<Log> inLoggerList){

        logs = inLoggerList;

    }

    // this method will create


    public ArrayList<String> getLogStrings(){

        if (logStrings == null){
            createLogStrings();
        }
        return logStrings;
    }

    public void createLogStrings(){

        logStrings = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();

        for (Log l : logs){

            long time = l.getInterval();


            sb.append( l.getTimeStepInfo().getTitle()+
                    ":\nSTARTED @ : "+l.getTimeStarted()+
                            "\n" + "FINISHED @ : "+l.getTimeFinished()+
                            "\nDURATION: "
                        );

            // hours
            sb.append(time/3600+" : ");

            // minutes
            time = time % 3600;
            sb.append(time/60+ " : ");


            //seconds
            sb.append(time%60);

            // end record with space
            sb.append("\n");

            // create log string
            logStrings.add(sb.toString());

            //reset string builder
            sb.setLength(0);

        }
    }
}
