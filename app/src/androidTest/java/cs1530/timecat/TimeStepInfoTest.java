package cs1530.timecat;

import junit.framework.TestCase;

/**
 * Created by matthew on 6/23/15.
 */
public class TimeStepInfoTest extends TestCase {

    public void testSetNotes() throws Exception {

        StringBuilder testString = new StringBuilder("ABC Test Title");

        TimeStepInfo tsi = new TimeStepInfo(0,0,0,testString.toString(),"NONE");

        for (int i = 0 ; i < 100 ; i++){

            testString.delete(0,testString.length());

            testString.append(i);

            tsi.setTitle(testString.toString());

            assertTrue(testString.toString().equals(tsi.getTitle()));

        }



    }

    public void testGetNotes() throws Exception {

        StringBuilder testString = new StringBuilder("ABC Test Title");

        TimeStepInfo tsi = new TimeStepInfo(0,0,0,testString.toString(),"NONE");

        for (int i = 0 ; i < 100 ; i++){

            testString.delete(0,testString.length());

            testString.append(i);

            tsi.setTitle(testString.toString());

            assertTrue(testString.toString().equals(tsi.getTitle()));

        }





    }

    public void testSetTitle() throws Exception {




        StringBuilder testString = new StringBuilder("ABC Test Title");

        TimeStepInfo tsi = new TimeStepInfo(0,0,0,testString.toString(),"NONE");

        for (int i = 0 ; i < 100 ; i++){

            testString.delete(0,testString.length());

            testString.append(i);

            tsi.setTitle(testString.toString());

            assertTrue(testString.toString().equals(tsi.getTitle()));

        }






    }

    public void testGetTitle() throws Exception {

        StringBuilder testString = new StringBuilder("ABC Test Title");

        TimeStepInfo tsi = new TimeStepInfo(0,0,0,testString.toString(),"NONE");

        for (int i = 0 ; i < 100 ; i++){

            testString.delete(0,testString.length());

            testString.append(i);

            tsi.setTitle(testString.toString());

            assertTrue(testString.toString().equals(tsi.getTitle()));

        }







    }

    public void testSetDuration() throws Exception {
        TimeStepInfo tsi = new TimeStepInfo(0,0,0,"None","none");


        for(int i = 0 ; i < 10000 ; i++){
            tsi.setDuration(i);
            assertEquals(tsi.getDuration(),i);
        }

    }

    public void testGetDuration() throws Exception {

        TimeStepInfo tsi = new TimeStepInfo(0,0,0,"None","none");


        for(int i = 0 ; i < 10000 ; i++){
            tsi.setDuration(i);
            assertEquals(tsi.getDuration(),i);
        }



    }

    public void testSetPriority() throws Exception {

        TimeStepInfo tsi = new TimeStepInfo(0,0,0,"None","none");


        for(int i = 0 ; i < 100 ; i++){
            tsi.setPriority(i);
            assertEquals(tsi.getPriority(),i);
        }

    }

    public void testGetPriority() throws Exception {

        TimeStepInfo tsi = new TimeStepInfo(0,0,0,"None","none");

        for(int i = 0 ; i < 100 ; i++){
            tsi.setPriority(i);
            assertEquals(tsi.getPriority(),i);
        }


    }

    public void testCompareTo() throws Exception {




    }

    public void testDescribeContents() throws Exception {

    }

    public void testWriteToParcel() throws Exception {

    }
}