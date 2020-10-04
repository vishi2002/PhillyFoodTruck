package com.example.findfoodandbuildings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    RecyclerView recyclerView;
    String[] names; //array of the name of the place
    String[] addresses; //array of all the addresses
    String[] type; //array of the type of food
    String[] phonenumbers; //array of all the phone numbers
    String[] hours; //array of all the hours
    int images[] = {R.drawable.five_stars, R.drawable.four_halfstars, R.drawable.four_halfstars, R.drawable.four_halfstars,
            R.drawable.four_halfstars, R.drawable.four_halfstars, R.drawable.four_halfstars, R.drawable.four_halfstars,
            R.drawable.four_halfstars, R.drawable.four_halfstars, R.drawable.four_halfstars, R.drawable.four_stars,
            R.drawable.four_stars, R.drawable.four_stars, R.drawable.four_stars, R.drawable.four_stars}; //array of all the ratings with images
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connects all the arrays in Java to the xml arrays
        recyclerView = findViewById(R.id.recyclerView);
        names = getResources().getStringArray(R.array.food_places); //connect xml to java
        addresses = getResources().getStringArray(R.array.Addresses);
        type = getResources().getStringArray(R.array.type);
        phonenumbers = getResources().getStringArray(R.array.phone);
        hours = getResources().getStringArray(R.array.hours);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,names, addresses, type, hours, phonenumbers, images); //send the parameters to the adapter
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void launchMap(View view) {
        Log.d(LOG_TAG, "Button clicked"); //make sure the button is working using a tag
        Intent intent = new Intent(this, MapsActivity.class); //activate the MapActivity when the button is clicked
        startActivity(intent);
    }
}