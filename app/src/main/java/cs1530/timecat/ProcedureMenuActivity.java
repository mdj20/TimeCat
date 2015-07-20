package cs1530.timecat;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.view.View;
import android.content.Intent;


import java.util.ArrayList;

import cs1530.timecat.R;

public class ProcedureMenuActivity extends ListActivity {

    private ListView list_view;
    private ArrayList<String> procedures;
    private String [] procedure_array;
    private DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedure_menu);

        db = new DbHelper(this);
        procedures = db.getProcedureNames();
        procedure_array = new String[procedures.size()];
        procedure_array = procedures.toArray(procedure_array);


        list_view =(ListView)findViewById(android.R.id.list);

        list_view.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , procedure_array));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_procedure_menu, menu);
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

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l, v, position, id);

        String name = (String) l.getItemAtPosition(position);

        // set new intent
        Intent timerDisplayIntent = new Intent(this,TimerDisplayActivity.class);

        // put timer values objects into intent
        timerDisplayIntent.putParcelableArrayListExtra("procedureBuilder",db.getProcedure(name));

        startActivity(timerDisplayIntent);
    }
}
