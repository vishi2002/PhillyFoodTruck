package com.example.findfoodandbuildings;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    ImageView ratingImageView;
    TextView name, address, type, phone, hours;

    String name1, address1, type1, phone1, hours1;
    int rating1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //connect the textview/imageview objects created to the data in the xml files
        ratingImageView = findViewById(R.id.rating);
        name = findViewById(R.id.name);
        address = findViewById(R.id.Address);
        type = findViewById(R.id.type);
        phone = findViewById(R.id.phonenum);
        hours = findViewById(R.id.hours);

        getData();
        setData();
    }

    private void getData(){ //getter method to get all data from intent
        if(getIntent().hasExtra("Rating") && getIntent().hasExtra("Name") && getIntent().hasExtra("Address")
                && getIntent().hasExtra("Type") && getIntent().hasExtra("PhoneNumber")
                && getIntent().hasExtra("Hours"))
        {
            name1 = getIntent().getStringExtra("Name");
            address1 = getIntent().getStringExtra("Address");
            type1 = getIntent().getStringExtra("Type");
            phone1 = getIntent().getStringExtra("PhoneNumber");
            hours1 = getIntent().getStringExtra("Hours");
            rating1 = getIntent().getIntExtra("Rating", 1);

        }
        else
        {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show(); //error message if no data present to avoid runtime error
        }

    }

    private void setData(){ //setter method
        name.setText(name1);
        address.setText(address1);
        type.setText(type1);
        phone.setText(phone1);
        hours.setText(hours1);
        ratingImageView.setImageResource(rating1);
    }
}