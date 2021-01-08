package com.example.codefest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class datesRecViewAdapter extends RecyclerView.Adapter<datesRecViewAdapter.ViewHolder>{

    dates_data [] database;
    Context context;

    public datesRecViewAdapter(dates_data[] database, dates activity){
        this.database= database;
        this.context = activity;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { //set the text for the recycler view
        dates_data temp = database[position];
        holder.title.setText(temp.getTask());
        holder.description.setText("Date: "+ temp.getDate() +" Time"+ temp.getTime());
        holder.icon.setImageResource(temp.getImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"clicked!",Toast.LENGTH_SHORT).show();
            }
        });
        //String item = database[position].getTask();
        //holder.time.setText(data.get(position).getTask());
        //holder.bind(item);
    }
    @Override
    public int getItemCount() {
        return database.length;
    }
    /*
    public void setData(ArrayList<dates_data> data) {
        this.data = data;
        notifyDataSetChanged();
    }

     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView title;
        TextView description; // I messed up here and put a unrelated name for the variable

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
        }
        //update the view inside of the view holder with this data
        }

    }
