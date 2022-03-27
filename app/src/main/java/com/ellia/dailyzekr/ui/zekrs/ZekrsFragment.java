package com.ellia.dailyzekr.ui.zekrs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.ellia.dailyzekr.R;
import com.ellia.dailyzekr.core.CalendarTool;
import com.ellia.dailyzekr.core.DailyZekrHandler;
import com.ellia.dailyzekr.handlers.DailyZekrManager;
import com.ellia.dailyzekr.handlers.UtilController;
import com.ellia.dailyzekr.handlers.ZekrsManager;
import com.ellia.dailyzekr.models.DailyZekr;
import com.ellia.dailyzekr.models.Zekr;
import com.ellia.dailyzekr.ui.zekrcounter.ZekrCounterActivity;
import com.ellia.dailyzekr.ui.zekrcounter.ZekrCounterViewModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class ZekrsFragment extends Fragment {
    private ZekrCounterViewModel toolsViewModel;
    private Context context  = null;
    private AdView zekrAdView;

    ListView list;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*setHasOptionsMenu(true);*/
        toolsViewModel = ViewModelProviders.of(this).get(ZekrCounterViewModel.class);
        View root = inflater.inflate(R.layout.fragment_zekrs, container, false);
        context = root.getContext();

        TextView txtTodayZekr = (TextView)root.findViewById(R.id.txtZekrText);
        TextView txtTodayZekrTrans = (TextView)root.findViewById(R.id.txtZekrTrans);
        TextView txtTodayName = (TextView)root.findViewById(R.id.txtTodayName);

        DailyZekrManager dailyZekrsManager = new DailyZekrManager(context);
        ArrayList<DailyZekr> dailyZekrs = dailyZekrsManager.getAllDayZekrs();

        DailyZekr todayZekr = dailyZekrs.get(UtilController.todayZekr());
        txtTodayZekr.setText(todayZekr.getZekr());
        txtTodayZekrTrans.setText(UtilController.getZekrDescription(todayZekr));
        txtTodayName.setText(UtilController.todayName(context));

        ZekrsManager zekrsManager = new ZekrsManager(context);
        ArrayList<Zekr> zekrs = zekrsManager.getAllZekrs();

        ListAdapterZekrCounter adapter = new ListAdapterZekrCounter(getActivity(), zekrs);
        list = (ListView)root.findViewById(R.id.list_zekrs);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                Intent intent = new Intent(context, ZekrCounterActivity.class);
                intent.putExtra("selected_zekr_position", String.valueOf(position));
                startActivity(intent);
            }
        });

        try {
            zekrAdView = root.findViewById(R.id.zekrAdView);
            AdRequest adRequest = new AdRequest.Builder().build();
            zekrAdView.loadAd(adRequest);
        }catch(Exception e) {
            e.printStackTrace();
        }

        return root;
    }
}
