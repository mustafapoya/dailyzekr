package com.ellia.dailyzekr.ui.quote;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ellia.dailyzekr.R;
import com.ellia.dailyzekr.models.Quote;

import java.util.ArrayList;

public class ListAdapterQuote extends ArrayAdapter<Quote> {
    private final Activity context;
    private final ArrayList<Quote> quotes;

    public ListAdapterQuote(Activity context, ArrayList<Quote> quotes) {
        super(context, R.layout.list_custom_quote, quotes);
        this.context = context;

        this.quotes = quotes;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_custom_quote, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.txtQuote);
        switch (rowView.getResources().getConfiguration().locale.getLanguage()) {
            case "fa":
                titleText.setText(quotes.get(position).quoteFarsi);
                break;
            case "tr":
                titleText.setText(quotes.get(position).quoteTurkey);
                break;
            case "hi":
                titleText.setText(quotes.get(position).quoteHindi);
                break;
            default:
                titleText.setText(quotes.get(position).quote);
                break;
        }

        TextView titleTrans = (TextView) rowView.findViewById(R.id.txtAuthor);
        titleTrans.setText(quotes.get(position).author);

        return rowView;

    };

}
