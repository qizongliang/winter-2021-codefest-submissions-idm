package com.example.codefest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.widget.RelativeLayout;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class dates extends AppCompatActivity {
    @SuppressWarnings("unchecked")
    private RecyclerView datesRecView;
    private RelativeLayout relativeLayout;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates);

        datesRecView = findViewById(R.id.datesRecView);
        ArrayList<dates_data> database = new ArrayList<>();
        try {
            database = load_data();
        }catch(Exception error){
            error.printStackTrace();
        }
        datesRecViewAdapter adapter = new datesRecViewAdapter();

        adapter.setData(database);

        datesRecView.setAdapter(adapter);
        datesRecView.setLayoutManager(new LinearLayoutManager(this));


    }
    //Todo add a button and implement an listener to add task to that date.
    //Todo remove a item on the schedule

    @RequiresApi(api = Build.VERSION_CODES.N)
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
                JSONObject temp = (JSONObject) object.get("Item");
                String a = (String) temp.get("Time");
                String b = (String) temp.get("Date");
                String c = (String) temp.get("Task");
                result.add(new dates_data(a, b, c));
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

        for (dates_data i : data) {
            Details.put("Time", i.getTime());
            Details.put("Date", i.getDate());
            Details.put("Task", i.getTask());
            Object.put("Item", Details);

            list.add(Object);
        }

        try (FileWriter file = new FileWriter("saves.json")) {
            file.write(list.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();

        } //TODO
    }
}
