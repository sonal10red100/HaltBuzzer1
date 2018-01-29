package com.hfad.haltbuzzer;

import android.app.Activity;
import android.provider.BaseColumns;

/**
 * Created by HP on 15-01-2018.
 */

public class table extends Activity
{
    public table()
    {

    }
    public static abstract class TableInfo implements BaseColumns{
        public static final String Station="Station";
        public static final String St_Code="St_Code";
        public static final String Database_Name="StationList";
        public static final String Table_Name="Stations";





    }
}
