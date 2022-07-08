package com.example.gdsc.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gdsc.R;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    Context context;

    public HomeAdapter(Context context, ArrayList<Home> list) {
        this.context = context;
        this.list = list;
    }

    ArrayList<Home> list;
    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item,parent,false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Home home =list.get(position);
        holder.name.setText(home.getTitle());
        holder.desc.setText(home.getDesc());
        Glide.with(context).load(home.getImg()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder{

        TextView name,desc;
        ImageView img;
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.home_img);
            name=itemView.findViewById(R.id.home_name);
            desc=itemView.findViewById(R.id.home_desc);
        }
    }
}
