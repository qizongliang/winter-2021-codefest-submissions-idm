package com.example.codefest;

public class Schedule {
    private String dayofweek;
    private  String task;
    private String time;

    public Schedule(String dayofweek, String task, String time) {
        this.dayofweek = dayofweek;
        this.task = task;
        this.time = time;
    }

    public String getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(String dayofweek) {
        this.dayofweek = dayofweek;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



}
