package com.example.gads_lbd.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.example.gads_lbd.network.ApiUtil;

import java.io.Console;
import java.io.IOException;
import java.net.URL;

import com.example.gads_lbd.ui.LearningFragment;

public class LearnerAsyncTask extends AsyncTask<URL, Void, String> {

    @Override
    protected String doInBackground(URL... urls) {
        URL searchURL = urls[0];
        String result = null;
        try {
            result = ApiUtil.getJson(searchURL);
        }
        catch (IOException e) {
            Log.e("Error", e.getMessage());
        }
        return result;
    }

    protected void onPostExecute(String result) {

        Log.e("JSON DATA: ", String.valueOf(result));
        LearningFragment.initializeView(result);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        LearningFragment.postExecuted();
    }
}
