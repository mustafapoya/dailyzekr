package com.ellia.dailyzekr.ui.zekrs;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.ellia.dailyzekr.R;
import com.ellia.dailyzekr.ui.zekrcounter.ZekrCounterViewModel;

public class ZekrsFragment extends Fragment {
    private ZekrCounterViewModel toolsViewModel;
    private Context context  = null;

    ListView list;
    String[] maintitle = {
            "یا الله",
            "تسبیحات حضرت زهرا",
            "تسبیحات اربعه",
            "ذکر یونسیه",
            "الله اکبر",
            "سبحان اللله",
            "لا اله الا الله",
            "اللهم صل علی محمد",
            "لا حول و لا قوه الا بالله العلی العظیم"
    };

    String[] subtitle = {
            "ای خدا",
            "الله اکبر، الحمدالله، سبحان الله",
            "سبحان الله، و الحمدالله، و لا اله الا الله",
            "لا اله الا انت سبحانک انی کنت من الظالمین",
            "",
            "خدا بزرگ است",
            "منزه است خداوند",
            "خدایا درود بفرست بر محمد و آل محمد",
            "جنبش و نیرویی نیست جز به خدای والای بزرگ"
    };

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*setHasOptionsMenu(true);*/
        toolsViewModel = ViewModelProviders.of(this).get(ZekrCounterViewModel.class);
        View root = inflater.inflate(R.layout.fragment_zekrs, container, false);
        context = root.getContext();

        ListAdapterZekrCounter adapter = new ListAdapterZekrCounter(getActivity(), maintitle, subtitle);
        list = (ListView)root.findViewById(R.id.list_zekrs);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
            if(position == 0) {
                Toast.makeText(context,"Place Your First Option Code",Toast.LENGTH_SHORT).show();
                Log.d("list_click", "Item clicked");
            }
            else if(position == 1) {
                Toast.makeText(context,"Place Your Second Option Code",Toast.LENGTH_SHORT).show();
            }
            else if(position == 2) {
                Toast.makeText(context,"Place Your Third Option Code",Toast.LENGTH_SHORT).show();
            }
            else if(position == 3) {
                Toast.makeText(context,"Place Your Forth Option Code",Toast.LENGTH_SHORT).show();
            }
            else if(position == 4) {
                Toast.makeText(context,"Place Your Fifth Option Code",Toast.LENGTH_SHORT).show();
            }
            }
        });

        return root;
    }
}
