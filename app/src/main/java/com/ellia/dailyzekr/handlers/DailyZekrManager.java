package com.ellia.dailyzekr.handlers;

import android.content.Context;
import android.util.Log;

import com.ellia.dailyzekr.models.DailyZekr;
import com.ellia.dailyzekr.models.Zekr;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class DailyZekrManager {
    private Context context;
    public DailyZekr zekr;
    public final String TAG = "DailyZekrManager";

    public DailyZekrManager(Context context) {
        this.context = context;
    }

    public DailyZekr getDayZekr(int dayNumber) {
        zekr = new DailyZekr();
        JSONArray data = getJSonData();

        try {
            JSONObject jsonObject = data.getJSONObject(dayNumber);
            zekr.setDay(jsonObject.getString("day"));
            zekr.setZekr(jsonObject.getString("zekr"));
            zekr.setEnglish(jsonObject.getString("trans_en"));
            zekr.setFarsi(jsonObject.getString("trans_fa"));
            zekr.setHindi(jsonObject.getString("trans_hi"));
            zekr.setTurkey(jsonObject.getString("trans_tr"));

            Log.d(TAG, "getDayZekr: " + zekr.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return zekr;
    }

    public ArrayList<DailyZekr> getAllDayZekrs() {
        ArrayList<DailyZekr> zekrs = new ArrayList<>();
        JSONArray data = getJSonData();

        try {
            for (int i = 0; i < data.length(); i++) {
                JSONObject jsonObject = data.getJSONObject(i);
                DailyZekr zekr = new DailyZekr();
                zekr.setDay(jsonObject.getString("day"));
                zekr.setZekr(jsonObject.getString("zekr"));
                zekr.setEnglish(jsonObject.getString("trans_en"));
                zekr.setFarsi(jsonObject.getString("trans_fa"));
                zekr.setHindi(jsonObject.getString("trans_hi"));
                zekr.setTurkey(jsonObject.getString("trans_tr"));
                zekrs.add(zekr);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return zekrs;
    }

    public int getRandomNumber() {
        int high = getJSonData().length();
        return new Random().nextInt(high - 1) + 1;
    }

    public JSONArray getJSonData() {
        JSONArray allQuotes = null;

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            allQuotes = obj.getJSONArray("daily_zekrs");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return allQuotes;
    }

    public String loadJSONFromAsset() {
        String jsonData = null;

        try {
            InputStream is = context.getAssets().open("daily_zekrs.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonData = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return jsonData;
    }
}
