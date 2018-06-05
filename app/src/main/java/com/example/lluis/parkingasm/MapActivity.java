package com.example.lluis.parkingasm;

import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import cat.tomasgis.app.providers.parkingprovider.contracts.ModelContracts;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    private static final String TAG = com.example.lluis.parkingasm.MapActivity.class.getSimpleName();
    private GoogleMap mMap;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        double latitude;
        double longitude;

        // Add a marker in Sydney and move the camera
        Cursor cursor = this.getContentResolver().query(ModelContracts.LocationModel.buildContentUri(),ModelContracts.LocationModel.DEFAULT_PROJECTIONS,null,null, ModelContracts.LocationModel.DEFAULT_SORT);

        if (cursor.getCount()>0) {

            cursor.moveToFirst();
            latitude = cursor.getDouble(cursor.getColumnIndex(ModelContracts.LocationContract.LATITUDE));
            longitude = cursor.getDouble(cursor.getColumnIndex(ModelContracts.LocationContract.LONGITUDE));

            Log.i(TAG,"Parking Location "+latitude+" , "+longitude);
        }
        else
        {
            latitude = 41.1258642;
            longitude = 1.2035639;
            Log.e(TAG,"Default location");
        }

        LatLng park_location = new LatLng(latitude,longitude);
        mMap.addMarker(new MarkerOptions().position(park_location).title("Marcador Parking"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(park_location,15.f));

    }
}