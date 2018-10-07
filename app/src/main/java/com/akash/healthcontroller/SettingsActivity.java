package com.akash.healthcontroller;

import android.content.SharedPreferences;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    //SharePreferrance File name where the shared data will be stored in device
    public static  String fileName = "MySharedFile";
    SharedPreferences someData;

    CheckBox historyCheckBox, notificationCheckBox;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initialize_all();
        set_settings_in_shared_preference();
    }

    private void set_settings_in_shared_preference() {
        //First set shared preference object
        someData = getSharedPreferences(fileName, 0);

        String notification_on_or_off = someData.getString("is_notification_on_off", "Data Not Found");
        String history_on_or_off = someData.getString("is_history_on_off", "Data Not Found");

        //Set Check for notification
        if(notification_on_or_off.equals("on"))
        {
            notificationCheckBox.setChecked(true);
        }else
        {
            notificationCheckBox.setChecked(false);
        }

        //Set Check for History
        if(history_on_or_off.equals("on"))
        {
            historyCheckBox.setChecked(true);
        }else
        {
            historyCheckBox.setChecked(false);
        }
    }

    private void initialize_all() {

        historyCheckBox = (CheckBox) findViewById(R.id.checkbox_history);
        notificationCheckBox = (CheckBox) findViewById(R.id.checkbox_notification);
        saveButton = (Button)  findViewById(R.id.saveSettings);
        saveSettingsAction();

    }

    private void saveSettingsAction() {

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(historyCheckBox.isChecked() == true)
                {
                    //Store this (the selection) in the shared preference
                    SharedPreferences.Editor editor = someData.edit();
                    editor.putString("is_history_on_off", "on");
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "History will be stored in next time", Toast.LENGTH_SHORT).show();
                }

                if(historyCheckBox.isChecked() == false)
                {
                    //Store this (the selection) in the shared preference
                    SharedPreferences.Editor editor = someData.edit();
                    editor.putString("is_history_on_off", "off");
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "History will not be stored in next time", Toast.LENGTH_SHORT).show();
                }


                if(notificationCheckBox.isChecked() == true)
                {
                    //Store this (the selection) in the shared preference
                    SharedPreferences.Editor editor = someData.edit();
                    editor.putString("is_notification_on_off", "on");
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "You'll get a meal eating notification in Next Time..! \nFirst Set the meal in Set Routine Tab", Toast.LENGTH_SHORT).show();
                }

                if(notificationCheckBox.isChecked() == false)
                {
                    //Store this (the selection) in the shared preference
                    SharedPreferences.Editor editor = someData.edit();
                    editor.putString("is_notification_on_off", "off");
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Notification will not be showed in next time..Don't worry!!!", Toast.LENGTH_SHORT).show();
                }



                Toast.makeText(SettingsActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        return true;
    }
}
