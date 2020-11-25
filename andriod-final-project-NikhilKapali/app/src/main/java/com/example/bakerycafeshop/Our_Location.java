package com.example.bakerycafeshop;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Our_Location extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener {

    private String TAG = "APP_FLOW---->";

    private GoogleMap mMap;
//    GoogleApiClient mGoogleApiClient;
//    private Location mlocation;
//    private LocationManager locationManager;
//    private LocationRequest mLocationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our__location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .addApi(LocationServices.API)
//                .build();
//
//        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        LatLng bakeryCafe = new LatLng(27.706453,85.306988);
    mMap.addMarker(new MarkerOptions().position(bakeryCafe).title("Marker in Bakery Cafe & Shop"));
    mMap.moveCamera(CameraUpdateFactory.newLatLng(bakeryCafe));
    }

    protected void startLocationUpdates(){
//        mLocationRequest = LocationRequest.create()
//                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
//                .setInterval(200)
//                .setFastestInterval(100);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!=
//                PackageManager.PERMISSION_GRANTED
//        ){
//            return;
//        }
//        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
//                mLocationRequest, this);
//        Log.d("reque", "---->>>");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!=
//                PackageManager.PERMISSION_GRANTED
//        ){
//            return;
//        }startLocationUpdates();
//        mlocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
//        if (mlocation == null){
//            startLocationUpdates();
//        }
//        if (mlocation!= null){
//            double latitude = mlocation.getLatitude();
//            double longitude = mlocation.getLongitude();
//            Log.d(TAG, "lat: " +latitude+ " ,lonL " +longitude);
//            LatLng currentLocation = new LatLng(latitude, longitude);
//            mMap.addMarker(new MarkerOptions().position(currentLocation).title("You are here"));
//            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 16f));
//        }else {
//            Toast.makeText(this,"Location not detected", Toast.LENGTH_LONG).show();
//        }
    }

    @Override
    public void onConnectionSuspended(int i) {
//        Log.i(TAG, "Connection Suspended");
//        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//        Log.i(TAG, "Connection Failed. Error:" +connectionResult.getErrorCode());
    }

    @Override
    public void onLocationChanged(Location location) {

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        mGoogleApiClient.connect();
//    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        if(mGoogleApiClient.isConnected()){
//            mGoogleApiClient.disconnect();
//        }
//    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
