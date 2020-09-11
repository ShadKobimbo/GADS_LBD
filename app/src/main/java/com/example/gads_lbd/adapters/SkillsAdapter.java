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
import com.example.gads_lbd.models.Skill;

import java.util.ArrayList;

public class SkillsAdapter extends RecyclerView.Adapter<SkillsAdapter.SkillsViewHolder> {

    private ArrayList<Skill> skills;
    public SkillsAdapter(ArrayList<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public SkillsAdapter.SkillsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.skill_list_item, parent, false);
        return new SkillsAdapter.SkillsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillsAdapter.SkillsViewHolder holder, int position) {
        Skill skill = skills.get(position);
        holder.setDetails(skill);
    }

    @Override
    public int getItemCount() {
        return skills.size();
    }

    public class SkillsViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvSkills;
        TextView tvCountry;

        public SkillsViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvSkills= (TextView) itemView.findViewById(R.id.tvSkills);
            tvCountry = (TextView) itemView.findViewById(R.id.tvCountry);
        }

        public void setDetails(Skill skill) {
            tvName.setText(skill.learner_name);
            tvSkills.setText(skill.learner_skills);
            tvCountry.setText(skill.learner_country);
        }
    }
}
