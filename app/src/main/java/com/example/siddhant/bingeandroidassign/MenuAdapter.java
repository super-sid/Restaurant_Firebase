package com.example.siddhant.bingeandroidassign;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.fyHolder> {

    List<MenuModel> menuModelList;
    Context ctx;

    public MenuAdapter(List<MenuModel> menuModelList, Context ctx){
        this.menuModelList = menuModelList;
        this.ctx = ctx;
    }

    @Override
    public MenuAdapter.fyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item,parent,false);
        MenuAdapter.fyHolder ffyHolder= new MenuAdapter.fyHolder(view);
        return ffyHolder;
    }

    public void onBindViewHolder(MenuAdapter.fyHolder holder, int position) {
        final MenuModel data = menuModelList.get(position);
        holder.dish.setText(data.getDish());
        holder.dish_price.setText(data.getPrice());
    }

    @Override
    public int getItemCount() {
        return menuModelList.size();
    }


    class fyHolder extends RecyclerView.ViewHolder{
        TextView dish, dish_price;

        public fyHolder(View itemView) {
            super(itemView);
            dish = itemView.findViewById(R.id.dish);
            dish_price= itemView.findViewById(R.id.priceDish);

        }
    }
}
