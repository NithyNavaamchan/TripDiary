package com.example.tripdiary2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.tripdiary2.databinding.ActivityMapBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapBinding binding;
    List<Address> listGeoCodes;
    double latitude, longitude;
    String countryName;
    private static final int LOCATION_PERMISSION_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if(isPermissionGranted()){
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }else {
            requestLocationPermission();
        }

       /*
       * try {
            listGeoCodes = new Geocoder(this).getFromLocationName("The Open University Of Sri Lanka - Batticaloa Regional Center (BRC), Batticaloa", 1);
            latitude = listGeoCodes.get(0).getLatitude();
            longitude = listGeoCodes.get(0).getLongitude();
            countryName = listGeoCodes.get(0).getCountryName();

            Log.i("Lab_Tag", "Latitude is : " + String.valueOf(latitude));
            Log.i("Lab_Tag", "Longitude is : " + String.valueOf(longitude));
            Log.i("Lab_Tag", "Country is : " + countryName);


        }catch (Exception exception){
           exception.printStackTrace();
        }*/

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        /* *LatLng batticaloa = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(batticaloa).title("Batticaloa"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(batticaloa, 18.0f));
        mMap.getUiSettings().setZoomControlsEnabled(true);*/
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED){
            mMap.setMyLocationEnabled(true);
        }
    }
    //To check wheather permission is granted for the location
    public boolean isPermissionGranted(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==
                PackageManager.PERMISSION_GRANTED){
            return true;
        }else {
            return false;
        }
    }
    // To request to enable location
    public void requestLocationPermission(){
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_CODE);
    }
}