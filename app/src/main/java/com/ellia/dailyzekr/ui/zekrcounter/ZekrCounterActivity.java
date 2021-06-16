package com.ellia.dailyzekr.ui.zekrcounter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ellia.dailyzekr.R;
import com.ellia.dailyzekr.core.DailyZekrHandler;
import com.ellia.dailyzekr.handlers.ZekrsManager;
import com.ellia.dailyzekr.models.Zekr;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import org.w3c.dom.Text;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ZekrCounterActivity extends AppCompatActivity {
    private Context context;
    private int countNumber = 0;
    int button_sound_state   = 1;
    private AdView zekrAdView;
    private InterstitialAd mInterstitialAd;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zekr_counter);
        context = this.getApplicationContext();

        ZekrsManager zekrsManager = new ZekrsManager(context);
        ArrayList<Zekr> zekrs = zekrsManager.getAllZekrs();

        String position = getIntent().getStringExtra("selected_zekr_position");
        Log.d("selected_zekr_position", "value: " + position);

        int zekr_id = 0;
        if(position != null) {
            zekr_id = Integer.parseInt(position);
        }

        Zekr zekr = zekrs.get(zekr_id);

        TextView txtZekrText = (TextView) findViewById(R.id.txtZekrText);
        txtZekrText.setText(zekr.getZekr());

        TextView txtZekrTrans = (TextView) findViewById(R.id.txtZekrTrans);
        txtZekrTrans.setText(zekr.getTrans());

        countNumber = 0;
        final TextView txtZekrNumber = findViewById(R.id.txtZekrNumber);
        txtZekrNumber.setText(String.format("%04d", countNumber));

        final Button btnSound = findViewById(R.id.btnZekrSound);
        Button btnReset = findViewById(R.id.btnReset);
        Button btnCount = findViewById(R.id.btnCountUp);
        final MediaPlayer mp = MediaPlayer.create(context, R.raw.button_click);

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ZekrCounterActivity.this);
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
        correct id: ca-app-pub-3540008829614888/9797063739
        Test    id: ca-app-pub-3940256099942544/1033173712
        */
        mInterstitialAd.setAdUnitId("ca-app-pub-3540008829614888/9797063739");

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

        try {
            zekrAdView = findViewById(R.id.zekrAdView);
            AdRequest adRequest = new AdRequest.Builder().build();
            zekrAdView.loadAd(adRequest);
        }catch(Exception e) {

        }
    }
}
