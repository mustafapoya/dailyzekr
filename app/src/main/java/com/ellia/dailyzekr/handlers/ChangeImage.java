package com.ellia.dailyzekr.handlers;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.ellia.dailyzekr.core.DailyZekrHandler;

import java.io.IOException;

public class ChangeImage {
    private Context context;
    private int todayImage;
    private DisplayMetrics metrics;
    private WindowManager window;
    private Bitmap tempbitMap;
    private WallpaperManager wallpaperManager;
    private Bitmap bitmap;

    public ChangeImage(Context context) {
        this.context = context;
    }

    public void change(boolean forceSet, int which) {
        todayImage = DailyZekrHandler.nameOfTheWeek();
        metrics = new DisplayMetrics();
        window = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        window.getDefaultDisplay().getMetrics(metrics);
        if (todayImage != DailyZekrHandler.getTodayImage(context) || forceSet) {
            DailyZekrHandler.storeTodayImage(context);
            tempbitMap = BitmapFactory.decodeResource(context.getResources(), todayImage);
            bitmap = Bitmap.createScaledBitmap(tempbitMap, metrics.widthPixels, metrics.heightPixels, true);

            wallpaperManager = WallpaperManager.getInstance(context);
            wallpaperManager.setWallpaperOffsetSteps(1, 1);
            wallpaperManager.suggestDesiredDimensions(metrics.widthPixels, metrics.heightPixels);
            try {
                switch (which) {
                    case 0:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_SYSTEM);
                        else wallpaperManager.setBitmap(bitmap);
                        break;
                    case 1:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_LOCK);
                        else wallpaperManager.setBitmap(bitmap);
                        break;
                    case 2:
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            wallpaperManager.setBitmap(bitmap);
                        }
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
