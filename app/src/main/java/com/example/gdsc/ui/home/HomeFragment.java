package com.example.gdsc.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.gdsc.R;

import com.example.gdsc.SliderAdapter;
import com.example.gdsc.SliderData;
import com.example.gdsc.databinding.FragmentHomeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    RecyclerView recyclerView;
    DatabaseReference database,database1;
    HomeAdapter homeAdapter;
    ArrayList<Home> list;
    ArrayList<SliderData> sliderDataArrayList;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.home_recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<>();
        homeAdapter = new HomeAdapter(getContext(),list);
        recyclerView.setAdapter(homeAdapter);
        database = FirebaseDatabase.getInstance().getReference().child("Database").child("posts");


        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot sp:snapshot.getChildren()){
                    Home home = new Home();
                    String title=sp.child("title").getValue(String.class).toString();
                    String desc=sp.child("description").getValue(String.class).toString();
                    String img=sp.child("image").getValue(String.class).toString();

                    home.setTitle(title);
                    home.setDesc(desc);
                    home.setImg(img);

                    list.add(home);
                }

                homeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        sliderDataArrayList = new ArrayList<>();

        // initializing the slider view.
        SliderView sliderView = root.findViewById(R.id.slider);

        database1 = FirebaseDatabase.getInstance().getReference().child("Database").child("events");
        /*database1.child("slider").push().child("imgurl").setValue("1P");
        database1.child("slider").push().child("imgurl").setValue("2P");
        database1.child("slider").push().child("imgurl").setValue("3P");*/

        SliderAdapter adapter = new SliderAdapter(getContext(), sliderDataArrayList);



        database1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot sp:snapshot.getChildren()){


                    String img=sp.child("image").getValue(String.class).toString();
                    Log.i("img",img);
                    sliderDataArrayList.add(new SliderData(img));
                    sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

                    // below method is used to
                    // setadapter to sliderview.
                    sliderView.setSliderAdapter(adapter);

                    // below method is use to set
                    // scroll time in seconds.
                    sliderView.setScrollTimeInSec(3);

                    // to set it scrollable automatically
                    // we use below method.
                    sliderView.setAutoCycle(true);

                    // to start autocycle below method is used.
                    sliderView.startAutoCycle();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

        // below method is used to
        // setadapter to sliderview.
        sliderView.setSliderAdapter(adapter);

        // below method is use to set
        // scroll time in seconds.
        sliderView.setScrollTimeInSec(3);

        // to set it scrollable automatically
        // we use below method.
        sliderView.setAutoCycle(true);

        // to start autocycle below method is used.
        sliderView.startAutoCycle();

        /*sliderDataArrayList.add(new SliderData(url1));
        sliderDataArrayList.add(new SliderData(url2));
        sliderDataArrayList.add(new SliderData(url3));*/

        // passing this array list inside our adapter class.


        return root;


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}