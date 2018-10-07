package com.akash.healthcontroller;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class RoutineActivity extends AppCompatActivity implements View.OnClickListener{

    Button morningTimeButton, noonTimeButton, afternoonTimeButton, nightTimeButton;


    //For Morning Time
    static final int MORNING_DIALOG_ID = 0;
    int hour_morning, minute_morning;

    //For Noon Time
    static final int NOON_DIALOG_ID = 1;
    int hour_noon, minute_noon;

    //For Afternoon Time
    static final int AFTERNOON_DIALOG_ID = 2;
    int hour_afternoon, minute_afternoon;

    //For night Time
    static final int NIGHT_DIALOG_ID = 3;
    int hour_night, minute_night;

    //SharePreferrance File name where the shared data will be stored in device
    public static  String fileName = "MySharedFile";
    SharedPreferences someData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);
        initialize_all();
    }

    private void initialize_all()
    {
        someData = getSharedPreferences(fileName, 0);

        morningTimeButton = (Button) findViewById(R.id.btn_morning_time);
        noonTimeButton = (Button) findViewById(R.id.btn_noon_time);
        afternoonTimeButton = (Button) findViewById(R.id.btn_afternoon_time);
        nightTimeButton = (Button) findViewById(R.id.btn_night_time);

        morningTimeButton.setOnClickListener(this);
        noonTimeButton.setOnClickListener(this);
        afternoonTimeButton.setOnClickListener(this);
        nightTimeButton.setOnClickListener(this);

        //Testing Shared Preference
        String morning_time = someData.getString("morning_time", "Data Not Found");
        String noon_time = someData.getString("noon_time", "Data Not Found");
        String afternoon_time = someData.getString("afternoon_time", "Data Not Found");
        String night_time = someData.getString("night_time", "Data Not Found");

        morningTimeButton.setText(morning_time);
        noonTimeButton.setText(noon_time);
        afternoonTimeButton.setText(afternoon_time);
        nightTimeButton.setText(night_time);
        Toast.makeText(this, "Shared times--- Morning: "+morning_time +"Noon Time : "+noon_time, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){

            case R.id.btn_morning_time:
                showDialog(MORNING_DIALOG_ID);
                break;
            case R.id.btn_noon_time:
                showDialog(NOON_DIALOG_ID);
                break;
            case R.id.btn_afternoon_time:
                showDialog(AFTERNOON_DIALOG_ID);
                break;
            case R.id.btn_night_time:
                showDialog(NIGHT_DIALOG_ID);
                break;

        }

    }

    //Oncreate dialog
    @Override
    protected Dialog onCreateDialog(int id) {

        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        if(id == MORNING_DIALOG_ID){

            TimePickerDialog dialog = new TimePickerDialog(this, morningListener, hour, minute, false);
            return dialog;
        }
        if(id == NOON_DIALOG_ID){
            TimePickerDialog dialog = new TimePickerDialog(this, noonListener, hour, minute, false);
            return dialog;
        }
        if(id == AFTERNOON_DIALOG_ID){
            TimePickerDialog dialog = new TimePickerDialog(this, afternoonListener, hour, minute, false);
            return dialog;
        }
        if(id == NIGHT_DIALOG_ID){
            TimePickerDialog dialog = new TimePickerDialog(this, nightListener, hour, minute, false);
            return dialog;
        }else{
            TimePickerDialog dialog = new TimePickerDialog(this, morningListener, hour, minute, false);
            return dialog;
        }

    }

    //Morning Time Listener
    TimePickerDialog.OnTimeSetListener morningListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String hour = String.valueOf(hourOfDay), minute_s = String.valueOf(minute);
            if (hourOfDay < 10) {
                hour = "0" + String.valueOf(hourOfDay);
            }
            if(minute < 10){
                minute_s="0" + String.valueOf(minute);
            }
            String finalOutput = hour+":"+minute_s+" AM";
            SharedPreferences.Editor editor = someData.edit();
            editor.putString("morning_time", finalOutput);
            editor.commit();

            morningTimeButton.setText(finalOutput);
        }
    };

    //Morning Time Listener
    TimePickerDialog.OnTimeSetListener noonListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String hour = String.valueOf(hourOfDay), minute_s = String.valueOf(minute);
            if (hourOfDay < 10) {
                hour = "0" + String.valueOf(hourOfDay);
            }
            if(minute < 10){
                minute_s="0" + String.valueOf(minute);
            }
            String finalOutput = hour + ":" + minute_s + " PM";
            SharedPreferences.Editor editor = someData.edit();
            editor.putString("noon_time", finalOutput);
            editor.commit();
            noonTimeButton.setText(finalOutput);
        }
    };

    //Morning Time Listener
    TimePickerDialog.OnTimeSetListener afternoonListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String hour = String.valueOf(hourOfDay), minute_s = String.valueOf(minute);
            if (hourOfDay < 10) {
                hour = "0" + String.valueOf(hourOfDay);
            }
            if(minute < 10){
                minute_s="0" + String.valueOf(minute);
            }

            String finalOutput = hour + ":" + minute_s + " PM";
            SharedPreferences.Editor editor = someData.edit();
            editor.putString("afternoon_time", finalOutput);
            editor.commit();
            afternoonTimeButton.setText(finalOutput);
        }
    };

    //Morning Time Listener
    TimePickerDialog.OnTimeSetListener nightListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String hour = String.valueOf(hourOfDay), minute_s = String.valueOf(minute);
            if (hourOfDay < 10) {
                hour = "0" + String.valueOf(hourOfDay);
            }
            if(minute < 10){
                minute_s="0" + String.valueOf(minute);
            }
            String finalOutput = hour + ":" + minute_s + " PM";
            SharedPreferences.Editor editor = someData.edit();
            editor.putString("night_time", finalOutput);
            editor.commit();
            nightTimeButton.setText(finalOutput);
        }
    };


}
