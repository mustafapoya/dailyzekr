package com.ellia.dailyzekr.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class DailyZekrBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("DailyZekrBroadcast", "onReceive:The broadcast is called ");
        DailyZekrHandler.setTodayImage(context);
    }
}
