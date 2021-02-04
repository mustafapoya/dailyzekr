package com.ellia.dailyzekr.ui.zekrs;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ellia.dailyzekr.R;

public class ListAdapterZekrCounter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] maintitle;
    private final String[] subtitle;

    public ListAdapterZekrCounter(Activity context, String[] maintitle, String[] subtitle) {
        super(context, R.layout.list_custom_zekr, maintitle);

        this.context = context;
        this.maintitle = maintitle;
        this.subtitle = subtitle;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_custom_zekr, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.txtTitle);
        titleText.setText(maintitle[position]);

        TextView titleTrans = (TextView) rowView.findViewById(R.id.txtTitleTrans);
        titleTrans.setText(maintitle[position]);

        return rowView;

    };

}
