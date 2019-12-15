package com.ellia.dailyzekr.alarm.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.ellia.dailyzekr.MainActivity;
import com.ellia.dailyzekr.R;
import com.ellia.dailyzekr.core.DailyZekrHandler;

public class MyAlarmService extends BroadcastReceiver {
    private MainActivity mainActivity;
    private AlarmTrigger alarmTrigger;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        Log.d("MyAlarmService", "onReceive:is called ");
        mainActivity = new MainActivity();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"notifyZekr").
                setSmallIcon(R.drawable.ellia_logo)
                .setContentTitle("Hazrat Ali")
                .setContentText(("Patience Ensure victory")).
                setPriority(NotificationCompat.PRIORITY_HIGH);
        NotificationManagerCompat notificationManagerCompat= NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(200,builder.build());
        DailyZekrHandler.setTodayImage(context);
this.alarmTrigger.createNotificationChannel();

        // an Intent broadcast.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
