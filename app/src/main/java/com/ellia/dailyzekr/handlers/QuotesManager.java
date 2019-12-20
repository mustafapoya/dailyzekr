package com.ellia.dailyzekr.handlers;

import android.content.Context;
import android.util.Log;

import com.ellia.dailyzekr.models.Quotes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Handler;

public class QuotesManager {
    private Context context;
    public Quotes quotes;
    private Random r;
    public final String TAG= "QuotesManager";
    int randomNum;

    public QuotesManager(Context context){
        this.context= context;
    }
    public Quotes getQuotes(){
        quotes = new Quotes();
        JSONArray data = getJSonData();
randomNum=getRandomNumber();
        try {
            JSONObject jsonObject = data.getJSONObject(randomNum);
            quotes.setAuthor(jsonObject.getString("author"));
            quotes.setQuote(jsonObject.getString("quote"));
            Log.d(TAG, "getQuotes: "+ quotes.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return quotes;
    }
    public int getRandomNumber(){
       r  = new Random();
        int low = 1;
        int high = 102;
        int result = r.nextInt(high-low) + low;
        return result;
    }
    public JSONArray getJSonData() {
        JSONArray quotes= null;
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
             quotes  = obj.getJSONArray("quotes");
        } catch (JSONException e) {
            e.printStackTrace();
        }
return quotes;
    }
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = context.getAssets().open("quotes.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
