package com.example.codefest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Edit_Activity extends AppCompatActivity {
    ArrayList<dates_data> database;

    // Edit in this activity. Adding schedule or adding a new task item
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_);



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