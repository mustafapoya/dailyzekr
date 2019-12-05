package com.ellia.dailyzekr.ui.home;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.ellia.dailyzekr.R;
import com.ellia.dailyzekr.core.DailyZekrHandler;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.time.DayOfWeek;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private AdView mAdView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);



        ImageView imgTodayZekr = root.findViewById(R.id.imgTodayZekr);
        imgTodayZekr.setImageResource(DailyZekrHandler.nameOfTheWeek());
        Log.d("ImgeViewImageToday", "image_id: " + DailyZekrHandler.nameOfTheWeek());
        int dayOfTheWeek = DailyZekrHandler.getTodayName();
        Log.d("ImgeViewImageToday", "today_name: " + dayOfTheWeek);
        Button btnSetTodayZekr = root.findViewById(R.id.btnSetTodayZekr);

        btnSetTodayZekr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DailyZekrHandler.setTodayImage(view.getContext());
            }
        });

        mAdView = root.findViewById(R.id.homeAdView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return root;
    }

}