package com.example.codefest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class dates extends AppCompatActivity {
    @SuppressWarnings("unchecked")
    //public static final String KEY_ITEM_TEXT = "item_text";
    //public static final String KEY_ITEM_POSITION ="item_position";
    //public static final int EDIT_TEXT_CODE = 20;
    datesRecViewAdapter adapter;
    ArrayList <dates_data>database = new ArrayList<>();
    ArrayList <dates_data> databaseclone =new ArrayList<>();
    int current_year;
    int current_day;
    String current_month;
    int current_weekday;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates);
        RecyclerView datesRecView = findViewById(R.id.datesRecView);
        load_data();

        databaseclone = (ArrayList<dates_data>) database.clone();

        Bundle extras = getIntent().getExtras();
        current_year = extras.getInt("Current year");
        current_day = extras.getInt("Current day");
        current_month = extras.getString("Current month"); // Change the month to word
        current_weekday = extras.getInt("Current weekday");

        Toast.makeText(getApplicationContext(),(String)(current_year+"|"+current_month+"|"+current_day+" - "+ current_weekday),Toast.LENGTH_SHORT).show();
        sort();

        datesRecView.setHasFixedSize(true);
        datesRecView.setLayoutManager(new LinearLayoutManager(this));


        adapter= new datesRecViewAdapter(databaseclone,dates.this);
        datesRecView.setAdapter(adapter);
        adapter.setOnItemClickListener(new datesRecViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getApplicationContext(),"clicked!",Toast.LENGTH_SHORT).show(); // This works
            }
        });
        adapter.setOnItemLongClickListener(new datesRecViewAdapter.OnLongClickListener() {
            @Override
            public void onItemLongClicked(int position) {

                Iterator<dates_data> itr = database.iterator();
                while (itr.hasNext()) {
                    dates_data temp  = itr.next();
                    if(databaseclone.get(position).getId().equals(temp.getId())){
                        itr.remove();
                    } //Generate unique Id's
                }

                databaseclone.remove(position);
                //remove the item with that id save then reload everything
                adapter.notifyItemRemoved(position);
                save_data();
                //load_data();
                //sort();
                //Todo stop the duplication glitch
            }
        });

    }
    public void sort(){// implement better sort
        Iterator<dates_data> itr = databaseclone.iterator();
        while (itr.hasNext()) {
            dates_data temp  = itr.next();
            if (current_weekday != temp.getSchedule()){
                itr.remove();
            }else if(!temp.getDate().equals((String)(current_month+"|"+current_day+"|"+current_year))){
                itr.remove();
            }
        }

        for(int i = 0; i <databaseclone.size()-1;i++){ //could be out of range? bubble sort
            for(int j = 0; j < databaseclone.size()-i-1; j++){
                if(((Integer.parseInt(databaseclone.get(j).getTime_hour()) * 60 )+(Integer.parseInt(databaseclone.get(j).getTime_minute()))) > ((Integer.parseInt(databaseclone.get(j+1).getTime_hour()) * 60 )+(Integer.parseInt(databaseclone.get(j+1).getTime_minute())))){
                    Collections.swap(databaseclone,j ,j+1);
                }
            }
        }
        //return tracking with the tracking index
    }
    //Todo add a button and implement an listener to add task to that date.
    //Todo remove a item on the schedule

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
