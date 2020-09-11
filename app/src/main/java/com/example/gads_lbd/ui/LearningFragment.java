package com.example.gads_lbd.ui;

import android.content.Context;
import android.os.AsyncTask;
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
import com.example.gads_lbd.asynctask.LearnerAsyncTask;
import com.example.gads_lbd.models.Learner;
import com.example.gads_lbd.network.ApiUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class LearningFragment extends Fragment {

    private static Context context = null;
    private static ProgressBar mLoadingProgress;
    private static RecyclerView rvLearners;
    private static View v;
    private static TextView tvError;

    public LearningFragment() {
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
        v =  inflater.inflate(R.layout.fragment_learning, container, false);
        rvLearners = v.findViewById(R.id.rv_learners);
        mLoadingProgress = (ProgressBar) v.findViewById(R.id.pb_loading);
        mLoadingProgress.setVisibility(v.VISIBLE);

        try {
            URL url = ApiUtil.buildUrl();
            new LearnerAsyncTask().execute(url);

        }
        catch (Exception e) {
            Log.d("error", e.getMessage());
        }

        return v;
    }

    public static void initializeView(String result){

        tvError = (TextView) v.findViewById(R.id.tv_error);
        mLoadingProgress.setVisibility(View.INVISIBLE);

        if (result == null) {
            rvLearners.setVisibility(View.INVISIBLE);
            tvError.setVisibility(View.VISIBLE);
        }
        else {
            rvLearners.setVisibility(View.VISIBLE);
            tvError.setVisibility(View.INVISIBLE);
        }

        ArrayList<Learner> learner = ApiUtil.getBooksFromJson(result);
        String resultString = "";

        LinearLayoutManager learnersLayoutManager =
                new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        rvLearners.setLayoutManager(learnersLayoutManager);

        LearnersAdapter adapter = new LearnersAdapter(learner);
        rvLearners.setAdapter(adapter);

    }

    public static void postExecuted() {
        mLoadingProgress.setVisibility(View.VISIBLE);
    }

}