package com.example.findfoodandbuildings;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    String names1[], addresses1[], types1[], phone1[], hours1[]; //arrays of all the data types
    int images1[];
    Context ct1;
    public RecyclerViewAdapter(Context ct, String[] names, String[] addresses, String[] types, String[] hours, String[] phone, int[] images){ //constructor
    ct1 = ct;
    images1 = images;
    names1 = names;
    addresses1 = addresses;
    types1 = types;
    phone1 = phone;
    hours1 = hours;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //adds each row to the entire layout
        LayoutInflater inflater = LayoutInflater.from(ct1);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new  MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.addresstext.setText(addresses1[position]);
        holder.nametext.setText(names1[position]);
        holder.typetext.setText(types1[position]);
        holder.ratingimage.setImageResource(images1[position]);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() { //clicklistener which allows the user to click the button and go to details page
            @Override
            public void onClick(View v) { //creates "intent" for all of the data so that it can be accessed in detailactivity after clicking
                Intent intent = new Intent(ct1, DetailActivity.class);
                intent.putExtra("Name", names1[position]);
                intent.putExtra("Address", addresses1[position]);
                intent.putExtra("Type", types1[position]);
                intent.putExtra("PhoneNumber", phone1[position]);
                intent.putExtra("Hours", hours1[position]);
                intent.putExtra("Rating", images1[position]);
                ct1.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() { //the number of data (16)
        return types1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView typetext, addresstext, nametext;
        ImageView ratingimage;
        ConstraintLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            typetext = itemView.findViewById(R.id.type);
            addresstext = itemView.findViewById(R.id.address);
            nametext = itemView.findViewById(R.id.name);
            ratingimage = itemView.findViewById(R.id.rating);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }

}
