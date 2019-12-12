package com.ellia.dailyzekr.ui.zekrcounter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ellia.dailyzekr.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class ZekrCounterFragment extends Fragment {

    private ZekrCounterViewModel toolsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ZekrCounterViewModel.class);

        View root = inflater.inflate(R.layout.fragment_zekr_counter, container, false);
        TextView todayZekr = (TextView) root.findViewById(R.id.txtTodayZekr);
        todayZekr.setText(R.string.friday);
        return root;
    }
}
