package cs1530.timecat;


import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.TextView;




import junit.framework.TestCase;

import static android.test.TouchUtils.*;

/**
 * Created by Brandon on 6/21/2015.
 */
public class TimerInfoInputActivityTest extends ActivityInstrumentationTestCase2<TimerInfoInputActivity> {

    private View mainLayout;
    private Button setValues;
    private Button done;
    private EditText inputName;
    private TimerInfoInputActivity activity;


    //defualt constructor
    public TimerInfoInputActivityTest() {
        super(TimerInfoInputActivity.class);
    }

    //default check for activity
    public void testActivityExists() {
        TimerInfoInputActivity activity = getActivity();
        assertNotNull(activity);

    }

    //testing function
    @Override
    public void setUp() throws Exception{
        //setup activity
        super.setUp();
        activity = getActivity();
    }

    public void testFunction() throws Exception{

        //text field

        inputName = (EditText) activity.findViewById(R.id.nameEditText);

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                inputName.requestFocus();

            }
        });

        //send string to text field
        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("5");
        getInstrumentation().waitForIdleSync();


        //button section
        setValues = (Button) activity.findViewById(R.id.buttonNext);

        done = (Button)activity.findViewById(R.id.buttonDone);

        //perform click

        TouchUtils.clickView(this, setValues);

        //verify numbers

        //  TextView greetMessage = (TextView) activity.findViewById(R.id.message_text_view);

        String actualText = inputName.getText().toString();

        assertEquals("", actualText);

    }


}