package com.ellia.dailyzekr.ui.quote;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ellia.dailyzekr.R;
import com.ellia.dailyzekr.handlers.QuotesManager;
import com.ellia.dailyzekr.models.Quote;
import com.ellia.dailyzekr.ui.zekrs.ListAdapterZekrCounter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;

public class QuoteFragment extends Fragment {

    private QuoteViewModel quoteViewModel;
    private Context context;

    ListView list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        quoteViewModel = ViewModelProviders.of(this).get(QuoteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_quote, container, false);
        context = root.getContext();

        QuotesManager quotesManager = new QuotesManager(context);

        ArrayList<Quote> quotes = quotesManager.getAllQuotes();

        ListAdapterQuote adapter = new ListAdapterQuote(getActivity(), quotes);
        list = (ListView)root.findViewById(R.id.list_quotes);
        list.setAdapter(adapter);

        return root;
    }
}
