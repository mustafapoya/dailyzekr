package com.ellia.dailyzekr.ui.setting;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.ellia.dailyzekr.R;
import com.ellia.dailyzekr.core.DailyBroadcastReceiverService;
import com.ellia.dailyzekr.core.DailyZekrHandler;
import com.ellia.dailyzekr.core.DailyZekrImageServiceStatus;
import com.ellia.dailyzekr.handlers.SharePreferences;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class SettingFragment extends Fragment {

    private SettingViewModel settingViewModel;
    private AdView mAdView;
    private Switch switchServiceStatus;
    private Switch switchNotification;

    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        settingViewModel =
                ViewModelProviders.of(this).get(SettingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_setting, container, false);

        final Context context = root.getContext();
        switchServiceStatus = root.findViewById(R.id.switchImageService);
        switchNotification = root.findViewById(R.id.swithDisableNotification);

        switchServiceStatus.setChecked(DailyZekrHandler.getDailyZekrServiceStatus(context) == DailyZekrImageServiceStatus.ON.getValue() ? true : false);
        Log.d("CheckService", "isRunning: " + isMyServiceRunning(DailyBroadcastReceiverService.class, context));

        switchNotification.setChecked(SharePreferences.getSharedPreferenceObject(context).getNotificationStatus()==1?true: false);

        switchNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true) {
                    SharePreferences.getSharedPreferenceObject(context).setNotificationStatus(1);
                    Toast.makeText(context, R.string.notification_enabled, Toast.LENGTH_SHORT).show();
                } else {
                    SharePreferences.getSharedPreferenceObject(context).setNotificationStatus(0);
                    Toast.makeText(context, R.string.notification_disabled, Toast.LENGTH_SHORT).show();
                }
            }
        });

        switchServiceStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DailyZekrImageServiceStatus status;
                if(isChecked == true) {
                    status = DailyZekrImageServiceStatus.ON;
                    DailyZekrHandler.startService(context);
                    Toast.makeText(context, R.string.back_img_service_started, Toast.LENGTH_SHORT).show();
                } else {
                    status = DailyZekrImageServiceStatus.OFF;
                    DailyZekrHandler.stopService(context);
                    Toast.makeText(context, R.string.back_img_service_stopped, Toast.LENGTH_SHORT).show();
                }
                DailyZekrHandler.storeDailyZekrServiceStatus(context, status);
            }
        });

        mAdView = root.findViewById(R.id.settingAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return root;
    }

    public boolean isMyServiceRunning(Class<?> serviceClass, Context context) {
        ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo service : activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if(serviceClass.getName().equals(service.service.getClassName())){
                return true;
            }
        }
        return true;
    }
}