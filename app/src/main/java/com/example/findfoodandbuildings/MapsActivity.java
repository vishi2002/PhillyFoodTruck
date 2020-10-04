package com.example.findfoodandbuildings;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    SupportMapFragment mapFragment;
    private GoogleMap mMap;
    FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this); //maybe causes a null-pointer error (hasn't yet)

        client = LocationServices.getFusedLocationProviderClient(this);

        //check permission
        if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //when permission is there do the following

            getCurrentLocation();
        } else {
            //when permission not given
            //Request permission again
            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 50);
        }
    }

    private void getCurrentLocation()  { //finds the current location of the client
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener((new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                if (location != null){
                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            LatLng currentlocation = new LatLng(location.getLatitude(),
                                    location.getLongitude());
                            mMap.addMarker(new MarkerOptions().position(currentlocation).title("Current Location")); //creates a market with current location
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentlocation, 2f )); //moves camera to current location
                        }
                    });
                }
            }
        }));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 50){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //when permission is given
                getCurrentLocation();
            }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we are adding markets for the different places (food trucks)
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add markers for food trucks
        LatLng center = new LatLng(39.9529, -75.197098);
        //mMap.addMarker(new MarkerOptions().position(center).title("Center of Map")); //don't need marker for center
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, 14)); //move to center of camera with 14 zoom

        for (int i = 0; i < 16; i++)
        {
            mMap.addMarker(new MarkerOptions().position(getCoordinate(i)).title(getFoodTruckName(i)));
        }
    }
    public String getFoodTruckName (int foodtruck) //creates array of strings of names of food trucks to later make marker
    {
        String[] FoodTruckList = {"Mikey's Grill", "Lyn", "John's Lunch Cart", "Rami's", "Sandwich Cart at 35th/Market",
        "Gul's Breakfast and Lunch Cart", "Pete's Little Lunch Box", "Magic Carpet at 36th/Spruce", "Troy Mediterranean at 38th/Spruce",
        "Fruit Truck at 37th/Spruce", "Memo's Lunch Truck", "Hanan House of Pita", "Fruit Truck at 35th/Market", "Troy Mediterranean at 40th/Spruce",
        "Sonic's", "Ali Baba"};

        return FoodTruckList[foodtruck];
    }

    public LatLng getCoordinate(int foodtruck) //creates an array of LatLng objects to later be called to make marker
    {

        LatLng Mikey = new LatLng(-34, 151);
        LatLng Lyn = new LatLng(39.950792, -75.195304);
        LatLng John = new LatLng(39.950331, -75.191717);
        LatLng Rami = new LatLng(39.952977, -75.202820);
        LatLng Sandwich = new LatLng(39.956213, -75.193475);
        LatLng Gul = new LatLng(39.956191, -75.194148);
        LatLng Pete = new LatLng(39.956425, -75.189327);
        LatLng Magic = new LatLng(39.950792, -75.195304);
        LatLng Troy38 = new LatLng(39.951287, -75.199274);
        LatLng Fruit37 = new LatLng(39.951020, -75.197285);
        LatLng Memo = new LatLng(39.957611, -75.189076);
        LatLng Hanan = new LatLng(39.953640, -75.198767);
        LatLng Fruit35 = new LatLng(39.956213, -75.193475);
        LatLng Troy40 = new LatLng(39.951756, -75.203074);
        LatLng Sonic = new LatLng(39.951030, -75.197268);
        LatLng AliBaba = new LatLng(39.953388, -75.196763);
        LatLng[] coordinates = {Mikey, Lyn, John, Rami, Sandwich, Gul, Pete, Magic, Troy38, Fruit37,
        Memo, Hanan, Fruit35, Troy40, Sonic, AliBaba};
        return coordinates[foodtruck];

    }
}