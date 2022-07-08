package com.example.gdsc.ui.events;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gdsc.R;

import com.example.gdsc.databinding.FragmentEventsBinding;
import com.example.gdsc.ui.home.Home;
import com.example.gdsc.ui.home.HomeAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EventsFragment extends Fragment {

    private EventsViewModel eventsViewModel;
    private FragmentEventsBinding binding;

    RecyclerView recyclerView;
    DatabaseReference database;
    EventsAdapter eventsAdapter;
    ArrayList<Events> list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        eventsViewModel =
                new ViewModelProvider(this).get(EventsViewModel.class);

        binding = FragmentEventsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.events_recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        list=new ArrayList<>();
        eventsAdapter=new EventsAdapter(getContext(),list);
        recyclerView.setAdapter(eventsAdapter);
        database = FirebaseDatabase.getInstance().getReference().child("Database").child("events");

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot sp:snapshot.getChildren()){
                    Events events = new Events();
                    String img=sp.child("image").getValue(String.class).toString();

                    events.setImg(img);

                    list.add(events);
                }
                eventsAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}