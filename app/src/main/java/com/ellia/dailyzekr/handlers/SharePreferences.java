package com.ellia.dailyzekr.handlers;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferences {
private static SharePreferences sharedPreferences;
    private  Context context;
    SharedPreferences sharedPref;

    private SharePreferences(Context context){
       this.context = context;
       sharedPref = context.getSharedPreferences("zekr_shared", context.MODE_PRIVATE);
    }

    public static SharePreferences getSharedPreferenceObject(Context context){
        if (sharedPreferences == null) {
            sharedPreferences = new SharePreferences(context);
        }
        return sharedPreferences;
    }

    public void setNotificationStatus(int value){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("notification", value);
        editor.commit();
    }

    public int getNotificationStatus() {
        return sharedPref.getInt("notification", 1);
    }
    public void setZekrImage(int value){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("zekrImage", value);
        editor.commit();
    }
    public int getZekrImage(){
        return sharedPref.getInt("zekrImage",-1);
    }

}
