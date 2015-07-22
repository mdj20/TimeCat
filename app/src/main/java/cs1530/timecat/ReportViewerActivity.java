package cs1530.timecat;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cs1530.timecat.R;

public class ReportViewerActivity extends ActionBarActivity {


    private final String logid = "logid";
    private ReportBuilder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_viewer);


        Intent intent = getIntent();

        ArrayList<Log> logs = intent.getParcelableArrayListExtra(logid);

        LinearLayout reportLinearLayout = (LinearLayout)findViewById(R.id.reportLinearLayout);

        builder = new ReportBuilder(logs);

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



    private void inflateLogs(ReportBuilder rb , LinearLayout layout){

        ArrayList<String> logStrings = rb.getLogStrings();

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT );

        for (String s : logStrings){

            TextView tv = new TextView(getApplicationContext());

            tv.setText(s);

            layout.addView(tv,layoutParams);

        }


    }
}