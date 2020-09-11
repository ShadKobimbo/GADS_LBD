package com.example.gads_lbd.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.gads_lbd.R;
import com.example.gads_lbd.adapters.LearnersAdapter;
import com.example.gads_lbd.adapters.SkillsAdapter;
import com.example.gads_lbd.asynctask.SkillsAsyncTask;
import com.example.gads_lbd.models.Learner;
import com.example.gads_lbd.models.Skill;
import com.example.gads_lbd.network.ApiUtilLearners;
import com.example.gads_lbd.network.ApiUtilSkills;

import java.net.URL;
import java.util.ArrayList;

public class SkillsFragment extends Fragment {

    private static Context context = null;
    private static ProgressBar mLoadingProgress;
    private static RecyclerView rvSkills;
    private static View v;
    private static TextView tvError;

    public SkillsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = getActivity();

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_skills, container, false);

        rvSkills = v.findViewById(R.id.rv_skills);
        mLoadingProgress = (ProgressBar) v.findViewById(R.id.pb_loading);

        try {
            URL url = ApiUtilSkills.buildUrl();
            new SkillsAsyncTask().execute(url);

        }
        catch (Exception e) {
            Log.d("error", e.getMessage());
        }

        return v;
    }

    public static void initializeView(String result) {

        tvError = (TextView) v.findViewById(R.id.tv_error);
        mLoadingProgress.setVisibility(View.INVISIBLE);

        if (result == null) {
            rvSkills.setVisibility(View.INVISIBLE);
            tvError.setVisibility(View.VISIBLE);
        }
        else {
            rvSkills.setVisibility(View.VISIBLE);
            tvError.setVisibility(View.INVISIBLE);
        }

        ArrayList<Skill> skill = ApiUtilSkills.getSkillsFromJson(result);
        String resultString = "";

        LinearLayoutManager skillsLayoutManager =
                new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rvSkills.setLayoutManager(skillsLayoutManager);

        SkillsAdapter adapter = new SkillsAdapter(skill);
        rvSkills.setAdapter(adapter);
    }

    public static void postExecuted() {
    }
}