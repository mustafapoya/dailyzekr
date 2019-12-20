package com.ellia.dailyzekr.ui.setting;

import android.app.ActivityManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ellia.dailyzekr.R;
import com.ellia.dailyzekr.core.DailyBroadcastReceiverService;
import com.ellia.dailyzekr.core.DailyZekrHandler;
import com.ellia.dailyzekr.core.DailyZekrImageServiceStatus;
import com.ellia.dailyzekr.ui.alram.TimePickerFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class SettingFragment extends Fragment implements View.OnClickListener {

    private SettingViewModel toolsViewModel;
    private AdView mAdView;
    private Button changeTheTime;
    private TimePickerFragment timePickerFragment;
    private TextView showTime;
    private  Switch switchServiceStatus;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(SettingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_setting, container, false);

        final Context context = root.getContext();
       initializeComponent(root);
        switchServiceStatus.setChecked(DailyZekrHandler.getDailyZekrServiceStatus(context) == DailyZekrImageServiceStatus.ON.getValue() ? true : false);
        Log.d("CheckService", "isRunning: " + isMyServiceRunning(DailyBroadcastReceiverService.class, context));

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
    public void initializeComponent(View root){
        changeTheTime = root.findViewById(R.id.changeTime);
        changeTheTime.setOnClickListener(this);
        showTime = root.findViewById(R.id.showTheTime);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyPref",0);

       int minute= sharedPreferences.getInt("minute",01);
      int hour =   sharedPreferences.getInt("hour",00);
        Toast.makeText(root.getContext(), "The time is "+ hour+" , "+ minute, Toast.LENGTH_SHORT).show();
      showTime.setText("The Background chnage time is"+ hour+" : "+ minute );
        switchServiceStatus = root.findViewById(R.id.switchImageService);

    }
public void changeTheTime(){
    timePickerFragment= new TimePickerFragment();
timePickerFragment.setCancelable(false) ;
timePickerFragment.show(getFragmentManager(),"timePicker");

}

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.changeTime:
                changeTheTime();
                break;
        }

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