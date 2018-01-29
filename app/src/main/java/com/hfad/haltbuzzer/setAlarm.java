package com.hfad.haltbuzzer;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class setAlarm extends AppCompatActivity {
   // RelativeLayout l1;
   // TextView ttt;
    //EditText et1;
   private SQLiteDatabase db;
   private Cursor cursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_set_alarm);

    /*fill();
        et1=(EditText)findViewById(R.id.head);
        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            fill(et1.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });*/
        ListView list;

        list = (ListView) findViewById(R.id.display);
//        db=openOrCreateDatabase("STATIONS", Context.MODE_PRIVATE,null);
      //  db.execSQL("create table if not exists demo(fname varchar2(20),lname varchar2(20),gender varchar2(20))");



        try {

            SQLiteOpenHelper SO = new DataOperations(this);
            db = SO.getReadableDatabase();
        //   db.execSQL("SELECT SNAME FROM STATIONS");
           cursor = db.query("STATIONS", new String[]{"_id", "SNAME","SCODE"}, null, null, null, null, null);
            CursorAdapter listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_2, cursor, new String[]{"SNAME","SCODE"}, new int[]{android.R.id.text1,android.R.id.text2}, 0);

            list.setAdapter(listAdapter);

        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
if(isServicesOK()) {
    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override

        public void onItemClick(AdapterView<?> listView, View v, int position, long id) {

            String nameText = cursor.getString(1);

            Toast toast = Toast.makeText(setAlarm.this, "YOUR ALARM IS SET!!\tHAPPY JOURNEY!!", Toast.LENGTH_SHORT);
            toast.show();

            Toast toast1 = Toast.makeText(setAlarm.this, nameText, Toast.LENGTH_SHORT);
            toast1.show();
            Intent intent = new Intent(setAlarm.this, google_maps_api.class);
            intent.putExtra("NAME",nameText);
            startActivity(intent);
            //Toast t2=Toast.makeText(setAlarm.this,nameText,Toast.LENGTH_SHORT)

        }
    });
}
    }
    private void init() {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean isServicesOK()
    {
        Log.d("google_maps_api","isServicesOK:checking");
        int a= GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(setAlarm.this);
        if(a== ConnectionResult.SUCCESS){
            Log.d("google_maps_api","isServicesOK:working");
            return  true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(a))
        {
            Log.d("google_maps_api","isServicesOK:error");
            Dialog dialog=GoogleApiAvailability.getInstance().getErrorDialog(setAlarm.this,a,9001);
            dialog.show();
        }
        else{
            Toast.makeText(this,"we can't",Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
  /*  void fill()
    {
        String query="select * from STATIONS";
        SQLiteDatabase db=openOrCreateDatabase("HaltBuzzer",MODE_PRIVATE,null);
        Cursor c=db.rawQuery(query,null);
        if(c.moveToFirst())
        {
            do{
                TextView tv=new TextView(setAlarm.this);
                tv.setText(c.getString(1));
                tv.setTextSize(20);
                registerForContextMenu(tv);
                tv.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        TextView tt=(TextView)v;
                        Intent in=new Intent(setAlarm.this,google_maps_api.class);
                        in.putExtra("SNAME",tt.getText().toString());
                        startActivity(in);
                    }
                });
                l1.addView(tv);

            }while (c.moveToNext());
        }

    else
        Toast.makeText(setAlarm.this,"DATA NOT FOUND",Toast.LENGTH_SHORT).show();

        c.close();
        db.close();
  }
  void fill(String name)
  {
      l1.removeAllViews();
      String query="select * from STATIONS where SNAME like '"+SNAME+"%'";
      SQLiteDatabase db=openOrCreateDatabase("HaltBuzzer",MODE_PRIVATE,null);
      Cursor c=db.rawQuery(query,null);
      if(c.moveToFirst())
      {
          do{
              TextView tv=new TextView(setAlarm.this);
              tv.setText(c.getString(0));
              tv.setTextSize(20);
              registerForContextMenu(tv);
              tv.setOnClickListener(new View.OnClickListener()
              {
                  @Override
                  public void onClick(View v)
                  {
                      TextView tt=(TextView)v;
                      Intent in=new Intent(setAlarm.this,google_maps_api.class);
                      in.putExtra("SNAME",ttt.getText().toString());
                      startActivity(in);
                  }
              });
              l1.addView(tv);

          }while (c.moveToNext());
      }

      else
          Toast.makeText(setAlarm.this,"DATA NOT FOUND",Toast.LENGTH_SHORT).show();

      c.close();
      db.close();
  }
*/




