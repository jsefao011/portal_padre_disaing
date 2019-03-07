package com.consultoraestrategia.ss_crmeducativo.util;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by irvinmarin on 17/08/2017.
 */

@SuppressLint("ValidFragment")
public class SelectTimeFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    private static final String TAG = "SelectDateFragment";
    Calendar calendar;
    int diaSemana;
    OnTimeSelectClickListener clickListener;

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        clickListener.onClickHourSelect(hourOfDay, minute);
    }

    public interface OnTimeSelectClickListener {
        void onClickHourSelect(int hourOfDay, int minute);
    }

    @SuppressLint("ValidFragment")
    public SelectTimeFragment(OnTimeSelectClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        calendar = Calendar.getInstance();
        int hh = calendar.get(Calendar.HOUR);
        int mm = calendar.get(Calendar.MINUTE);
        int ss = calendar.get(Calendar.SECOND);

        diaSemana = calendar.get(Calendar.DAY_OF_WEEK);

        return new TimePickerDialog(getContext(), this, hh, mm, true);
    }


}
