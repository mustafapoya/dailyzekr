package com.ellia.dailyzekr.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class DailyZekrBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        DailyZekrHandler.setTodayImage(context);
    }


}
