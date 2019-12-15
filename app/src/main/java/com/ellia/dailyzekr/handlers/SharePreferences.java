package com.ellia.dailyzekr.handlers;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferences {
    private static SharedPreferences sharedPreferences;
   private SharePreferences(){

    }
    public static SharedPreferences getSharedPreferenceObject(Context context){
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        }
        return sharedPreferences;

    }
   public void addData(String key, Object value){

   }
}
