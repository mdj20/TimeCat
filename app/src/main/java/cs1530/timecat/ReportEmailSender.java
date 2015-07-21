package cs1530.timecat;

import android.content.Intent;

/**
 * Created by Matthew on 7/21/2015.
 *
 *
 *  This class will email the report to a email address specified
 */
public class ReportEmailSender {

    // Fields required for email
    String recipient;
    String subject;
    String body;


    // constructor requires Strings for recipient subject and body
    ReportEmailSender(String r, String s, String b){

        recipient = r;
        subject = s;
        body = b;


    }

    public boolean sendEmail(){
        boolean result = true;

        // initialize intent
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        // specifies the type of message, will tell android to ask user for specific mail client
        emailIntent.setType("message/rfc822");

        //add string values to intent
        emailIntent.putExtra(Intent.EXTRA_EMAIL, this.recipient);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT , this.subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT , this.body);



        return result;
    }




}
