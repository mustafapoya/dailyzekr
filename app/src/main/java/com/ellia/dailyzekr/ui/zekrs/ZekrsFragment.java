package com.ellia.dailyzekr.ui.zekrs;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.ellia.dailyzekr.R;
import com.ellia.dailyzekr.handlers.QuotesManager;
import com.ellia.dailyzekr.handlers.ZekrsManager;
import com.ellia.dailyzekr.models.Quote;
import com.ellia.dailyzekr.models.Zekr;
import com.ellia.dailyzekr.ui.zekrcounter.ZekrCounterViewModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

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

        ZekrsManager zekrsManager = new ZekrsManager(context);
        ArrayList<Zekr> zekrs = zekrsManager.getAllZekrs();

        ListAdapterZekrCounter adapter = new ListAdapterZekrCounter(getActivity(), zekrs);
        list = (ListView)root.findViewById(R.id.list_zekrs);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                if(position == 0) {
                    Toast.makeText(context,"Place Your First Option Code", Toast.LENGTH_SHORT).show();
                    Log.d("list_click", "Item clicked");
                }
            }
        });

        try {
            zekrAdView = root.findViewById(R.id.zekrAdView);
            AdRequest adRequest = new AdRequest.Builder().build();
            zekrAdView.loadAd(adRequest);
        }catch(Exception e) {

        }

        return root;
    }
}
