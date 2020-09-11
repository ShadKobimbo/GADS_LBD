package com.example.gads_lbd.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gads_lbd.R;
import com.example.gads_lbd.models.Learner;

import java.util.ArrayList;

public class LearnersAdapter extends RecyclerView.Adapter<LearnersAdapter.LearnersViewHolder> {

    private ArrayList<Learner> learners;
    public LearnersAdapter(ArrayList<Learner> learners) {
        this.learners = learners;
    }

    @Override
    public LearnersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_learner_card, parent, false);
        return new LearnersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LearnersViewHolder holder, int position) {
        Learner learner = learners.get(position);
        holder.setDetails(learner);

    }

    @Override
    public int getItemCount() {
        return learners.size();
    }

    public class LearnersViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvHours;
        TextView tvCountry;

        public LearnersViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvHours= (TextView) itemView.findViewById(R.id.tvHours);
            tvCountry = (TextView) itemView.findViewById(R.id.tvCountry);
        }

        public void setDetails(Learner learner) {
            tvName.setText(learner.learner_name);
            tvHours.setText(learner.learner_hours);
            tvCountry.setText(learner.learner_country);

        }

    }
}
