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

    public void createLogStrings(){

        logStrings = new ArrayList<String>();

        StringBuilder sb = new StringBuilder();

        for (Log l : logs){

            sb.append( l.getTimeStepInfo().getTitle()+
                    ":\nSTARTED @ : "+l.getTimeStarted()+
                            "\n" + "FINISHED @ : "+l.getTimeFinished()+
                            "\n DURATION IS SECONDS : "+l.getInterval()
                        );

            logStrings.add(sb.toString());

            //reset string builder
            sb.setLength(0);

        }
    }
}
