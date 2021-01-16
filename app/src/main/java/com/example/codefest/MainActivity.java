package com.example.codefest;

import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

//todo logo for the app


public class MainActivity extends AppCompatActivity {
    ArrayList<dates_data> database = new ArrayList<>();
    int currentyear;
    int currentmonth;
    int currentday;
    int currentweekday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        CalendarView cal =(CalendarView) findViewById(R.id.calendarView);

        setSupportActionBar(toolbar);
        load_data();
        Button add_schedule = findViewById(R.id.AddSchedule);

        add_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                currentyear = year;
                currentmonth = month +1;
                currentday = dayOfMonth;
                currentweekday = calendar.get(Calendar.DAY_OF_WEEK);
                opendates();
                //Toast.makeText(getApplicationContext(), ""+year +" "+ (month+1) +" "+ dayOfMonth, Toast.LENGTH_SHORT).show();

            }
        });

        //Todo make a button that allows for scheduling for every specific time and week days of the day.
        //Todo make notification when that day and time for the task is reached.
        //Todo make a button that remove the task

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openedit(){
        Intent intent = new Intent(this, Edit_Activity.class);
        startActivity(intent);
    }

    public void opendates(){
        Intent intent = new Intent(this, dates.class);
        String month = new String();
        switch(currentmonth){
            case 1: month = "Jan"; break;
            case 2: month = "Feb"; break;
            case 3: month = "Mar"; break;
            case 4: month = "Apr"; break;
            case 5: month = "May"; break;
            case 6: month = "Jun"; break;
            case 7: month = "Jul"; break;
            case 8: month = "Aug"; break;
            case 9: month = "Sep"; break;
            case 10: month = "Oct"; break;
            case 11: month = "Nov"; break;
            case 12: month = "Dec"; break;
            default: month = "";break;
        }

        intent.putExtra("Current year", currentyear);
        intent.putExtra("Current month", month);
        intent.putExtra("Current day", currentday);
        intent.putExtra("Current weekday", currentweekday);
        startActivity(intent);
    }

    public void load_data() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("dates_data", null);
        Type type = new TypeToken<ArrayList<dates_data>>() {}.getType();
        database = gson.fromJson(json, type);
        if (database == null) {
            database = new ArrayList<>();
        }
    }

    public void save_data() {  // This function saves items by writing them into the data file
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(database);
        editor.putString("dates_data", json);
        editor.apply();
    }



}