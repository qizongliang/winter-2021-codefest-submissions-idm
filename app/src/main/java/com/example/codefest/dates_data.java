package com.example.codefest;

import java.time.*;

public class dates_data {
    private String time;
    private String date;
    private String task;

    public dates_data(String time, String date, String task) {
        this.time = time;
        this.date = date;
        this.task = task;
    }

    public String getTime() { return time; }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getTime_hour() {
        String temp = this.getTime();
        return temp.substring(0,2);
    }

    public String  getTime_minute(){
        String temp = this.getTime();
        return temp.substring(2);
    }

    public String getDate_month(){
        return this.getDate().substring(0,3);
    }
    public String getDate_year(){
        return this.getDate().substring(6);
    }
    public String getDate_date(){
        return this.getDate().substring(4,6);
    }





    @Override
    public String toString() {
        return "dates_data{" +
                "time=" + time +
                ", date=" + date +
                ", task='" + task + '\'' +
                '}';
    }
}
