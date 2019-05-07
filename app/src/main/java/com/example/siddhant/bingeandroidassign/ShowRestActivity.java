package com.example.siddhant.bingeandroidassign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowRestActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    FirebaseDatabase dbb;
    List<Restaurant_Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_rest);

        recyclerView = findViewById(R.id.restaurant_recycler);
        dbb = FirebaseDatabase.getInstance();
        mDatabase = dbb.getReference("Restaurants");
        mDatabase.keepSynced(true);
        final RestaurantAdapter restaurantAdapter = new RestaurantAdapter(list,ShowRestActivity.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator( new DefaultItemAnimator());

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list = new ArrayList<Restaurant_Model>();
                list = new ArrayList<>();
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren())
                {
                    String resname= userSnapshot.getKey();
                    String restime= userSnapshot.child("Timings").getValue().toString();
                    String resloc= userSnapshot.child("Location").getValue().toString();
                    String videourl = userSnapshot.child("URL").getValue().toString();

                    Restaurant_Model listdata = new Restaurant_Model();

                    listdata.setName_rest(resname);
                    listdata.setLocation_rest(resloc);
                    listdata.setTimings_rest(restime);
                    listdata.setVideo_url(videourl);
                    list.add(listdata);

                }
                RestaurantAdapter recyclerAdapter = new RestaurantAdapter(list,ShowRestActivity.this);
                RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(ShowRestActivity.this);
                recyclerView.setLayoutManager(layoutmanager);
                recyclerView.setItemAnimator( new DefaultItemAnimator());
                recyclerView.setAdapter(recyclerAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
