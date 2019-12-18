package com.ellia.dailyzekr.core;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.ellia.dailyzekr.R;

import java.io.IOException;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DailyZekrHandler {

    public static int getTodayName() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return day;
    }

    public static int[] daysImages() {
        int [] images = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g};
        return images;
    }

    public static int nameOfTheWeek(){
        int dayOfTheWeek = DailyZekrHandler.getTodayName();

        switch (dayOfTheWeek){
            case Calendar.SATURDAY:
                return R.drawable.a;
            case Calendar.SUNDAY:
                 return R.drawable.b;
            case Calendar.MONDAY:
                return R.drawable.c;
            case Calendar.TUESDAY:
                return R.drawable.d;
            case Calendar.WEDNESDAY:
                return R.drawable.e;
            case Calendar.THURSDAY:
                return R.drawable.f;
            case Calendar.FRIDAY:
                return R.drawable.g;
            default:
                return -1;
        }
    }

    public static int zekrOfDay() {
        int dayOfTheWeek = DailyZekrHandler.getTodayName();

        switch (dayOfTheWeek){
            case Calendar.SATURDAY:
                return R.string.saturday;
            case Calendar.SUNDAY:
                return R.string.sunday;
            case Calendar.MONDAY:
                return R.string.monday;
            case Calendar.TUESDAY:
                return R.string.tuesday;
            case Calendar.WEDNESDAY:
                return R.string.wednesday;
            case Calendar.THURSDAY:
                return R.string.thursday;
            case Calendar.FRIDAY:
                return R.string.friday;
            default:
                return R.string.saturday;
        }
    }

    public static void storeTodayImage(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("app_config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("today_image", DailyZekrHandler.nameOfTheWeek());
        editor.commit();
    }

    public static int getTodayImage(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("app_config", Context.MODE_PRIVATE);
        return sharedPref.getInt("today_image", 0);
    }

    public static int getDailyZekrServiceStatus(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("app_config", context.MODE_PRIVATE);
        return sharedPref.getInt("daily_zekr_image_service", DailyZekrImageServiceStatus.ON.value);
    }

    public static void storeDailyZekrServiceStatus(Context context, DailyZekrImageServiceStatus status) {
        SharedPreferences sharedPref = context.getSharedPreferences("app_config", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("daily_zekr_image_service", status.value);
        editor.commit();
    }

    public static int getZekrCounterNumber(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("app_config", context.MODE_PRIVATE);
        return sharedPref.getInt("zekr_counter_number", 0);
    }

    public static void storeZekrCounterNumber(Context context, int number) {
        SharedPreferences sharedPref = context.getSharedPreferences("app_config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("zekr_counter_number", number);
        editor.commit();
    }

    public static void setTodayImage(Context context) {
        int todayImage = DailyZekrHandler.nameOfTheWeek();

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager window = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        window.getDefaultDisplay().getMetrics(metrics);

        Log.d("DailyZekrBroadCast", "trying to change imge: " + todayImage);

        if(todayImage != DailyZekrHandler.getTodayImage(context)) {
            DailyZekrHandler.storeTodayImage(context);

            Bitmap tempbitMap = BitmapFactory.decodeResource(context.getResources(), todayImage);
            Bitmap bitmap = Bitmap.createScaledBitmap(tempbitMap, metrics.widthPixels, metrics.heightPixels, true);

            WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
            wallpaperManager.setWallpaperOffsetSteps(1, 1);
            wallpaperManager.suggestDesiredDimensions(metrics.widthPixels, metrics.heightPixels);

            try {
                wallpaperManager.setBitmap(bitmap);
                Log.d("DailyZekrBroadCast", "today_image: " + todayImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void startService(Context context) {
        Intent zekrService = new Intent(context, DailyBroadcastReceiverService.class);
        context.startService(zekrService);
    }

    public static void stopService(Context context) {
        Intent zekrService = new Intent(context, DailyBroadcastReceiverService.class);
        context.stopService(zekrService);
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
