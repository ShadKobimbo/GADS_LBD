package com.example.gads_lbd.network;

import android.net.Uri;
import android.util.Log;

import com.example.gads_lbd.models.Learner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ApiUtil {

    private ApiUtil(){}

    public static final String BASE_API_URL =
            "https://gadsapi.herokuapp.com/api/hours";

    public static URL buildUrl() {

        URL url = null;
        Uri uri = Uri.parse(BASE_API_URL).buildUpon()
                .build();
        try {
            url = new URL(uri.toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }

    public static String getJson(URL url) throws IOException {

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");
            boolean hasData = scanner.hasNext();
            if (hasData) {
                return scanner.next();
            } else {
                return null;
            }
        }
        catch (Exception e){
            Log.d("Error", e.toString());
            return null;
        }
        finally {
            connection.disconnect();
        }
    }

    public static ArrayList<Learner> getBooksFromJson(String json) {

        final String name = "name";
        final String hours = "hours";
        final String country = "country";
        final String badgeUrl = "badgeUrl";

        ArrayList<Learner> learners = new ArrayList<Learner>();

        try {
            JSONArray arrayLearners = new JSONArray(json);
            int numberOfLearners = arrayLearners.length();

            for (int i = 0; i < numberOfLearners; i++) {

                JSONObject learnerJSON = arrayLearners.getJSONObject(i);

                Learner learner = new Learner(
                        learnerJSON.getString(name),
                        learnerJSON.getString(hours),
                        learnerJSON.getString(country),
                        learnerJSON.getString(badgeUrl));
                learners.add(learner);
            }

            }catch(JSONException e){
                e.printStackTrace();
        }

        return learners;
    }
}
