package unitTest;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import cs1530.timecat.DbHelper;


public class DbTest extends AndroidTestCase {

    private static final String TEST_FILE_PREFIX = "test_";
    private DbHelper mMyAdapter;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        RenamingDelegatingContext context = new RenamingDelegatingContext(getContext(), TEST_FILE_PREFIX);

        mMyAdapter = new DbHelper(context);
   //     mDb = DbHelper.getWritableDatabase();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        mMyAdapter.close();
        mMyAdapter = null;
    }

    public void testPreConditions() {
        assertNotNull(mMyAdapter);
    }
}