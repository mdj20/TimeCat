package cs1530.timecat;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;


import cs1530.timecat.R;

public class Procedure_name_retrieval extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_procedure_name_retrieval);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_procedure_name_retrieval, menu);
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

    //called when the user clicks the next button.
    public void nextButton(View view)
    {
        String procedure_name = getText();

        Intent next_intent = new Intent(getApplicationContext(), TimerInfoInputActivity.class);
        next_intent.putExtra("procedure_name",procedure_name);
        next_intent.putExtra("numOfSteps",0);
        startActivity(next_intent);
    }

    private String getText()
    {
        EditText new_procedure_name_input = (EditText)findViewById(R.id.pro_name);
        return new_procedure_name_input.getText().toString();
    }
}
