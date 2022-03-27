package com.ellia.dailyzekr.handlers;

import android.content.Context;
import android.util.Log;

import com.ellia.dailyzekr.models.Quote;
import com.ellia.dailyzekr.models.Zekr;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class ZekrsManager {
    private Context context;
    public Zekr zekr;
    public final String TAG = "ZekrsManager";

    public ZekrsManager(Context context) {
        this.context = context;
    }

    public Zekr getRandomZekr() {
        zekr = new Zekr();
        JSONArray data = getJSonData();

        try {
            JSONObject jsonObject = data.getJSONObject(getRandomNumber());
            zekr.setZekr(jsonObject.getString("author"));
            zekr.setTransFarsi(jsonObject.getString("quote"));


            Log.d(TAG, "getRandomZekr: " + zekr.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return zekr;
    }

    public ArrayList<Zekr> getAllZekrs() {
        ArrayList<Zekr> zekrs = new ArrayList<>();
        JSONArray data = getJSonData();

        try {
            for (int i = 0; i < data.length(); i++) {
                JSONObject jsonObject = data.getJSONObject(i);
                Zekr zekr = new Zekr();
                zekr.setZekr(jsonObject.getString("zekr"));
                zekr.setTransFarsi(jsonObject.getString("trans"));
                zekr.setTransEnglish(jsonObject.getString("trans_en"));
                zekr.setTransHindi(jsonObject.getString("trans_hi"));
                zekr.setTransTurkey(jsonObject.getString("trans_tr"));
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
            allQuotes = obj.getJSONArray("zekrs");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return allQuotes;
    }

    public String loadJSONFromAsset() {
        String jsonData = null;

        try {
            InputStream is = context.getAssets().open("zekrs.json");
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
