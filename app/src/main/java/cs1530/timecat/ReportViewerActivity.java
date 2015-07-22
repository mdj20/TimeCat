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

import java.util.ArrayList;

import cs1530.timecat.R;

public class ReportViewerActivity extends ActionBarActivity {


    // required for intent data retrieval
    private final String logid = "logid";
    private ReportBuilder builder;

    PopupWindow emailPopupWindow;

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

        ArrayList<String> logStrings = rb.getLogStrings();

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT );

        for (String s : logStrings){

            TextView tv = new TextView(getApplicationContext());

            tv.setText(s);

            layout.addView(tv,layoutParams);

        }


    }


    public void emailButtonPress(View view){

        LinearLayout layout = new LinearLayout(getApplicationContext());


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT );

        EditText emailSpace = new EditText( getApplicationContext() );
        Button button = new Button(getApplicationContext());

        layout.addView(emailSpace,layoutParams);
        layout.addView(button,layoutParams);

        emailPopupWindow = new PopupWindow(layout);

        emailPopupWindow.setContentView(layout);

        emailPopupWindow.showAsDropDown(reportLinearLayout);

    }

}
