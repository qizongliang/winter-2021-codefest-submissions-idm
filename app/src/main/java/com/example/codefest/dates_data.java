package com.example.codefest;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.time.*;

public class dates_data implements Parcelable{
    //rework the class and fix the parsing issue.
    private int time_hours;
    private int time_minutes;
    private String month;
    private int year;
    private int day;

    private String task;
    private int schedule;
    private boolean  item;
    private Integer image;
    private String id;

    public dates_data(int time_hours, int time_minutes, String month, int year, int day, String task, int schedule, boolean item, Integer image, String id) {
        this.time_hours = time_hours;
        this.time_minutes = time_minutes;
        this.month = month;
        this.year = year;
        this.day = day;
        this.task = task;
        this.schedule = schedule;
        this.item = item;
        this.image = image;
        this.id = id;
    }

    protected dates_data(Parcel in) {
        time_hours = in.readInt();
        time_minutes = in.readInt();
        month = in.readString();
        year = in.readInt();
        day = in.readInt();
        task = in.readString();
        schedule = in.readInt();
        item = in.readByte() != 0;
        if (in.readByte() == 0) {
            image = null;
        } else {
            image = in.readInt();
        }
        id = in.readString();
    }

    public static final Creator<dates_data> CREATOR = new Creator<dates_data>() {
        @Override
        public dates_data createFromParcel(Parcel in) {
            return new dates_data(in);
        }

        @Override
        public dates_data[] newArray(int size) {
            return new dates_data[size];
        }
    };

    public int getTime_hours() {
        return time_hours;
    }

    public void setTime_hours(int time_hours) {
        this.time_hours = time_hours;
    }

    public int getTime_minutes() {
        return time_minutes;
    }

    public void setTime_minutes(int time_minutes) {
        this.time_minutes = time_minutes;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) { this.task = task; }

    public int getSchedule() { return schedule; }

    public void setSchedule(int schedule) { this.schedule = schedule; }

    public boolean isItem() { return item; }

    public void setItem(boolean item) { this.item = item; }

    public Integer getImage() { return image;}

    public void setImage(Integer image) { this.image = image; }

    public String getMonth() {return month;}

    public void setMonth(String month) {this.month = month;}

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(time_hours);
        dest.writeInt(time_minutes);
        dest.writeString(month);
        dest.writeInt(year);
        dest.writeInt(day);
        dest.writeString(task);
        dest.writeInt(schedule);
        dest.writeByte((byte) (item ? 1 : 0));
        if (image == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(image);
        }
        dest.writeString(id);
    }
}
