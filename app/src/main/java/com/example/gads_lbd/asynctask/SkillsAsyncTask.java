package com.example.gads_lbd.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.example.gads_lbd.network.ApiUtilSkills;
import com.example.gads_lbd.ui.SkillsFragment;

import java.io.IOException;
import java.net.URL;

public class SkillsAsyncTask extends AsyncTask<URL, Void, String> {

    @Override
    protected String doInBackground(URL... urls) {
        URL searchURL = urls[0];
        String result = null;
        try {
            result = ApiUtilSkills.getJson(searchURL);
        }
        catch (IOException e) {
            Log.e("Error", e.getMessage());
        }
        return result;
    }

    protected void onPostExecute(String result) {

        Log.e("JSON DATA: ", String.valueOf(result));
        SkillsFragment.initializeView(result);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        SkillsFragment.postExecuted();
    }
}
