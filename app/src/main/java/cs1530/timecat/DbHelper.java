package cs1530.timecat;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.content.Context;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

/**
 * Created by jacobpavlovich on 7/5/15.
 */
public class DbHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "TimeCate.db";
    private static final String DATABASE_TABLE_ALLPROCEDURES = "AllProcedures";
    private static final String DATABASE_TABLE_PROFILE = "Profile";
    private static final String CREATE_ALLPROCEDURES_TABLE = "CREATE TABLE AllProcedures (id integer primary key AUTOINCREMENT, duration integer, priority integer,procedure text, title text, notes text)";
    //private static final String CREATE_PROFILE_TABLE = "CREATE TABLE Profile (username TEXT PRIMARY KEY, password TEXT)";

    public DbHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_ALLPROCEDURES_TABLE);
        //db.execSQL(CREATE_PROFILE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_ALLPROCEDURES+ "");
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_PROFILE + "");
        onCreate(db);
    }

    public boolean insert_procedure(int duration, int priority,String procedure, String title, String notes)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put("id", id);
        values.put("duration", duration);
        values.put("priority", priority);
        values.put("procedure", procedure);
        values.put("title", title);
        values.put("notes", notes);
        db.insert("AllProcedures", null, values);
        return true;
    }

    /*public boolean insert_profile(String user, String pwd)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", user);
        values.put("password", pwd);
        db.insert(DATABASE_TABLE_PROFILE, null, values);
        return true;
    }*/

    public ArrayList<String> getProcedureNames()
    {
        ArrayList<String> procedures = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor curs = db.rawQuery("SELECT DISTINCT procedure FROM AllProcedures", null);

        curs.moveToFirst();

        while(!curs.isAfterLast())
        {
            procedures.add(curs.getString(curs.getColumnIndex("procedure")));
            curs.moveToNext();
        }

        curs.close();
        return procedures;
    }

    public ProcedureBuilder getProcedure(String name)
    {
        int i, d, pri;
        String pro,t,n;
        ProcedureBuilder pb = new ProcedureBuilder();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor curs = db.rawQuery("SELECT * FROM AllProcedures WHERE procedure = ?",new String[] {name});

        curs.moveToFirst();

        while(!curs.isAfterLast())
        {
            i = curs.getInt(curs.getColumnIndex("id"));
            d = curs.getInt(curs.getColumnIndex("duration"));
            pri = curs.getInt(curs.getColumnIndex("priority"));
            pro = curs.getString(curs.getColumnIndex("procedure"));
            t = curs.getString(curs.getColumnIndex("title"));
            n = curs.getString(curs.getColumnIndex("notes"));
            TimeStepInfo ts = new TimeStepInfo(d,pri,pro,t,n);
            pb.add(ts);
            curs.moveToNext();
        }

        curs.close();
        return pb;
    }
}
