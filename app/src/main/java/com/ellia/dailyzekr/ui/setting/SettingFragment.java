package com.ellia.dailyzekr.ui.setting;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ellia.dailyzekr.R;
import com.ellia.dailyzekr.core.DailyZekrHandler;
import com.ellia.dailyzekr.core.DailyZekrImageServiceStatus;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class SettingFragment extends Fragment {

    private SettingViewModel toolsViewModel;
    private AdView mAdView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(SettingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_setting, container, false);
        final Context context = root.getContext();

        Switch switchServiceStatus = root.findViewById(R.id.switchImageService);
        switchServiceStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DailyZekrImageServiceStatus status;
                if(isChecked == true) {
                    status = DailyZekrImageServiceStatus.ON;
                } else {
                    status = DailyZekrImageServiceStatus.OFF;
                }
                DailyZekrHandler.storeDailyZekrServiceStatus(context, status);
                Log.d("ZekrService", "status: " + DailyZekrHandler.getDailyZekrServiceStatus(context));
            }
        });

        mAdView = root.findViewById(R.id.settingAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return root;
    }
}