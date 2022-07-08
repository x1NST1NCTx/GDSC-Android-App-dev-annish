package com.example.gdsc.ui.faq;

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
import com.example.gdsc.databinding.FragmentFaqBinding;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FaqFragment extends Fragment {

    private FaqViewModel faqViewModel;
    private FragmentFaqBinding binding;

    RecyclerView recyclerView;
    DatabaseReference database;
    FaqAdapter faqAdapter;
    ArrayList<Faq> list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        faqViewModel =
                new ViewModelProvider(this).get(FaqViewModel.class);

        binding = FragmentFaqBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.faq_recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<>();
        faqAdapter = new FaqAdapter(getContext(),list);
        recyclerView.setAdapter(faqAdapter);
        database = FirebaseDatabase.getInstance().getReference().child("Database").child("faq");

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot sp:snapshot.getChildren()){
                    Faq faq = new Faq();
                    String ques = sp.child("question").getValue(String.class);
                    String ans = sp.child("answer").getValue(String.class);

                    faq.setQues(ques);
                    faq.setAns(ans);

                    Log.i("info",ques);
                    Log.i("info",ans);

                    list.add(faq);
                }

                faqAdapter.notifyDataSetChanged();
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