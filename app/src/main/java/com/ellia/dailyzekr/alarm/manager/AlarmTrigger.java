package com.ellia.dailyzekr.alarm.manager;

import android.app.AlarmManager;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;


import com.ellia.dailyzekr.handlers.SharePreferences;

import java.util.Calendar;


public class AlarmTrigger {
    private  Context context;
    private Calendar calendar;
    private  AlarmManager alarMgr;
    private PendingIntent alarmIntent;
    private static final String TAG = "ALARMTRIGGER";

    public AlarmTrigger(Context context){
        this.context= context;
    }

    public void createNotificationChannel() {
        Log.d("VersionOFTHE", "onCreate: The android version is greater than O");
        alarMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, MyAlarmService.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 00);
        calendar.set(Calendar.MINUTE, 01);

        Log.d(TAG, "createNotificationChannel: "+ calendar.getTimeInMillis());
        Log.d("AlarmTrigger", "createNotificationChannel: The alarm is "+calendar.getTimeInMillis());
        alarMgr.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),1000 * 60 * 20, alarmIntent);
    }

}
