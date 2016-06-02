package com.glenwin.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.glenwin.host.ReturnHost;
import com.glenwin.tick_a_train.AddSchedule;
import com.glenwin.tick_a_train.LoginActivity;
import com.glenwin.tick_a_train.R;
import com.glenwin.tick_a_train.ShowProgressLane;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter{
    // Declare Variables
    Context context;
    String[] rank;
    String[] transportation;
    int[] pictures_trans;
    LayoutInflater inflater;
    TextView tvTransportation;
    ImageView imgvehicle;

    public ViewPagerAdapter(Context context,String[] rank,
                            String[] transportation, int[] pictures_trans) {
        this.context = context;
        this.rank = rank;
        this.transportation = transportation;
        this.pictures_trans = pictures_trans;
    }

    @Override
    public int getCount() {
        return rank.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item, container,
                false);

        tvTransportation = (TextView) itemView.findViewById(R.id.tvtranspo);

        tvTransportation.setText(transportation[position]);

        // Locate the ImageView in viewpager_item.xml
        imgvehicle = (ImageView) itemView.findViewById(R.id.ivPictures);
        // Capture position and set to the ImageView
        imgvehicle.setImageResource(pictures_trans[position]);
        imgvehicle.setTag(pictures_trans[position]);
        imgvehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (getDrawableId((ImageView)v)) {
                    case R.drawable.lrt1:
                        dialogLRT1();
                        break;
                    case R.drawable.lrt2:
                        dialogLRT2();
                        break;
                    case R.drawable.mrt3:
                        dialogMRT3();
                        break;
                }
            }
        });
        // Add viewpager_item.xml to ViewPager
        ((ViewPager) container).addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((RelativeLayout) object);

    }
    private void dialogLRT1(){
        SharedPreferences preferences = this.context.getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        final String finalEmail = preferences.getString("email_key", "");
        String temp = preferences.getString("ticket_load", "");
        Integer load_ticket = Integer.parseInt(temp);
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.sched_lrt1);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        Button btnReserveSched = (Button)dialog.getWindow().findViewById(R.id.btnReserveSched);
        Button btn_close = (Button)dialog.getWindow().findViewById(R.id.buttonClose);
        Button btnBookSched = (Button)dialog.getWindow().findViewById(R.id.btnBookSched);
        RadioButton Lane1 = (RadioButton)dialog.getWindow().findViewById(R.id.Lane1);
        RadioButton Lane2 = (RadioButton)dialog.getWindow().findViewById(R.id.Lane2);
        RadioButton Lane3 = (RadioButton)dialog.getWindow().findViewById(R.id.Lane3);
        RadioButton Lane4 = (RadioButton)dialog.getWindow().findViewById(R.id.Lane4);
        Lane1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,ShowProgressLane.class);
                i.putExtra("lane","Lane 1");
                context.startActivity(i);
            }
        });
        Lane2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,ShowProgressLane.class);
                i.putExtra("lane","Lane 2");
                context.startActivity(i);
            }
        });
        Lane3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,ShowProgressLane.class);
                i.putExtra("lane","Lane 3");
                context.startActivity(i);
            }
        });
        Lane4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,ShowProgressLane.class);
                i.putExtra("lane","Lane 4");
                context.startActivity(i);
            }
        });
        final Spinner spinFromStation1 = (Spinner)dialog.getWindow().findViewById(R.id.spinFromStation1);
        final Spinner spinToStation1 = (Spinner)dialog.getWindow().findViewById(R.id.spinToStation1);
        final DatePicker datePicker = (DatePicker)dialog.getWindow().findViewById(R.id.datePicker);
        final TimePicker timePicker = (TimePicker)dialog.getWindow().findViewById(R.id.timePicker);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        btnBookSched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setTitle("Confirmation for Booking a Schedule");
                builder1.setMessage("Are you sure?");
                builder1.setIcon(android.R.drawable.ic_menu_info_details);
                builder1.setCancelable(true);
                builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog2, int which) {
                        String month = "" + (datePicker.getMonth() + 1);
                        String day = "" + datePicker.getDayOfMonth();
                        String year = "" + datePicker.getYear();
                        String from = String.valueOf(spinFromStation1.getSelectedItem());
                        String to = String.valueOf(spinToStation1.getSelectedItem());
                        RadioGroup grp = (RadioGroup) dialog.getWindow().findViewById(R.id.radioLanes);
                        int selected = grp.getCheckedRadioButtonId();
                        String selected_lane;
                        String temp = String.valueOf(selected);
                        if (temp.endsWith("1")) {
                            selected_lane = "1";
                        }
                        else if (temp.endsWith("2")) {
                            selected_lane = "2";
                        }
                        else if (temp.endsWith("3")) {
                            selected_lane = "3";
                        }
                        else{
                            selected_lane = "4";
                        }
                        String am_pm="",ex="";
                        int time_hour_daw = timePicker.getCurrentHour();
                        int time_minute_daw = timePicker.getCurrentMinute();
                        if(time_hour_daw > 12){
                            int temp2 = time_hour_daw-12;
                            am_pm = "pm";
                            ex = "" + temp2;
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
                            int temp2 = time_hour_daw+12;
                            am_pm = "am";
                            ex = "" + temp2;
                        }
                        Intent i = new Intent(context, AddSchedule.class);
                        i.putExtra("dateYear",year);
                        i.putExtra("dateMonth",month);
                        i.putExtra("dateDay",day);
                        i.putExtra("timehour",ex);
                        i.putExtra("timeminute",String.valueOf(time_minute_daw));
                        i.putExtra("timeAMPM",am_pm.toUpperCase());
                        i.putExtra("to",to);
                        i.putExtra("from",from);
                        i.putExtra("email",finalEmail);
                        i.putExtra("what_lrt","1");
                        i.putExtra("lane", selected_lane);
                        context.startActivity(i);

                    }
                });
                builder1.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
        btnReserveSched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setTitle("Confirmation for Reserving a Schedule");
                builder1.setMessage("Are you sure?");
                builder1.setIcon(android.R.drawable.ic_menu_info_details);
                builder1.setCancelable(true);
                builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog2, int which) {
                        String month = "" + (datePicker.getMonth() + 1);
                        String day = "" + datePicker.getDayOfMonth();
                        String year = "" + datePicker.getYear();
                        String from = String.valueOf(spinFromStation1.getSelectedItem());
                        String to = String.valueOf(spinToStation1.getSelectedItem());
                        RadioGroup grp = (RadioGroup) dialog.getWindow().findViewById(R.id.radioLanes);
                        int selected = grp.getCheckedRadioButtonId();
                        String selected_lane;
                        String temp = String.valueOf(selected);
                        if (temp.endsWith("1")) {
                            selected_lane = "Lane 1";
                        }
                        else if (temp.endsWith("2")) {
                            selected_lane = "Lane 2";
                        }
                        else if (temp.endsWith("3")) {
                            selected_lane = "Lane 3";
                        }
                        else{
                            selected_lane = "Lane 4";
                        }
                        String am_pm="",ex="";
                        int time_hour_daw = timePicker.getCurrentHour();
                        int time_minute_daw = timePicker.getCurrentMinute();
                        if(time_hour_daw > 12){
                            int temp2 = time_hour_daw-12;
                            am_pm = "pm";
                            ex = "" + temp2;
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
                            int temp2 = time_hour_daw+12;
                            am_pm = "am";
                            ex = "" + temp2;
                        }
//                        String thisisTime = ex +":" +time_minute_daw + " " +am_pm.toUpperCase();
//                        Intent i = new Intent(context, AddSchedule.class);
//                        i.putExtra("date",year + "-" + month + "-" + day);
//                        i.putExtra("time",thisisTime);
//                        i.putExtra("to",to);
//                        i.putExtra("from",from);
//                        i.putExtra("email",finalEmail);
//                        i.putExtra("what_lrt","LRT 1");
//                        i.putExtra("lane",selected_lane);
//                        context.startActivity(i);
                        Toast.makeText(context,month +"-"+day+"-"+year+"\n"+ex +":" +time_minute_daw + " " +am_pm.toUpperCase() +"\n" +to+"\n"+from+"\n"+selected_lane,Toast.LENGTH_LONG).show();

                    }
                });
                builder1.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
    }
    private void dialogLRT2(){
        SharedPreferences preferences = this.context.getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        final String finalEmail = preferences.getString("email_key", "");
        String temp = preferences.getString("ticket_load","");
        Integer load_ticket = Integer.parseInt(temp);
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.sched_lrt2);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        Button btnReserveSched = (Button)dialog.getWindow().findViewById(R.id.btnReserveSched);
        Button btn_close = (Button)dialog.getWindow().findViewById(R.id.buttonClose);
        Button btnBookSched = (Button)dialog.getWindow().findViewById(R.id.btnBookSched);
        final Spinner spinFromStation2 = (Spinner)dialog.getWindow().findViewById(R.id.spinFromStation2);
        final Spinner spinToStation2 = (Spinner)dialog.getWindow().findViewById(R.id.spinToStation2);
        final DatePicker datePicker = (DatePicker)dialog.getWindow().findViewById(R.id.datePicker);
        final TimePicker timePicker = (TimePicker)dialog.getWindow().findViewById(R.id.timePicker);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        btnBookSched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setTitle("Confirmation for Booking a Schedule");
                builder1.setMessage("Are you sure?");
                builder1.setIcon(android.R.drawable.ic_menu_info_details);
                builder1.setCancelable(true);
                builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog2, int which) {
                        String month = "" + (datePicker.getMonth() + 1);
                        String day = "" + datePicker.getDayOfMonth();
                        String year = "" + datePicker.getYear();
                        String from = String.valueOf(spinFromStation2.getSelectedItem());
                        String to = String.valueOf(spinToStation2.getSelectedItem());
                        RadioGroup grp = (RadioGroup) dialog.getWindow().findViewById(R.id.radioLanes);
                        int selected = grp.getCheckedRadioButtonId();
                        String selected_lane;
                        String temp = String.valueOf(selected);
                        if (temp.endsWith("1")) {
                            selected_lane = "Lane 1";
                        }
                        else if (temp.endsWith("2")) {
                            selected_lane = "Lane 2";
                        }
                        else if (temp.endsWith("3")) {
                            selected_lane = "Lane 3";
                        }
                        else{
                            selected_lane = "Lane 4";
                        }
                        String am_pm="",ex="";
                        int time_hour_daw = timePicker.getCurrentHour();
                        int time_minute_daw = timePicker.getCurrentMinute();
                        if(time_hour_daw > 12){
                            int temp2 = time_hour_daw-12;
                            am_pm = "pm";
                            ex = "" + temp2;
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
                            int temp2 = time_hour_daw+12;
                            am_pm = "am";
                            ex = "" + temp2;
                        }
//                        Intent i = new Intent(context, AddSchedule.class);
//                        i.putExtra("date",year + "-" + month + "-" + day);
//                        i.putExtra("time",ex +":" +time_minute_daw + " " +am_pm.toUpperCase());
//                        i.putExtra("to",to);
//                        i.putExtra("from",from);
//                        i.putExtra("email",finalEmail);
//                        i.putExtra("what_lrt","LRT 2");
//                        i.putExtra("lane",selected_lane);
//                        context.startActivity(i);
                        Toast.makeText(context,month +"-"+day+"-"+year+"\n"+ex +":" +time_minute_daw + " " +am_pm.toUpperCase() +"\n" +to+"\n"+from+"\n"+selected_lane,Toast.LENGTH_LONG).show();

                    }
                });
                builder1.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
        btnReserveSched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setTitle("Confirmation for Reserving a Schedule");
                builder1.setMessage("Are you sure?");
                builder1.setIcon(android.R.drawable.ic_menu_info_details);
                builder1.setCancelable(true);
                builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog2, int which) {
                        String month = "" + (datePicker.getMonth() + 1);
                        String day = "" + datePicker.getDayOfMonth();
                        String year = "" + datePicker.getYear();
                        String from = String.valueOf(spinFromStation2.getSelectedItem());
                        String to = String.valueOf(spinToStation2.getSelectedItem());
                        RadioGroup grp = (RadioGroup) dialog.getWindow().findViewById(R.id.radioLanes);
                        int selected = grp.getCheckedRadioButtonId();
                        String selected_lane;
                        String temp = String.valueOf(selected);
                        if (temp.endsWith("1")) {
                            selected_lane = "Lane 1";
                        }
                        else if (temp.endsWith("2")) {
                            selected_lane = "Lane 2";
                        }
                        else if (temp.endsWith("3")) {
                            selected_lane = "Lane 3";
                        }
                        else{
                            selected_lane = "Lane 4";
                        }
                        String am_pm="",ex="";
                        int time_hour_daw = timePicker.getCurrentHour();
                        int time_minute_daw = timePicker.getCurrentMinute();
                        if(time_hour_daw > 12){
                            int temp2 = time_hour_daw-12;
                            am_pm = "pm";
                            ex = "" + temp2;
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
                            int temp2 = time_hour_daw+12;
                            am_pm = "am";
                            ex = "" + temp2;
                        }
//                        Intent i = new Intent(context, AddSchedule.class);
//                        i.putExtra("date",year + "-" + month + "-" + day);
//                        i.putExtra("time",ex +":" +time_minute_daw + " " +am_pm.toUpperCase());
//                        i.putExtra("to",to);
//                        i.putExtra("from",from);
//                        i.putExtra("email",finalEmail);
//                        i.putExtra("what_lrt","LRT 2");
//                        i.putExtra("lane",selected_lane);
//                        context.startActivity(i);
                        Toast.makeText(context,month +"-"+day+"-"+year+"\n"+ex +":" +time_minute_daw + " " +am_pm.toUpperCase() +"\n" +to+"\n"+from+"\n"+selected_lane,Toast.LENGTH_LONG).show();

                    }
                });
                builder1.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
    }
    private void dialogMRT3(){
        SharedPreferences preferences = this.context.getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        final String finalEmail = preferences.getString("email_key", "");
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.sched_mrt3);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        Button btnReserveSched = (Button)dialog.getWindow().findViewById(R.id.btnReserveSched);
        Button btn_close = (Button)dialog.getWindow().findViewById(R.id.buttonClose);
        Button btnBookSched = (Button)dialog.getWindow().findViewById(R.id.btnBookSched);
        final Spinner spinFromStation3 = (Spinner)dialog.getWindow().findViewById(R.id.spinFromStation3);
        final Spinner spinToStation3 = (Spinner)dialog.getWindow().findViewById(R.id.spinToStation3);
        final DatePicker datePicker = (DatePicker)dialog.getWindow().findViewById(R.id.datePicker);
        final TimePicker timePicker = (TimePicker)dialog.getWindow().findViewById(R.id.timePicker);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        btnReserveSched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setTitle("Confirmation for Reserving a Schedule");
                builder1.setMessage("Are you sure?");
                builder1.setIcon(android.R.drawable.ic_menu_info_details);
                builder1.setCancelable(true);
                builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog2, int which) {
                        String month = "" + (datePicker.getMonth() + 1);
                        String day = "" + datePicker.getDayOfMonth();
                        String year = "" + datePicker.getYear();
                        String from = String.valueOf(spinFromStation3.getSelectedItem());
                        String to = String.valueOf(spinToStation3.getSelectedItem());
                        RadioGroup grp = (RadioGroup) dialog.getWindow().findViewById(R.id.radioLanes);
                        int selected = grp.getCheckedRadioButtonId();
                        String selected_lane;
                        String temp = String.valueOf(selected);
                        if (temp.endsWith("1")) {
                            selected_lane = "Lane 1";
                        }
                        else if (temp.endsWith("2")) {
                            selected_lane = "Lane 2";
                        }
                        else if (temp.endsWith("3")) {
                            selected_lane = "Lane 3";
                        }
                        else{
                            selected_lane = "Lane 4";
                        }
                        String am_pm="",ex="";
                        int time_hour_daw = timePicker.getCurrentHour();
                        int time_minute_daw = timePicker.getCurrentMinute();
                        if(time_hour_daw > 12){
                            int temp2 = time_hour_daw-12;
                            am_pm = "pm";
                            ex = "" + temp2;
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
                            int temp2 = time_hour_daw+12;
                            am_pm = "am";
                            ex = "" + temp2;
                        }
//                        Intent i = new Intent(context, AddSchedule.class);
//                        i.putExtra("date",year + "-" + month + "-" + day);
//                        i.putExtra("time",ex +":" +time_minute_daw + " " +am_pm.toUpperCase());
//                        i.putExtra("to",to);
//                        i.putExtra("from",from);
//                        i.putExtra("email",finalEmail);
//                        i.putExtra("what_lrt","MRT 3");
//                        i.putExtra("lane",selected_lane);
//                        context.startActivity(i);
                        Toast.makeText(context,month +"-"+day+"-"+year+"\n"+ex +":" +time_minute_daw + " " +am_pm.toUpperCase() +"\n" +to+"\n"+from+"\n"+selected_lane,Toast.LENGTH_LONG).show();

                    }
                });
                builder1.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
        btnBookSched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setTitle("Confirmation for Booking a Schedule");
                builder1.setMessage("Are you sure?");
                builder1.setIcon(android.R.drawable.ic_menu_info_details);
                builder1.setCancelable(true);
                builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog2, int which) {
                        String month = "" + (datePicker.getMonth() + 1);
                        String day = "" + datePicker.getDayOfMonth();
                        String year = "" + datePicker.getYear();
                        String from = String.valueOf(spinFromStation3.getSelectedItem());
                        String to = String.valueOf(spinToStation3.getSelectedItem());
                        RadioGroup grp = (RadioGroup) dialog.getWindow().findViewById(R.id.radioLanes);
                        int selected = grp.getCheckedRadioButtonId();
                        String selected_lane;
                        String temp = String.valueOf(selected);
                        if (temp.endsWith("1")) {
                            selected_lane = "Lane 1";
                        }
                        else if (temp.endsWith("2")) {
                            selected_lane = "Lane 2";
                        }
                        else if (temp.endsWith("3")) {
                            selected_lane = "Lane 3";
                        }
                        else{
                            selected_lane = "Lane 4";
                        }
                        String am_pm="",ex="";
                        int time_hour_daw = timePicker.getCurrentHour();
                        int time_minute_daw = timePicker.getCurrentMinute();
                        if(time_hour_daw > 12){
                            int temp2 = time_hour_daw-12;
                            am_pm = "pm";
                            ex = "" + temp2;
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
                            int temp2 = time_hour_daw+12;
                            am_pm = "am";
                            ex = "" + temp2;
                        }
//                        Intent i = new Intent(context, AddSchedule.class);
//                        i.putExtra("date",year + "-" + month + "-" + day);
//                        i.putExtra("time",ex +":" +time_minute_daw + " " +am_pm.toUpperCase());
//                        i.putExtra("to",to);
//                        i.putExtra("from",from);
//                        i.putExtra("email",finalEmail);
//                        i.putExtra("what_lrt","MRT 3");
//                        i.putExtra("lane",selected_lane);
//                        context.startActivity(i);
                        Toast.makeText(context,month +"-"+day+"-"+year+"\n"+ex +":" +time_minute_daw + " " +am_pm.toUpperCase() +"\n" +to+"\n"+from+"\n"+selected_lane,Toast.LENGTH_LONG).show();

                    }
                });
                builder1.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
    }
//    private static String doPayLRT2(String from,String to){
//        if(from.equals(to)){
//            //
//        }
//        else {
//            if (from.equals("C.M. Recto")) {
//                if (stations_15.contains(to)) {
//                    fee = "" + 15;
//                } else if (stations_20.contains(to)) {
//                    fee = "" + 20;
//                } else {
//                    fee = "" + 25;
//                }
//            } else if (from.equals("Legarda")) {
//                stations_15.add(stations_20.get(0));//jruiz
//                stations_20.remove(new String(stations_20.get(0)));
//                stations_20.add(stations_25.get(0));//anonas
//                stations_25.remove(new String(stations_25.get(0)));
//                if (stations_15.contains(to)) {
//                    fee = "" + 15;
//                } else if (stations_20.contains(to)) {
//                    fee = "" + 20;
//                } else {
//                    fee = "" + 25;
//                }
//            } else if (from.equals("Pureza")) {
//                stations_15.add("Gilmore");
//                stations_20.remove(new String("Gilmore"));
//                stations_20.add(new String("Katipunan"));
//                stations_25.remove(new String("Katipunan"));
//                Log.d("15", stations_15.toString() + "\n");
//                Log.d("20",stations_20.toString()+"\n");
//                Log.d("25",stations_25.toString()+"\n");
//                if (stations_15.contains(to)) {
//                    fee = "" + 15;
//                } else if (stations_20.contains(to)) {
//                    fee = "" + 20;
//                } else {
//                    fee = "" + 25;
//                }
//            } else if (from.equals("V.Mapa")) {
//                stations_15.add(new String("Betty Go Belmonte"));//betty go
//                stations_20.remove(new String("Betty Go Belmonte"));
//                Log.d("15", stations_15.toString() + "\n");
//                Log.d("20", stations_20.toString() + "\n");
//                Log.d("25", stations_25.toString() + "\n");
//                if (stations_15.contains(to)) {
//                    fee = "" + 15;
//                } else if (stations_20.contains(to)) {
//                    fee = "" + 20;
//                } else {
//                    fee = "" + 25;
//                }
//            }
//        }
//        return fee;
//    }
    private int getDrawableId(ImageView iv) {
        return (Integer) iv.getTag();
    }
}
