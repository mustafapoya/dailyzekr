package com.ellia.dailyzekr.core;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.ellia.dailyzekr.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DailyZekrHandler {

    public static String getTodayName() {
        return new SimpleDateFormat("EEEE").format(new Date());
    }

    public static int[] daysImages() {
        int [] images = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g};
        return images;
    }

    public static int nameOfTheWeek(){
        String dayOfTheWeek = DailyZekrHandler.getTodayName();
        switch (dayOfTheWeek){
            case "Saturday":
                return R.drawable.a;
            case "Sunday":
                 return R.drawable.b;
            case "Monday":
                return R.drawable.c;
            case "Tuesday":
                return R.drawable.d;
            case "Wednesday":
                return R.drawable.e;
            case "Thursday":
                return R.drawable.f;
            case "Friday":
                return R.drawable.g;
            default:
                return 0;
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

    public static void setTodayImage(Context context) {
        int todayImage = DailyZekrHandler.nameOfTheWeek();

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager window = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        window.getDefaultDisplay().getMetrics(metrics);

        if(todayImage != DailyZekrHandler.getTodayImage(context)) {
            DailyZekrHandler.storeTodayImage(context);

            Bitmap tempbitMap = BitmapFactory.decodeResource(context.getResources(), todayImage);
            Bitmap bitmap = Bitmap.createScaledBitmap(tempbitMap, metrics.widthPixels, metrics.heightPixels, true);

            WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
            wallpaperManager.setWallpaperOffsetSteps(1, 1);
            wallpaperManager.suggestDesiredDimensions(metrics.widthPixels, metrics.heightPixels);

            try {
                wallpaperManager.setBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
