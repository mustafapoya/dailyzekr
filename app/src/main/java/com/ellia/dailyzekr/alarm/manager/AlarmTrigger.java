package com.ellia.dailyzekr.alarm.manager;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.ellia.dailyzekr.alarm.manager.MyAlarmService;

import java.util.Calendar;
import java.util.Date;

public class AlarmTrigger {
    private  Context context;
    private Calendar calendar;
    public static final int REQUEST_CODE = 101;

    public AlarmTrigger(Context context){
    this.context= context;

}
    public void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence name = "Zekr";
            String description = "Change the background photo";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyZekr", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = this.context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            AlarmManager alarmManager = (AlarmManager) this.context.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(context, MyAlarmService.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, this.REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, 00);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.AM_PM, Calendar.PM);
/*
Alarm will be triggered exactly at 8:30 every day
*/
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

        }
    }}
