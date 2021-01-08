package com.example.codefest;

import java.time.*;

public class dates_data {
    private String time;
    private String date;
    private String task;
    private boolean schedule;
    private boolean  item;
    private Integer image;

    public dates_data(String time, String date, String task,boolean schedule, boolean item, Integer image) {
        this.time = time;
        this.date = date;
        this.task = task;
        this.schedule = schedule;
        this.item = item;
        this.image=image;
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

    public void setTask(String task) { this.task = task; }

    public boolean isSchedule() { return schedule; }

    public void setSchedule(boolean schedule) { this.schedule = schedule; }

    public boolean isItem() { return item; }

    public void setItem(boolean item) { this.item = item; }

    public Integer getImage() { return image;}

    public void setImage(Integer image) { this.image = image; }

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
                "time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", task='" + task + '\'' +
                ", schedule=" + schedule +
                ", item=" + item +
                '}';
    }
}
