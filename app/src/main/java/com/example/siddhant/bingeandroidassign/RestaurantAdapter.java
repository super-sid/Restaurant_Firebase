package com.example.siddhant.bingeandroidassign;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyHolder> {

    List<Restaurant_Model> restaurantModelList;
    Context ctx;

    public RestaurantAdapter(List<Restaurant_Model> restaurantAdapterList, Context ctx){ this.restaurantModelList = restaurantAdapterList;
    this.ctx = ctx;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    public void onBindViewHolder(MyHolder holder, int position) {
        final Restaurant_Model data = restaurantModelList.get(position);
        holder.rest_name.setText(data.getName_rest());
        holder.rest_loc.setText(data.getLocation_rest());
        holder.rest_time.setText(data.getTimings_rest());
        holder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent restInfo = new Intent(v.getContext(),RestaurantAct.class);
                restInfo.putExtra("restName",data.getName_rest());
                restInfo.putExtra("restLoc",data.getLocation_rest());
                restInfo.putExtra("restTime",data.getTimings_rest());
                restInfo.putExtra("url",data.getVideo_url());
                v.getContext().startActivity(restInfo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantModelList.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{
        TextView rest_name, rest_loc, rest_time;
        CardView parent_layout;

        public MyHolder(View itemView) {
            super(itemView);
            rest_name = itemView.findViewById(R.id.restaurant_name);
            rest_loc = itemView.findViewById(R.id.rest_place);
            rest_time = itemView.findViewById(R.id.rest_timings);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
