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
import com.ellia.dailyzekr.handlers.QuotesManager;
import com.ellia.dailyzekr.models.Quotes;

public class MyAlarmService extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        Log.d("MyAlarmService", "onReceive:is called ");
        Quotes quotes= new QuotesManager(context).getQuotes();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"notifyZekr").
                setSmallIcon(R.drawable.ellia_logo)
                .setContentTitle(quotes.getAuthor())
                .setContentText((quotes.getQuote())).
                setPriority(NotificationCompat.PRIORITY_HIGH);
        NotificationManagerCompat notificationManagerCompat= NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(200,builder.build());
        DailyZekrHandler.setTodayImage(context);

        // an Intent broadcast.
    }
}
