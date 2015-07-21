package cs1530.timecat;

import android.widget.EditText;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by Matthew on 6/9/2015.
 */
public class ProfileSetupActivityTest extends TestCase {


    public void testContainsNullValue() throws Exception {

        boolean result;

        String str1 = "1234";
        String str2 = "5678";
        String empty = "";

        ArrayList<String> badCase = new ArrayList<String>();
        ArrayList<String> goodCase = new ArrayList<String>();

        goodCase.add(str1);
        goodCase.add(str2);

        badCase.add(str2);
        badCase.add(empty);

        //This is going to just test the weather either ArrayList contains an empty string

        ProfileSetupActivity profilesetupactivity = new ProfileSetupActivity();

        result = profilesetupactivity.containsNullValueString(goodCase);
        assertEquals(false,result);

    }
}



// this is a test update