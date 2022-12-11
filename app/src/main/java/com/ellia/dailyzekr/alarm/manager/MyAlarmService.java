package com.ellia.dailyzekr.alarm.manager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;
import android.util.Log;

import com.ellia.dailyzekr.MainActivity;
import com.ellia.dailyzekr.R;
import com.ellia.dailyzekr.core.DailyZekrHandler;
import com.ellia.dailyzekr.handlers.QuotesManager;
import com.ellia.dailyzekr.handlers.SharePreferences;
import com.ellia.dailyzekr.models.Quote;

import androidx.core.app.NotificationCompat;

public class MyAlarmService extends BroadcastReceiver {

    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;
    public static final String NOTIFICATION_CHANNEL_ID = "10001";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        Log.d("MyAlarmService", "onReceive:is called ");
        createNotification(context);
        DailyZekrHandler.setTodayImage(context, false);
        // an Intent broadcast.
    }

    /**
     * Create and push the notification
     */
    public void createNotification(Context mContext) {
        /**Creates an explicit intent for an Activity in your app**/
        Intent resultIntent = new Intent(mContext , MainActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(mContext,
                0 /* Request code */, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        Quote quotes = new QuotesManager(mContext).getRandomQuote();

        String quoteText = "";

        switch (mContext.getResources().getConfiguration().locale.getLanguage()) {
            case "fa":
                quoteText  = quotes.getQuoteFarsi();
                break;
            case "tr":
                quoteText  = quotes.getQuoteTurkey();
                break;
            case "hi":
                quoteText  = quotes.getQuoteHindi();
                break;
            default:
                quoteText  = quotes.getQuote();
                break;
        }

        mBuilder = new NotificationCompat.Builder(mContext,NOTIFICATION_CHANNEL_ID);
        mBuilder.setSmallIcon(R.drawable.app_logo);
        mBuilder.setContentTitle(quotes.getAuthor())
                .setContentText(quoteText)
                .setAutoCancel(false)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(quoteText))
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setContentIntent(resultPendingIntent);

        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.CYAN);
            notificationChannel.enableVibration(true);

            assert mNotificationManager != null;
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            mBuilder.setSmallIcon(R.drawable.app_logo);
            mBuilder.setContentTitle(quotes.getAuthor());
            mBuilder.setContentText(quoteText);
            mBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(quoteText));
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        assert mNotificationManager != null;

        if(SharePreferences.getSharedPreferenceObject(mContext).getNotificationStatus()==1) {
            mNotificationManager.notify(0 /* Request Code */, mBuilder.build());
        }
    }
}
