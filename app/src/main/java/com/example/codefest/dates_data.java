package com.example.codefest;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.time.*;

public class dates_data implements Parcelable {
    private String time;
    private String date;
    private String task;
    private int schedule;
    private boolean  item;
    private Integer image;
    private String id;


    public dates_data(String time, String date, String task,int schedule, boolean item, Integer image, String id) {
        this.time = time;
        this.date = date;
        this.task = task;
        this.schedule = schedule;
        this.item = item;
        this.image=image;
        this.id = id;
    }


    protected dates_data(Parcel in) {
        time = in.readString();
        date = in.readString();
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

    public int getSchedule() { return schedule; }

    public void setSchedule(int schedule) { this.schedule = schedule; }

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
        return temp.substring(3);
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(time);
        dest.writeString(date);
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
