package cs1530.timecat;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import javax.security.auth.Subject;

import cs1530.timecat.R;

public class ReportViewerActivity extends ActionBarActivity {


    // required for intent data retrieval
    private final String logid = "logid";
    private ReportBuilder builder;

    LinearLayout reportLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_viewer);

        //get intent from previous
        Intent intent = getIntent();

        // retrieve Logs objects
        ArrayList<Log> logs = intent.getParcelableArrayListExtra(logid);

        //init liner layout, use to list log
        reportLinearLayout = (LinearLayout)findViewById(R.id.reportLinearLayout);

        // create reports builder
        builder = new ReportBuilder(logs);

        // call method to creat strings and display
        inflateLogs(builder,reportLinearLayout);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_report_viewer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // this class will set up linear View with records
    private void inflateLogs(ReportBuilder rb , LinearLayout layout){

        // retreive logs
        ArrayList<String> logStrings = rb.getLogStrings();

        // new liner layout
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT );

        for (String s : logStrings){

            TextView tv = new TextView(getApplicationContext());

            tv.setText(s);

            layout.addView(tv,layoutParams);

        }


    }


    // send email report.
    public void emailButtonPress(View view){

        //text view for sending id
       EditText emailEditText = (EditText)findViewById(R.id.emailEditText);

        // checks if null
        if (emailEditText.getText() != null){


            // subject
            String subject = "TIMECAT REPORT";


            // initialize intent
            Intent emailIntent = new Intent(Intent.ACTION_SEND);

            // specifies the type of message, will tell android to ask user for specific mail client
            emailIntent.setType("message/rfc822");

            // build report for email
            StringBuilder sb = new StringBuilder();
            for (String s :builder.getLogStrings()){

                sb.append(s+"\n");

            }


            //add string values to intent
            emailIntent.putExtra(Intent.EXTRA_EMAIL, emailEditText.getText());
            emailIntent.putExtra(Intent.EXTRA_SUBJECT , subject);
            emailIntent.putExtra(Intent.EXTRA_TEXT , sb.toString());

            // try to send email
            try {
                startActivity(emailIntent.createChooser(emailIntent,"SENDING EMAIL"));
            }
            catch (Exception e) {
                Toast.makeText(this, "Error sending email ", Toast.LENGTH_SHORT).show();
            }


        }

    }

}
