package com.example.codefest;

import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        CalendarView cal =(CalendarView) findViewById(R.id.calendarView);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                opendates();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                String DayOftheWeek = new String();
                switch (calendar.get(Calendar.DAY_OF_WEEK)) {
                    case 1: DayOftheWeek ="Sunday"; break;
                    case 2: DayOftheWeek ="Monday"; break;
                    case 3: DayOftheWeek ="Tuesday"; break;
                    case 4: DayOftheWeek ="Wednesday"; break;
                    case 5: DayOftheWeek ="Thursday"; break;
                    case 6: DayOftheWeek ="Friday"; break;
                    case 7: DayOftheWeek ="Saturday"; break;
                    default: DayOftheWeek ="Unknown"; break;
                }

                Toast.makeText(getApplicationContext(), ""+year +" "+ month +" "+ dayOfMonth, Toast.LENGTH_SHORT).show();
            }
        });


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

    public void opendates(){
        Intent intent = new Intent(this, dates.class);
        startActivity(intent);
    }
}