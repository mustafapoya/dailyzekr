package com.ellia.dailyzekr.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.ellia.dailyzekr.R;
import com.ellia.dailyzekr.core.DailyZekrHandler;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private AdView mAdView;
    private Context context;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        context = root.getContext();

        RelativeLayout relativeLayout= root.findViewById(R.id.homeLayout);
        relativeLayout.setBackgroundResource(DailyZekrHandler.nameOfTheWeek());
        Log.d("ImgeViewImageToday", "image_id: " + DailyZekrHandler.nameOfTheWeek());
        int dayOfTheWeek = DailyZekrHandler.getTodayName();
        Log.d("ImgeViewImageToday", "today_name: " + dayOfTheWeek);
        Button btnSetTodayZekr = root.findViewById(R.id.btnSetTodayZekr);

        btnSetTodayZekr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ButtonImage", "trying to change image");
                DailyZekrHandler.setTodayImage(context, true);
            }
        });

        mAdView = root.findViewById(R.id.homeAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return root;
    }

}