package com.example.codefest;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.*;

import com.google.android.material.snackbar.Snackbar;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class dates extends AppCompatActivity {
    @SuppressWarnings("unchecked")
    //public static final String KEY_ITEM_TEXT = "item_text";
    //public static final String KEY_ITEM_POSITION ="item_position";
    //public static final int EDIT_TEXT_CODE = 20;
    datesRecViewAdapter adapter;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates);
        Button add_item = findViewById(R.id.additem);
        RecyclerView datesRecView = findViewById(R.id.datesRecView);


        ArrayList <dates_data>database = new ArrayList<dates_data>(){}; // Todo load the correct task for each day or weekdays so if I have a schedule on friday. and I clicekd into a friday then the task will appear
        
        database.add(new dates_data("18:20","Jan|10|2010","Make Meth",false,true,R.drawable.ic_android));
        database.add(new dates_data("18:20","Jan|10|2010","Make Meth",false,true,R.drawable.ic_android));
        database.add(new dates_data("18:20","Jan|10|2010","Make Meth",false,true,R.drawable.ic_android));
        database.add(new dates_data("18:20","Jan|10|2010","Make Meth",false,true,R.drawable.ic_android));
        database.add(new dates_data("18:20","Jan|10|2010","Make Meth",false,true,R.drawable.ic_android));
        database.add(new dates_data("18:20","Jan|10|2010","Make Meth",false,true,R.drawable.ic_android));


        //database = load_data();


        datesRecView.setHasFixedSize(true);
        datesRecView.setLayoutManager(new LinearLayoutManager(this));
        adapter= new datesRecViewAdapter(database,dates.this);
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
                Toast.makeText(getApplicationContext(),"Removed",Toast.LENGTH_SHORT).show(); // THis should work
                database.remove(position);
                adapter.notifyItemRemoved(position);

            }
        });


        /*itemsAdapter = new datesRecViewAdapter(database,onLongClickListener, onClickListener );
        datesRecView.setAdapter(itemsAdapter);
        datesRecView.setLayoutManager(new LinearLayoutManager(this));

        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        try {
            database = load_data();
        }catch(Exception error){
            error.printStackTrace();
        }

         */
    }
    //Todo add a button and implement an listener to add task to that date.
    //Todo remove a item on the schedule

    //@RequiresApi(api = Build.VERSION_CODES.N)

    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == EDIT_TEXT_CODE) {
            //Retreive the updated text value
            String itemText = data.getStringExtra(KEY_ITEM_TEXT);
            //Extract the original position of the editred item from the position key
            int position = data.getExtras().getInt(KEY_ITEM_POSITION);

            //update the model at the right position
            data.set(position,itemText );
            itemsAdapter.notifyItemChanged(position);
            saveItems();
            Toast.makeText(getApplicationContext()
                    , "Item updated successfully "
                    ,Toast.LENGTH_SHORT).show();
        }else{
            Log.w("MainActivity","Unknown call to onActivityResult");
        }
    }

     */

/*
    public ArrayList<dates_data> load_data() {
        //Write JSON file
        JSONParser jsonParser = new JSONParser();
        ArrayList<dates_data> result = new ArrayList<>();


        JSONArray list = new JSONArray();
        try (FileWriter file = new FileWriter("output.json")) {
            file.write(list.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileReader reader = new FileReader("output.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray List = (JSONArray) obj;

            //Iterate over JSON array
            for (JSONObject object : (Iterable<JSONObject>) List) {
                JSONObject temp = (JSONObject) object.get("Data");
                String a = (String) temp.get("Time");
                String b = (String) temp.get("Date");
                String c = (String) temp.get("Task");
                Boolean d = Boolean.parseBoolean ((String) temp.get("Schedule"));
                Boolean e = Boolean.parseBoolean ((String) temp.get("Item"));

                result.add(new dates_data(a, b, c,d,e));
            }


            //list.forEach( data -> parseObject( (JSONObject) data) );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void save_data(ArrayList<dates_data> data) {  // This function saves items by writing them into the data file
        JSONObject Details = new JSONObject();
        JSONObject Object = new JSONObject();
        JSONArray list = new JSONArray();


        try{
            for (dates_data i : data) {
                Details.put("Time", i.getTime());
                Details.put("Date", i.getDate());
                Details.put("Task", i.getTask());
                Details.put("Schedule",i.isSchedule());
                Details.put("Item", i.isItem());

                Object.put("Data", Details);

                list.add(Object);
            }
        }catch (Exception error){
            error.printStackTrace();
        }


        try (FileWriter file = new FileWriter("saves.json")) {
            file.write(list.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();

        } //TODO
    }

 */
}
