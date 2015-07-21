package cs1530.timecat;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


import java.util.ArrayList;


public class ProfileSetupActivity extends ActionBarActivity {


    private static final String timeValuesID = "timeValues";
    private static final String numOfStepsKey = "numOfSteps";

    private DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);
        db = new DbHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        menu.clear();

        ArrayList<String> procedure_list = new ArrayList<String>();
        procedure_list = db.getProcedureNames();

        for(int i = 0; i < procedure_list.size(); i++)
        {
            menu.add(procedure_list.get(i));
        }

        getMenuInflater().inflate(R.menu.menu_profile_setup, menu);
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





    public void buildNewProcedure(View view){

        Intent procedureIntent = new Intent(this,Procedure_name_retrieval.class);

        startActivity(procedureIntent);


    }

    public void loadProcedure(View view){
        Intent loadProcedureIntent = new Intent(this, ProcedureMenuActivity.class);

        startActivity(loadProcedureIntent);
    }
}
