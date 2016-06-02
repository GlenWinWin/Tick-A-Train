package com.glenwin.tick_a_train;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Glenwin18 on 9/22/2015.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class SearchFragment extends Fragment {

    public SearchFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_pages, container, false);

        final DatePicker datePicker = (DatePicker) rootView.findViewById(R.id.datePicker);
        final TimePicker timePicker = (TimePicker) rootView.findViewById(R.id.timePicker);
        Button searchBtn = (Button) rootView.findViewById(R.id.btnSearch);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String month = "" + (datePicker.getMonth() + 1);
                String day = "" + datePicker.getDayOfMonth();
                String year = "" + datePicker.getYear();
                String am_pm = null;
                String ex = "";

                int time_hour_daw = timePicker.getCurrentHour();
                int time_minute_daw = timePicker.getCurrentMinute();
                if(time_hour_daw > 12){
                    int temp = time_hour_daw-12;
                        am_pm = "pm";
                        ex = "" + temp;
                }
                else if(time_hour_daw == 12){
                    am_pm = "pm";
                    ex = "" + time_hour_daw;
                }
                else if(time_hour_daw > 0){
                    am_pm = "am";
                    ex = "" + time_hour_daw;
                }
                else if(time_hour_daw == 0){
                    int temp = time_hour_daw+12;
                    am_pm = "am";
                    ex = "" + temp;
                }
                Toast.makeText(getActivity(),month + "/" + day + "/" + year + "\n" + ex +":" +time_minute_daw + " " +am_pm.toUpperCase(),Toast.LENGTH_SHORT).show();
            }
        });


        return rootView;
    }
}

