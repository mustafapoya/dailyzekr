package com.ellia.dailyzekr.core;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class DailyBroadcastReceiverService extends Service {
    private BroadcastReceiver dailyZekrBr;
    private Context context;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerDailyZekrReceiver();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(dailyZekrBr);
        dailyZekrBr = null;
    }



    private void registerDailyZekrReceiver() {
        context= this.getApplicationContext();
        Log.d("Register", "onStart: Now gonna register the broadcast receiver on Daily Boadcast receiver");

        dailyZekrBr = new DailyZekrBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
       filter.addCategory(Intent.CATEGORY_DEFAULT);

        filter.addAction("android.intent.action.ACTION_TIME_CHANGED");
        filter.addAction("android.intent.action.TIME_SET");
        filter.addAction("android.intent.action.DATE_CHANGED");
        filter.addAction("android.intent.action.TIMEZONE_CHANGED");
        this.registerReceiver(dailyZekrBr, filter);
    }
}
