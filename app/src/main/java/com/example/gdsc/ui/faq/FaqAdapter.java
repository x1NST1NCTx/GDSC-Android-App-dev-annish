package com.example.gdsc.ui.faq;

import android.content.Context;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gdsc.R;

import java.util.ArrayList;

import at.blogc.android.views.ExpandableTextView;

public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.FaqViewHolder> {

    Context context;

    public FaqAdapter(Context context, ArrayList<Faq> list) {
        this.context = context;
        this.list = list;
    }

    ArrayList<Faq> list;


    @NonNull
    @Override
    public FaqAdapter.FaqViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_item,parent,false);
        return new FaqViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FaqAdapter.FaqViewHolder holder, int position) {
        Faq faq = list.get(position);
        holder.ques.setText(faq.getQues());
        holder.ans.setText(faq.getAns());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FaqViewHolder extends RecyclerView.ViewHolder {

        TextView ques,ans;
        ViewGroup tContainer;

        public FaqViewHolder(@NonNull View itemView) {

            super(itemView);

            ques = itemView.findViewById(R.id.faq_ques);
            ans = itemView.findViewById(R.id.faq_ans);
            tContainer = itemView.findViewById(R.id.transitionContainer);
            ans.setVisibility(View.GONE);

            ques.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    TransitionManager.beginDelayedTransition(tContainer);
                    if(ans.getVisibility() == View.VISIBLE){
                        ans.setVisibility(View.GONE);
                    }
                    else
                        ans.setVisibility(View.VISIBLE);
                }
            });


        }
    }
}
