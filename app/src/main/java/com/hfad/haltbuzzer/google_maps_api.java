package com.hfad.haltbuzzer;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

//import com.google.android.gms.maps.SupportMapFragment;

public class google_maps_api extends FragmentActivity implements LocationListener,OnMapReadyCallback{
  //  public static final String EXTRA="NAME";
    private GoogleMap mMap;
    TextView t1;
    String value;
    double myLat=0.0,myLong1=0.0;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle=getIntent().getExtras();
         value=bundle.getString("NAME");
        if(googlePlayServiceAvailable())
        {
            Toast.makeText(this, "Perfect", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_maps);

            intitMap();

        }
        else
        {
            // No google Maps


        }

       /* SupportMapFragment mapFragment =
                (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);mapFragment.getMapAsync(this);
**/


        // mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        t1=(TextView)findViewById(R.id.t5);
        t1.setText(value);
        value=value+" Railway Station";

      /*  if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
           // mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        }
            Geocoder gc=new Geocoder(google_maps_api.this);
        try{
            List<Address> list=gc.getFromLocationName(t1.getText().toString(),1);
            Address ad=list.get(0);
            LatLng lng=new LatLng(ad.getLatitude(),ad.getLongitude());
            Toast.makeText(google_maps_api.this,(int)ad.getLatitude()+"Hello",Toast.LENGTH_SHORT).show();
            CameraUpdate cm= CameraUpdateFactory.newLatLngZoom(lng,30);
            mMap.animateCamera(cm);
            MarkerOptions mo=new MarkerOptions();
            mo.position(lng);
            mo.title(ad.getAddressLine(0)+","+ad.getAddressLine(1)+","+ad.getLocality()+","+ad.getCountryName()+","+ad.getLongitude());
            mMap.addMarker(mo);

        }catch(Exception e){
            Toast.makeText(google_maps_api.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }**/
        //ActivityCompat.requestPermissions(google_maps_api.this,new String[]{Manifest.permission.INTERNET},1);
        //SharedPreferences sp=getSharedPreferences("myprefs",MODE_PRIVATE);
       // String str=sp.getString("lastLocation","India Gate,Delhi");
       // go(str);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // 15 zoom level

        // Default Map points to 00 latitued longtiude Africa

    }

    private void intitMap() {
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            // mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        }
        Geocoder gc=new Geocoder(google_maps_api.this);

        try {
            List<Address> list = gc.getFromLocationName(value, 2);
             Address ad = list.get(0);
            LatLng lng = new LatLng(ad.getLatitude(), ad.getLongitude());
          //  Toast.makeText(google_maps_api.this, (int) ad.getLatitude() + "Hello", Toast.LENGTH_SHORT).show();
           myLat=ad.getLatitude();
          myLong1=ad.getLongitude();
            SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            fragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    mMap = googleMap; // here you set your Google map
                    goToLocationZoom(myLat,myLong1, 15);

                    // here you do the rest of your calculations with your map
                }
            });
        }catch(Exception e)
        {
            Toast.makeText(google_maps_api.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }
    private void goToLocationZoom(double lat, double lg,float zoom)
    {
        LatLng ll = new LatLng(lat,lg);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll,zoom);
     //   mMap.animateCamera(update);
        MarkerOptions mo=new MarkerOptions();
        mo.position(ll);
        //mo.title(ad.getAddressLine(0)+","+ad.getAddressLine(1)+","+ad.getLocality()+","+ad.getCountryName()+","+ad.getLongitude());
        mMap.addMarker(mo);
        mMap.moveCamera(update);
        // Camera Update defines Camera move Used using CameraUpdateFactory

    }
    private void goToLocation(double lat, double lg)
    {
        LatLng ll = new LatLng(lat,lg);
        CameraUpdate update = CameraUpdateFactory.newLatLng(ll);
        // move to lan and long

        mMap.moveCamera(update);
    }



    public boolean googlePlayServiceAvailable()
    {
        GoogleApiAvailability api= GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if(isAvailable == ConnectionResult.SUCCESS)
        {
            return true;
        }
        else if(api.isUserResolvableError(isAvailable))
        {
            Dialog dialog = api.getErrorDialog(this,isAvailable,0);
            // 0 is request Code
            dialog.show();

        }
        else
        {
            Toast.makeText(this,"Cannot play services",Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    protected void onResume()
    {
        super.onResume();// setUpMapIfNeeded();
    }
    /*private void setUpMapIfNeeded()
    {

            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }
    private void setUpMap() {

    }*/

        @Override
        public void onProviderEnabled (String Provider)
        {

        }
        @Override
        public void onLocationChanged (Location location)
        {

        }
        @Override
        public void onStatusChanged (String provider,int status, Bundle extras)
        {

        }

    @Override
    protected void onStop()

    {
        super.onStop();
        SharedPreferences sharedPref = getSharedPreferences(
                "MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed=sharedPref.edit();
        ed.putString("lastlocation",t1.getText().toString());
        ed.commit();
    }

    void go(String name)
    {
        Geocoder gc=new Geocoder(google_maps_api.this);
        try{
            List<Address> list=gc.getFromLocationName(name,1);
            Address ad=list.get(0);
            LatLng lng=new LatLng(ad.getLatitude(),ad.getLongitude());
            CameraUpdate cm= CameraUpdateFactory.newLatLngZoom(lng,30);
            if(mMap!=null)
                mMap.animateCamera(cm);
            MarkerOptions mo=new MarkerOptions();
            mo.position(lng);
            mo.title(ad.getAddressLine(0)+","+ad.getAddressLine(1)+","+ad.getLocality()+","+ad.getCountryName()+","+ad.getLongitude());
            mMap.addMarker(mo);

        }catch(Exception e){
            Toast.makeText(google_maps_api.this,e.getMessage(),Toast.LENGTH_SHORT).show();

        }
    }
    @Override
    public void onProviderDisabled(String provider)
    {

    }


    }


      /*


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
 /*
}*/
