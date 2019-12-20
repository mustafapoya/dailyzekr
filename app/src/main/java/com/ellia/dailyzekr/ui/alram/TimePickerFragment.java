package com.ellia.dailyzekr.ui.alram;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.text.format.DateFormat;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;


public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

//    public interface TimePickerListener {
//        void onTimeSet(TimePicker timePicker, int hour, int minute);
//    }

//    TimePickerListener mListener;

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        try {
//            mListener = (TimePickerListener) context;
//        } catch (Exception e) {
//            throw new ClassCastException(getActivity().toString() + " must implements TimePickerListener");
//        }
//    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getContext()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Toast.makeText(getContext(),"The time is "+ hourOfDay+"  , "+ minute,Toast.LENGTH_SHORT).show();
        SharedPreferences pref = getContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("hour", hourOfDay);
        editor.putInt("minute",minute);
        editor.apply();
    }

//    @Override
//    public void onTimeSet(TimePicker timePicker, int i, int i1) {
//        mListener.onTimeSet(timePicker, i, i1);
//    }
}