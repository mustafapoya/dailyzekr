package com.ellia.dailyzekr.ui.quote;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ellia.dailyzekr.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class QuoteFragment extends Fragment {

    private QuoteViewModel quoteViewModel;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        quoteViewModel = ViewModelProviders.of(this).get(QuoteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_quote, container, false);
        context = root.getContext();



        return root;
    }
}
