package com.ellia.dailyzekr.ui.zekrcounter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ellia.dailyzekr.R;
import com.ellia.dailyzekr.core.DailyZekrHandler;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class ZekrCounterFragment extends Fragment {

    private ZekrCounterViewModel toolsViewModel;
    private int countNumber = 0;
    private Context context  = null;
    int button_sound_state   = 1;

    private InterstitialAd mInterstitialAd;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*setHasOptionsMenu(true);*/
        toolsViewModel =
                ViewModelProviders.of(this).get(ZekrCounterViewModel.class);
        View root = inflater.inflate(R.layout.fragment_zekr_counter, container, false);
        context = root.getContext();
        countNumber = DailyZekrHandler.getZekrCounterNumber(context);

        TextView txtTodayZekr        = root.findViewById(R.id.txtTodayZekr);
        final TextView txtZekrNumber = root.findViewById(R.id.txtZekrNumber);
        txtZekrNumber.setText(String.format("%04d", countNumber));

        final Button btnSound = root.findViewById(R.id.btnZekrSound);
        Button btnReset = root.findViewById(R.id.btnReset);
        Button btnCount = root.findViewById(R.id.btnCountUp);
        final MediaPlayer mp = MediaPlayer.create(context, R.raw.button_click);

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setIcon(R.drawable.daily_zekr_icon);
        alertDialogBuilder.setTitle(R.string.reset_counter);
        alertDialogBuilder.setMessage(R.string.reset_counter_message);
        alertDialogBuilder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                txtZekrNumber.setText(String.format("%04d", 0));
                countNumber = 0;
            }
        });

        alertDialogBuilder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        mInterstitialAd = new InterstitialAd(context);
        /*
        correct id: ca-app-pub-9778979220370457/5551080472
        Test    id: ca-app-pub-3940256099942544/1033173712
        */
        mInterstitialAd.setAdUnitId("ca-app-pub-9778979220370457/5551080472");

        txtTodayZekr.setText(DailyZekrHandler.zekrOfDay());

        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button_sound_state == 1) {
                    mp.start();
                }
                txtZekrNumber.setText(String.format("%04d", (++countNumber)));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
            if(DailyZekrHandler.isNetworkAvailable(context)) {
                if(mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    mInterstitialAd.setAdListener(new AdListener(){
                        @Override
                        public void onAdClosed() {
                            alertDialogBuilder.show();
                        }

                    });
                } else {
                    alertDialogBuilder.show();
                }
            } else {
                alertDialogBuilder.show();
            }
            }
        });

        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button_sound_state == 1) {
                    button_sound_state = 0;
                    btnSound.setBackgroundResource(R.drawable.btn_zekr_sound_off);
                } else {
                    button_sound_state = 1;
                    btnSound.setBackgroundResource(R.drawable.btn_zekr_sound_on);
                }
            }
        });

        return root;
    }
    @Override
    public void onPause() {
        DailyZekrHandler.storeZekrCounterNumber(getContext(), countNumber);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        DailyZekrHandler.storeZekrCounterNumber(getContext(), countNumber);
        super.onDestroy();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.zekr_counter_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_zekr_info:
                Toast.makeText(context, "Zekr Info Button Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_zekr_setting:
                Toast.makeText(context, "Zekr Setting Button Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }
}
