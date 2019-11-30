package com.ellia.dailyzekr.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ellia.dailyzekr.R;
import com.ellia.dailyzekr.core.DailyZekrHandler;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ImageView imgTodayZekr = root.findViewById(R.id.imgTodayZekr);
        imgTodayZekr.setImageResource(DailyZekrHandler.nameOfTheWeek());
        Button btnSetTodayZekr = root.findViewById(R.id.btnSetTodayZekr);

        btnSetTodayZekr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DailyZekrHandler.setTodayImage(view.getContext());
                Toast.makeText(view.getContext(), "clicked", Toast.LENGTH_LONG).show();
            }
        });

        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}