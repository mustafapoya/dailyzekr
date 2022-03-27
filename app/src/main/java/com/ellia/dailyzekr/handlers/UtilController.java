package com.ellia.dailyzekr.handlers;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.ellia.dailyzekr.R;
import com.ellia.dailyzekr.core.DailyZekrHandler;
import com.ellia.dailyzekr.models.DailyZekr;
import com.ellia.dailyzekr.models.Zekr;

import java.util.Calendar;
import java.util.Locale;

public class UtilController {

    public static String todayName(Context context) {
        int dayOfTheWeek = DailyZekrHandler.getTodayName();

        switch (dayOfTheWeek) {
            case Calendar.SATURDAY:
                return context.getString(R.string.day_saturday);
            case Calendar.SUNDAY:
                return context.getString(R.string.day_sunday);
            case Calendar.MONDAY:
                return context.getString(R.string.day_monday);
            case Calendar.TUESDAY:
                return context.getString(R.string.day_tuesday);
            case Calendar.WEDNESDAY:
                return context.getString(R.string.day_wednesday);
            case Calendar.THURSDAY:
                return context.getString(R.string.day_thursday);
            case Calendar.FRIDAY:
                return context.getString(R.string.day_friday);
            default:
                return "";
        }
    }

    public static String getZekrDescription(DailyZekr todayZekr) {
        String language = Locale.getDefault().getLanguage();

        switch (language) {
            case "en":
                return todayZekr.getEnglish();
            case "hi":
                return todayZekr.getHindi();
            case "tr":
                return todayZekr.getTurkey();
            default:
                return todayZekr.getFarsi();
        }
    }

    public static int todayZekr() {
        int dayOfTheWeek = DailyZekrHandler.getTodayName();

        switch (dayOfTheWeek) {
            case Calendar.SATURDAY:
                return 0;
            case Calendar.SUNDAY:
                return 1;
            case Calendar.MONDAY:
                return 2;
            case Calendar.TUESDAY:
                return 3;
            case Calendar.WEDNESDAY:
                return 4;
            case Calendar.THURSDAY:
                return 5;
            case Calendar.FRIDAY:
                return 6;
            default:
                return 0;
        }
    }

    public static String getZekrTrans(Zekr zekr) {
        String language = Locale.getDefault().getLanguage();

        switch (language) {
            case "en":
                return zekr.getTransEnglish();
            case "hi":
                return zekr.getTransHindi();
            case "tr":
                return zekr.getTransTurkey();
            default:
                return zekr.getTransFarsi();
        }
    }

    public static void setLocale(Context context, String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }
}
