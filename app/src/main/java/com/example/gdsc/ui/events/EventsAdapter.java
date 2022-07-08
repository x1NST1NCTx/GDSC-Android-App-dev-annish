package com.example.gdsc.ui.events;

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

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {

    Context context;

    public EventsAdapter(Context context, ArrayList<Events> list) {
        this.context = context;
        this.list = list;
    }

    ArrayList<Events> list;

    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_item,parent,false);
        return new EventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsViewHolder holder, int position) {
        Events events = list.get(position);
        Glide.with(context).load(events.getImg()).into(holder.events_img);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EventsViewHolder extends RecyclerView.ViewHolder {

        ImageView events_img;
        public EventsViewHolder(@NonNull View itemView) {
            super(itemView);

            events_img = itemView.findViewById(R.id.events_img);
        }
    }
}
