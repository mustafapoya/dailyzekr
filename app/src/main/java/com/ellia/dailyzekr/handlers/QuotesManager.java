package com.ellia.dailyzekr.handlers;

import android.content.Context;
import android.util.Log;

import com.ellia.dailyzekr.models.Quote;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class QuotesManager {

    private Context context;
    public Quote quotes;
    public final String TAG = "QuotesManager";

    public QuotesManager(Context context) {
        this.context = context;
    }

    public Quote getQuotes() {
        quotes = new Quote();
        JSONArray data = getJSonData();

        try {
            JSONObject jsonObject = data.getJSONObject(getRandomNumber());
            quotes.setAuthor(jsonObject.getString("author"));
            quotes.setQuote(jsonObject.getString("quote"));
            Log.d(TAG, "getQuotes: " + quotes.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return quotes;
    }

    public int getRandomNumber() {
        int high = getJSonData().length();
        return new Random().nextInt(high - 1) + 1;
    }

    public JSONArray getJSonData() {
        JSONArray allQuotes = null;

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            allQuotes = obj.getJSONArray("quotes");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return allQuotes;
    }

    public String loadJSONFromAsset() {
        String jsonData = null;

        try {
            InputStream is = context.getAssets().open("quotes.json");
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
