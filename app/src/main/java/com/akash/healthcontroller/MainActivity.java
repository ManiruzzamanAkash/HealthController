package com.akash.healthcontroller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.akash.healthcontroller.Web.OurWebView;
import com.akash.healthcontroller.notification_receiver.AfternoonNotification;
import com.akash.healthcontroller.notification_receiver.MorningNotification;
import com.akash.healthcontroller.notification_receiver.NightNotification;
import com.akash.healthcontroller.notification_receiver.NoonNotification;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RadioGroup.OnCheckedChangeListener {

    //SharePreferrance File name where the shared data will be stored in device
    public static String fileName = "MySharedFile";
    public Date historyDate;
    public int age = 0;
    public float height = 0.0f, weight = 0.0f;
    public boolean isMale = false, isFemale = false;
    DatabaseHelper myDB = new DatabaseHelper(MainActivity.this);
    EditText ageEditText, heightEditText, weightEditText;
    TextView resultTextView;
    Button checkResultButton, resetInputButton, seeWhatisBMIButton, gobtnFoodList;
    RadioGroup genderRadioGroup;
    //For Notification
    NotificationCompat.Builder notification;
    String morning_time, noon_time, afternoon_time, night_time;
    SharedPreferences someData;
    String notification_on_or_off, history_on_or_off;
    private int mId = 9122;
    private int morning_notification_id = 1001;
    private int noon_notification_id = 1002;
    private int afternoon_notification_id = 1003;
    private int night_notification_id = 1004;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initialize_all();
        someData = getSharedPreferences(fileName, 0);

        notification_on_or_off = someData.getString("is_notification_on_off", "Data Not Found");
        history_on_or_off = someData.getString("is_history_on_off", "Data Not Found");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        set_routine_notification();


    }

    private void set_routine_notification() {
        //Read Meal Notifications time
        morning_time = someData.getString("morning_time", "Data Not Found");
        noon_time = someData.getString("noon_time", "Data Not Found");
        afternoon_time = someData.getString("afternoon_time", "Data Not Found");
        night_time = someData.getString("night_time", "Data Not Found");

        if (notification_on_or_off.equals("on")) {
            set_morning_notification();
            set_noon_notification();
            set_afternoon_notification();
            set_night_notification();
        }

    }


    private void set_morning_notification() {
        //        Toast.makeText(MainActivity.this, "Morning Time= "+ morning_time, Toast.LENGTH_LONG).show();
        String morning_hour = morning_time.substring(0, 2).toString();
        String morning_minute = morning_time.substring(3, 5).toString();

        Calendar cal = Calendar.getInstance();

        //Set for morning
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(morning_hour));
        cal.set(Calendar.MINUTE, Integer.parseInt(morning_minute));
        cal.set(Calendar.SECOND, 0);
        Intent morning_intent = new Intent(getApplicationContext(), MorningNotification.class);
        PendingIntent morningPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), morning_notification_id, morning_intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager morningAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        morningAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), morningAlarmManager.INTERVAL_HOUR, morningPendingIntent);

    }

    private void set_noon_notification() {
        String noon_hour = noon_time.substring(0, 2).toString();
        String noon_minute = noon_time.substring(3, 5).toString();

        Calendar cal = Calendar.getInstance();

        //Set for morning
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(noon_hour));
        cal.set(Calendar.MINUTE, Integer.parseInt(noon_minute));
        cal.set(Calendar.SECOND, 0);
        Intent noon_intent = new Intent(getApplicationContext(), NoonNotification.class);
        PendingIntent noonPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), noon_notification_id, noon_intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager noonAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        noonAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), noonAlarmManager.INTERVAL_HOUR, noonPendingIntent);

    }

    private void set_afternoon_notification() {
        String afternoon_hour = afternoon_time.substring(0, 2).toString();
        String afternoon_minute = afternoon_time.substring(3, 5).toString();

        Calendar cal = Calendar.getInstance();

        //Set for afternoon
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(afternoon_hour));
        cal.set(Calendar.MINUTE, Integer.parseInt(afternoon_minute));
        cal.set(Calendar.SECOND, 0);
        Intent afternoon_intent = new Intent(getApplicationContext(), AfternoonNotification.class);
        PendingIntent afternoonPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), afternoon_notification_id, afternoon_intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager afternoonAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        afternoonAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), afternoonAlarmManager.INTERVAL_HOUR, afternoonPendingIntent);

    }

    private void set_night_notification() {
        String night_hour = night_time.substring(0, 2).toString();
        String night_minute = night_time.substring(3, 5).toString();

        Calendar cal = Calendar.getInstance();

        //Set for afternoon
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(night_hour));
        cal.set(Calendar.MINUTE, Integer.parseInt(night_minute));
        cal.set(Calendar.SECOND, 0);
        Intent night_intent = new Intent(getApplicationContext(), NightNotification.class);
        PendingIntent nightPendingIntent = PendingIntent.getBroadcast(getApplicationContext(), afternoon_notification_id, night_intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager nightAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        nightAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), nightAlarmManager.INTERVAL_HOUR, nightPendingIntent);


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        }
        if (id == R.id.action_about) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        }
        if (id == R.id.action_exit) {
            return true;
        }
        if (id == R.id.action_help) {
            startActivity(new Intent(MainActivity.this, HelpActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_history) {
            startActivity(new Intent(MainActivity.this, HistoryActivity.class));
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_web) {
            startActivity(new Intent(MainActivity.this, OurWebView.class));
        } else if (id == R.id.nav_help) {
            startActivity(new Intent(MainActivity.this, HelpActivity.class));
        } else if (id == R.id.nav_routine) {
            startActivity(new Intent(MainActivity.this, RoutineActivity.class));
        } else if (id == R.id.nav_what_is) {
            startActivity(new Intent(MainActivity.this, WhatIs.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio_btn_male:
                isMale = true;
                break;
            case R.id.radio_btn_female:
                isFemale = true;
                break;
        }
    }

    public void initialize_all() {

        genderRadioGroup = (RadioGroup) findViewById(R.id.radio_gender);
        genderRadioGroup.setOnCheckedChangeListener(this);

        //Initialize fields
        ageEditText = (EditText) findViewById(R.id.edit_txt_age);
        heightEditText = (EditText) findViewById(R.id.edit_txt_height);
        weightEditText = (EditText) findViewById(R.id.edit_txt_weight);

        resultTextView = (TextView) findViewById(R.id.txt_result);
        resultTextView.setText("");
        checkResultButton = (Button) findViewById(R.id.btn_check);
        resetInputButton = (Button) findViewById(R.id.btn_reset);

        seeWhatisBMIButton = (Button) findViewById(R.id.learn_more_btn);

        checkResultButton.setOnClickListener(new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     //Check inputs
                                                     String ageTxt = ageEditText.getText().toString();
                                                     String heightTxt = heightEditText.getText().toString();
                                                     String weightTxt = weightEditText.getText().toString();

                                                     if (ageTxt.isEmpty() || heightTxt.isEmpty() || weightTxt.isEmpty()) {
                                                         resultTextView.setBackgroundResource(R.color.red_deep);
                                                         resultTextView.setTextColor(getResources().getColor(R.color.white));
                                                         resultTextView.setText("Please fill all the fields");
                                                     } else {

                                                         age = Integer.parseInt(ageTxt);
                                                         height = Float.parseFloat(heightTxt);
                                                         weight = Float.parseFloat(weightTxt);
                                                         if (age == 0 || height == 0.0f || weight == 0.0f) {
                                                             resultTextView.setBackgroundResource(R.color.red_deep);
                                                             resultTextView.setTextColor(getResources().getColor(R.color.white));
                                                             resultTextView.setText("Please give every input more than 0");
                                                         } else {
                                                             double BMR = 0.2f;
                                                             double BMI = 0.2f;
                                                             if (isMale) {
                                                                 //For male
                                                                 BMR = 66 + (13.7 * weight) + (5 * height) - (6.8 * age);
                                                             } else if (isFemale) {
                                                                 //For Female
                                                                 BMR = 655 + (9.6 * weight) + (1.8 * height) - (4.7 * age);
                                                             } else {
                                                                 resultTextView.setBackgroundResource(R.color.red_deep);
                                                                 resultTextView.setTextColor(getResources().getColor(R.color.white));
                                                                 resultTextView.setText("Please Select your gender");
                                                             }

                                                             BMI = weight / ((height / 100) * (height / 100));

                                                             String BMI_string = String.format("%.2f", BMI);
                                                             String BMR_string = String.format("%.2f", BMR);

                                                             if (BMI < 18.5) {
                                                                 //Has to increase weight

                                                             } else if (BMI >= 18.5 || BMI <= 24.9) {
                                                                 //Ideal weight and Hight Combination

                                                             } else if (BMI >= 30 || BMI <= 34.9) {
                                                                 //First Level Fat. Has to eat by banlancing

                                                             } else if (BMI >= 35 || BMI <= 39.9) {
                                                                 //Second Level Fat. Has to eat by banlancing and excercise

                                                             } else {
                                                                 //Has the posssibility to a heart attack

                                                             }


                                                             String dateTime = DateFormat.getDateTimeInstance().format(new Date());
                                                             String entry = "\nDate : " + dateTime + "\nHeight = " + heightTxt +
                                                                     " | Weight = " + weightTxt + "\nBMR = " + BMR_string + "\nBMI = " + BMI_string + "\n";

                                                             if (history_on_or_off.equals("on")) {
                                                                 boolean insertData = myDB.addHistoryData(entry);
                                                                 if (insertData == true) {
                                                                     Toast.makeText(MainActivity.this, "Data has added to history also", Toast.LENGTH_SHORT).show();
                                                                 } else {
                                                                     Toast.makeText(MainActivity.this, "Data has not added to history also", Toast.LENGTH_SHORT).show();
                                                                 }
                                                             }

                                                             resultTextView.setText("BMR = " + BMR_string + "\nBMI = " + BMI_string);
                                                         }
                                                     }

                                                 }
                                             }

        );

        resetInputButton.setOnClickListener(new View.OnClickListener()

                                            {
                                                @Override
                                                public void onClick(View v) {
                                                    ageEditText.setText("");
                                                    heightEditText.setText("");
                                                    weightEditText.setText("");
                                                    resultTextView.setText("");
                                                    Toast.makeText(MainActivity.this, "Everything has resetted... Type again to check new data", Toast.LENGTH_SHORT).show();
                                                }
                                            }

        );

        seeWhatisBMIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WhatIs.class));
            }
        });

        goFoodActivity();

    }

    private void insertHistoryData(String entry) {
        boolean insertData = myDB.addHistoryData(entry);
        if (insertData == true) {
            Toast.makeText(MainActivity.this, "Data has added to history also", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Data has not added to history also", Toast.LENGTH_SHORT).show();
        }
    }

    //GO food activity intent
    public void goFoodActivity() {
        gobtnFoodList = (Button) findViewById(R.id.btnFoodList);
        gobtnFoodList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FoodItem_Activity.class));
            }
        });

    }


}
