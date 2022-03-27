package com.ellia.dailyzekr.ui.zekrs;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ellia.dailyzekr.R;
import com.ellia.dailyzekr.handlers.UtilController;
import com.ellia.dailyzekr.models.Quote;
import com.ellia.dailyzekr.models.Zekr;

import java.util.ArrayList;

public class ListAdapterZekrCounter extends ArrayAdapter<Zekr> {
    private final Activity context;
    private final ArrayList<Zekr> zekrs;

    public ListAdapterZekrCounter(Activity context, ArrayList<Zekr> zekrs) {
        super(context, R.layout.list_custom_zekr, zekrs);

        this.context = context;
        this.zekrs = zekrs;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_custom_zekr, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.txtTitle);
        titleText.setText(zekrs.get(position).getZekr());

        TextView titleTrans = (TextView) rowView.findViewById(R.id.txtTitleTrans);
        titleTrans.setText(UtilController.getZekrTrans(zekrs.get(position)));

        return rowView;

    };

}
