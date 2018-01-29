package com.hfad.haltbuzzer;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HP on 15-01-2018.
 */

public class DataOperations extends SQLiteOpenHelper {
    private static final String DB_NAME="Station_List";
    private static final String SQLITE_TABLE = "STATIONS";
    private static final int DB_VERSION=1;
    public static final String SNAME = "SNAME";
    public static final String SCODE = "SCODE";
    public static final String _id = "_id";
    DataOperations(Context context)
    {
       super(context,DB_NAME,null,DB_VERSION);
    }

    SQLiteDatabase db = getReadableDatabase();

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        updateMyDatabase(db,0,DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db,oldVersion,newVersion);
    }
    private static void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if(oldVersion<1)
        {
            db.execSQL("CREATE TABLE STATIONS("+"_id INTEGER PRIMARY KEY AUTOINCREMENT,"+"SNAME TEXT,"+"SCODE TEXT);");
            insertStation(db," AHMEDABAD","AMD");
            insertStation(db,"SURAT ","SRT");
            insertStation(db,"RAJKOT","RJT");
            insertStation(db,"DELHI","DEL");
            insertStation(db,"JAIPUR","JAI");
            insertStation(db,"KOTA","KOT");
            insertStation(db,"MUMBAI","MUM");
            insertStation(db,"LUCKNOW","LNW");
            insertStation(db,"UDAIPUR","UDR");
            insertStation(db,"DELHI","DEL");
            insertStation(db,"JAIPUR","JAI");
            insertStation(db,"MATHURA","MTH");
            insertStation(db,"BAREILLY","BLY");
        }
    }

    private static void insertStation(SQLiteDatabase db, String sname,String scode)
    {
        ContentValues stationValues=new ContentValues();
        stationValues.put("SNAME",sname);
        stationValues.put("SCODE",scode);
        db.insert("STATIONS",null,stationValues);
    }
  /*  public void getNames()
    {

        SQLiteDatabase db = getReadableDatabase();


        String[] sqlSelect = {"SNAME", "SCODE"};//_id and Name are rows in my db's table
        String sqlTables = "STATIONS";//table name
        String selection = "_id = ?";
        String[] selectionArgs = {""};
        qb.setTables(sqlTables);
        Cursor c = qb.query(db, sqlSelect, selection, selectionArgs,
                null, null, null);//this query is basically "select Name from table where id=3"
        c.moveToFirst();
    }*/

  /*  public Cursor fetchAllStations() {

        Cursor mCursor = db.query(SQLITE_TABLE, new String[] {_id,SNAME,
                        SCODE},
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
*/
}
