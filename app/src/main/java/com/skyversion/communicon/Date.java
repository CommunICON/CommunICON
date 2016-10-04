package com.skyversion.communicon;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.TimePicker;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ss on 2016-08-14.
 */
public class Date extends Activity implements OnDateSelectedListener{
    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();
    private MaterialCalendarView calendarView;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_activity);

        calendarView = (MaterialCalendarView)findViewById(R.id.calendarView);
        tv = (TextView)findViewById(R.id.textView);

        Calendar calendar = Calendar.getInstance();
        calendarView.setSelectedDate(calendar.getTime());
        // 현재날짜 자동선택

        calendarView.setOnDateChangedListener(this);
        tv.setText(getSelectedDatesString());

        TimePicker timePicker = (TimePicker)findViewById(R.id.timePicker);
        
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @Nullable CalendarDay date, boolean selected) {
        tv.setText(getSelectedDatesString());
    }

    private String getSelectedDatesString() {
        CalendarDay date = calendarView.getSelectedDate();
        if (date == null) {
            return "No Selection";
        }
        return FORMATTER.format(date.getDate());
    }
}