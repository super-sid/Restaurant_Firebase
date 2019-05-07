package com.example.siddhant.bingeandroidassign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.pd.lookatme.LookAtMe;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAct extends AppCompatActivity {

    TextView nameRest, timeRest, locRest;
    String name, time, loc, url;
    RecyclerView menuRecycler;
    private DatabaseReference mDatabase;
    FirebaseDatabase dbb;
    List<MenuModel> list;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        menuRecycler = findViewById(R.id.recycler_menu);
        nameRest = findViewById(R.id.nameeRest);
        timeRest = findViewById(R.id.timeeRest);
        locRest = findViewById(R.id.loccRest);
        button = findViewById(R.id.video_button);

        Intent i = getIntent();
        name = i.getStringExtra("restName");
        time = i.getStringExtra("restTime");
        loc = i.getStringExtra("restLoc");
        url = i.getStringExtra("url");

        nameRest.setText(name);
        timeRest.setText(time);
        locRest.setText(loc);

//        String path = "https://www.youtube.com/watch?v=2t3GnbhbVCc";
//        Uri uri = Uri.parse(path);
//        LookAtMe lookAtMe;
//        lookAtMe = findViewById(R.id.lookme);
//        lookAtMe.setVideoURI(uri);
//        lookAtMe.start();
//        lookAtMe.setLookMe();

        dbb = FirebaseDatabase.getInstance();
        mDatabase = dbb.getReference("Restaurants").child(name).child("Menu");
        mDatabase.keepSynced(true);
        final MenuAdapter restaurantAdapter = new MenuAdapter(list,RestaurantAct.this);
        menuRecycler.setHasFixedSize(true);
        menuRecycler.setLayoutManager(new LinearLayoutManager(this));
        menuRecycler.setItemAnimator( new DefaultItemAnimator());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://"+url)));
            }
        });
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list = new ArrayList<MenuModel>();
                list = new ArrayList<>();
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren())
                {
                    String dish = userSnapshot.getKey();
                    String dishprice = userSnapshot.getValue().toString();

                    MenuModel listdata = new MenuModel();

                    listdata.setDish(dish);
                    listdata.setPrice(dishprice);
                    list.add(listdata);

                }
                MenuAdapter recyclerAdapter = new MenuAdapter(list,RestaurantAct.this);
                RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(RestaurantAct.this);
                menuRecycler.setLayoutManager(layoutmanager);
                menuRecycler.setItemAnimator( new DefaultItemAnimator());
                menuRecycler.setAdapter(recyclerAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
