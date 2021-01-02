package com.example.codefest;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class datesRecViewAdapter extends RecyclerView.Adapter<datesRecViewAdapter.ViewHolder>{
    private ArrayList<dates_data> data = new ArrayList<>();


    public datesRecViewAdapter(){

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dates_list_item,parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { //set the text for the recycler view
        holder.time.setText(data.get(position).getTime_hour());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(ArrayList<dates_data> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
        }
    }


}
