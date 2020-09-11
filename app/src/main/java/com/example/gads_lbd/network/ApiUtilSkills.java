package com.example.gads_lbd.network;

import android.net.Uri;
import android.util.Log;

import com.example.gads_lbd.models.Skill;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ApiUtilSkills {

    private ApiUtilSkills(){}

    public static final String BASE_API_URL =
            "https://gadsapi.herokuapp.com/api/skilliq";

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

    public static ArrayList<Skill> getSkillsFromJson(String json) {

        final String name = "name";
        final String score = "score";
        final String country = "country";
        final String badgeUrl = "badgeUrl";

        ArrayList<Skill> skills = new ArrayList<Skill>();

        try {
            JSONArray arraySkills = new JSONArray(json);
            int numberOfSkilliq = arraySkills.length();

            for (int i = 0; i < numberOfSkilliq; i++) {

                JSONObject skillJSON = arraySkills.getJSONObject(i);

                Skill skill = new Skill(
                        skillJSON.getString(name),
                        skillJSON.getString(score),
                        skillJSON.getString(country),
                        skillJSON.getString(badgeUrl));
                skills.add(skill);
            }

        }catch(JSONException e){
            e.printStackTrace();
        }

        return skills;
    }
}
