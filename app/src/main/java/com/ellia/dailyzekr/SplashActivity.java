package com.ellia.dailyzekr;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ellia.dailyzekr.MainActivity;
import com.ellia.dailyzekr.R;

import java.util.Locale;

public class SplashActivity extends AppCompatActivity {
    boolean isActive = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread splashThread = new Thread(){
            @Override
            public void run() {
                int waitTime = 0;

                while(waitTime <= 3 && isActive) {
                    try{
                        sleep(1000);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    waitTime += 1;
                }
                isActive = false;
                finish();
                startActivity(new Intent(getBaseContext(), MainActivity.class));
            }
        };
        splashThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            isActive = false;
        }
        return true;
    }
}
