package com.example.codefest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class datesRecViewAdapter extends RecyclerView.Adapter<datesRecViewAdapter.ViewHolder>{


    private OnItemClickListener Listener;
    private OnLongClickListener longClickListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public interface OnLongClickListener{
        void onItemLongClicked(int position);
    }

    public void setOnItemClickListener(OnItemClickListener Listener){
        this.Listener = Listener;
    }

    public void setOnItemLongClickListener(OnLongClickListener Listener){
        this.longClickListener = Listener;
    }

    ArrayList<dates_data> database;
    Context context;




    public datesRecViewAdapter(ArrayList<dates_data> database, dates activity){
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
        dates_data temp = database.get(position);
        holder.title.setText(temp.getTask());
        holder.description.setText("Date: "+ temp.getDate() +" Time"+ temp.getTime());
        holder.icon.setImageResource(temp.getImage());

        /*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"clicked!",Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context,"clicked!",Toast.LENGTH_SHORT).show();
                return true;
            }
        });

         */


        //String item = database[position].getTask();
        //holder.time.setText(data.get(position).getTask());
        //holder.bind(item);
    }
    @Override
    public int getItemCount() {
        return database.size();
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            Listener.onItemClick(position);
                        }
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // notify the listener
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });

        }
        //update the view inside of the view holder with this data
        }

    }
