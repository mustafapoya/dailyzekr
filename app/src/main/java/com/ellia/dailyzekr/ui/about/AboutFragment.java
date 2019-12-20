package com.ellia.dailyzekr.ui.about;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ellia.dailyzekr.R;
import com.google.android.gms.ads.AdView;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutFragment extends Fragment {

    private AboutViewModel aboutViewModel;
    private AdView mAdView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        aboutViewModel =
                ViewModelProviders.of(this).get(AboutViewModel.class);
        View root = inflater.inflate(R.layout.fragment_about, container, false);
        LinearLayout layout = root.findViewById(R.id.linearLayoutAbout);

        simulateDayNight(/* DAY */ 0);

        View aboutPage = new AboutPage(root.getContext())
                .isRTL(false)
                .setDescription(getString(R.string.about_page_description))
                .addItem(new Element().setTitle("Version 1.0").setIconDrawable(R.drawable.ic_menu_setting))
                .addGroup("Connect With US")
                .addEmail("elliasoft10@gmail.com")
                .addFacebook("ellia.brothers.3")
                .addYoutube("UCzsLPS-vuKJ9ihmKjIoh9xg")
                .addPlayStore("com.ellia.dailyzekr")
                .addItem(getCopyRightsElement())
                .create();

        layout.addView(aboutPage);
        return root;
    }

    Element getCopyRightsElement() {
        Element copyRightsElement = new Element();
        final String copyrights = "CopyRights ©" + Calendar.getInstance().get(Calendar.YEAR);
        copyRightsElement.setTitle(copyrights);
        copyRightsElement.setIconTint(mehdi.sakout.aboutpage.R.color.about_item_icon_color);
        copyRightsElement.setIconNightTint(android.R.color.white);
        copyRightsElement.setGravity(Gravity.CENTER);
        copyRightsElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("AboutFragment", "onClick:yup ");            }
        });
        return copyRightsElement;
    }

    void simulateDayNight(int currentSetting) {
        final int DAY = 0;
        final int NIGHT = 1;
        final int FOLLOW_SYSTEM = 3;

        int currentNightMode = getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
        if (currentSetting == DAY && currentNightMode != Configuration.UI_MODE_NIGHT_NO) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        } else if (currentSetting == NIGHT && currentNightMode != Configuration.UI_MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else if (currentSetting == FOLLOW_SYSTEM) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }
    }
}