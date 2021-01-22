package com.example.codefest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.joda.time.format.*;
import org.joda.time.DateTime;
import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Edit_Activity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{
    private Button addbutton;
    private TextView colon;
    private TextView title;
    private TextView date;
    private TextView time;
    private TextView itemType;
    private TextView weekdays;

    private EditText year;
    private EditText taskedit;

    private Spinner hours;
    private Spinner minutes;
    private Spinner itemtypespinner;
    private Spinner month;
    private Spinner day;
    private Spinner schedule_spinner;

    private ArrayList<dates_data> database;
    private String task_temp;
    private int hours_temp;
    private int minutes_temp;
    private String month_temp;
    private int years_temp;
    private int days_temp;
    private int schedule_temp;
    private boolean item_temp;
    private String id_temp;
    private Integer image_temp;


    // Edit in this activity. Adding schedule or adding a new task item
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_);

        addbutton =  (Button) findViewById(R.id.addbutton);
        colon = (TextView)findViewById(R.id.colon);
        title = (TextView) findViewById(R.id.edit_text_title);
        date = (TextView)findViewById(R.id.Date);
        time = (TextView)findViewById(R.id.time);
        weekdays = (TextView)findViewById(R.id.weekdays);
        itemType =  (TextView)findViewById(R.id.itemtype);

        year = (EditText) findViewById(R.id.year);
        taskedit = (EditText) findViewById(R.id.editTexttaskname);
        schedule_spinner = findViewById(R.id.schedule);

        hours = findViewById(R.id.hours);
        minutes = findViewById(R.id.minutes);
        itemtypespinner = findViewById(R.id.itemtypespinner);
        month = findViewById(R.id.month);
        day = findViewById(R.id.day);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Date_month, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        month.setAdapter(adapter);
        month.setOnItemSelectedListener(this);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.Date_day, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        day.setAdapter(adapter);
        day.setOnItemSelectedListener(this);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.time_hours, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hours.setAdapter(adapter);
        hours.setOnItemSelectedListener(this);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.time_minute, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        minutes.setAdapter(adapter);
        minutes.setOnItemSelectedListener(this);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.item_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        itemtypespinner.setAdapter(adapter);
        itemtypespinner.setOnItemSelectedListener(this);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.schedule, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schedule_spinner.setAdapter(adapter);
        schedule_spinner.setOnItemSelectedListener(this);

        addbutton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                additem();
            }
        });
    }
    public String generate_random_id() {
        String charset = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
        Random rand = new Random();
        String id = "";
        for (int i = 0; i < 10; i++) {
            id = id + charset.charAt(rand.nextInt(charset.length()));
        }
        return id;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void additem(){
        load_data();

        days_temp = Integer.parseInt(day.getSelectedItem().toString());
        month_temp =month.getSelectedItem().toString();
        years_temp =Integer.parseInt(year.getText().toString());
        image_temp= R.drawable.ic_android;
        minutes_temp = Integer.parseInt(minutes.getSelectedItem().toString());
        hours_temp = Integer.parseInt(hours.getSelectedItem().toString());
        id_temp = generate_random_id();
        task_temp = taskedit.getText().toString();
        int m = 0;
        switch (month_temp){
            case "Jan": m =1; break;
            case "Feb": m =2; break;
            case "Mar": m =3; break;
            case "Apr": m =4; break;
            case "May": m =5; break;
            case "Jun": m =6; break;
            case "Jul": m =7; break;
            case "Aug": m =8; break;
            case "Sep": m =9; break;
            case "Oct": m =10; break;
            case "Nov": m =11; break;
            case "Dec": m =12;break;
            default: break;
        }
        if(check_valid_date(days_temp,m,years_temp)){
            //dates_data(int time_hours, int time_minutes, String month, int year, int day, String task, int schedule, boolean item, Integer image, String id)
            if(itemtypespinner.getSelectedItem().toString().equals("Schedule")){ // make a schedule
                switch(schedule_spinner.getSelectedItem().toString()){
                    case "Monday": schedule_temp = 2; break;
                    case "Tuesday": schedule_temp = 3; break;
                    case "Wednesday": schedule_temp = 4; break;
                    case "Thursday": schedule_temp = 5; break;
                    case "Friday": schedule_temp = 6; break;
                    case "Saturday": schedule_temp = 7; break;
                    case "Sunday": schedule_temp = 1; break;
                    default:schedule_temp = -1; break;
                }
                if(schedule_temp == -1){
                    Toast.makeText(getApplicationContext(),"Error adding schedule",Toast.LENGTH_SHORT).show();
                }else{
                    database.add(new dates_data(hours_temp,minutes_temp,month_temp,years_temp,days_temp,task_temp,schedule_temp,false,R.drawable.ic_android,id_temp));
                    Toast.makeText(getApplicationContext(),"Added schedule",Toast.LENGTH_SHORT).show();
                }
            }else{ // make a single item.
                database.add(new dates_data(hours_temp,minutes_temp,month_temp,years_temp,days_temp,task_temp,0,true,R.drawable.ic_android,id_temp));
                Toast.makeText(getApplicationContext(),"Added item",Toast.LENGTH_SHORT).show();
            }
            save_data();
        }else{
            Toast.makeText(getApplicationContext(),"Error adding date check your item again",Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(getApplicationContext(), d + " "+ m +" "+ y, Toast.LENGTH_SHORT).show();
        /*
        if(check_valid_date(d,m,y) && find_weekday(m,d,y) != -1){ // failling this check
            if(am_or_pm.getSelectedItem().toString().equals("PM")){
                time_temp =Integer.toString((Integer.parseInt(hours.getSelectedItem().toString()) *2));
            }
            int schedule_temp=find_weekday(m,d,y);
            generate_random_id();
            if(itemtypespinner.getSelectedItem().toString().equals("Schedule")){
                database.add(new dates_data(time_temp, minutes.getSelectedItem().toString(), month.getSelectedItem().toString(), year.getText().toString(), day.getSelectedItem().toString(), taskedit.getText().toString(),schedule_temp , false, R.drawable.ic_android,id_temp));
            }else{
                database.add(new dates_data(time_temp.toString(), minutes.getSelectedItem().toString(), month.getSelectedItem().toString(), year.getText().toString(), day.getSelectedItem().toString(), taskedit.getText().toString(), 0 , true, R.drawable.ic_android,id_temp));
            }
            Toast.makeText(getApplicationContext(),"Item added",Toast.LENGTH_SHORT).show();
            //dates_data(String time_hours, String time_minutes, String month, String year, String day, String task,int schedule, boolean item, Integer image, String id)
            save_data();
        }else{
            Toast.makeText(getApplicationContext(),"Error adding date",Toast.LENGTH_SHORT).show();
            //do not add item and out put error message save_data();
        }
        //extract and create new item. then save

         */

    }

    public boolean check_valid_date(int d, int m, int y) {
        if (y > 9999 || y < 1800)
            return false;
        if (m < 1 || m > 12)
            return false;
        if (d < 1 || d > 31)
            return false;
        if (m == 2){
            if (isLeap(y))
                return (d <= 29);
            else
                return (d <= 28);
        }

        if (m == 4 || m == 6 || m == 9 || m == 11)
            return (d <= 30);
        return true;
    }
    public boolean isLeap(int year){
        return (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int find_weekday(int m, int d, int y){ //joda time is easier
        LocalDate temp_time = LocalDate.of(y,m,d);

        String tempstring = temp_time.getDayOfWeek().toString();
        if (tempstring.equals("MONDAY")){
            return 2;
        }else if (tempstring.equals("TUESDAY")){
            return 3;
        }else if (tempstring.equals("WEDNESDAY")){
            return 4;
        }else if (tempstring.equals("THURSDAY")){
            return 5;
        }else if (tempstring.equals("FRIDAY")){
            return 6;
        }else if (tempstring.equals("SATURDAY")){
            return 7;
        }else if(tempstring.equals("SUNDAY")){
            return 1;
        }else{
            return -1;
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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