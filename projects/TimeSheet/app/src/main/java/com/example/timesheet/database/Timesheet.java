package com.example.timesheet.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Timesheet implements Parcelable {


public Timesheet(){

}

    @NonNull
    @PrimaryKey
    private String days;
    private String task1;
    private String task2;
    private String task3;
    private String task4;
    private String task5;
    private String task6;

    private String hours1;
    private String hours2;
    private String hours3;
    private String hours4;
    private String hours5;
    private String hours6;

    private String totalHours;

    public Timesheet(Parcel in) {
        days = in.readString();
        task1 = in.readString();
        task2 = in.readString();
        task3 = in.readString();
        task4 = in.readString();
        task5 = in.readString();
        task6 = in.readString();
        hours1 = in.readString();
        hours2 = in.readString();
        hours3 = in.readString();
        hours4 = in.readString();
        hours5 = in.readString();
        hours6 = in.readString();
        totalHours = in.readString();
    }

    public static final Creator<Timesheet> CREATOR = new Creator<Timesheet>() {
        @Override
        public Timesheet createFromParcel(Parcel in) {
            return new Timesheet(in);
        }

        @Override
        public Timesheet[] newArray(int size) {
            return new Timesheet[size];
        }
    };

    public Timesheet(Timesheet time) {

    }

    public String getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(String totalHours) {
        this.totalHours = totalHours;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTask1() {
        return task1;
    }

    public void setTask1(String task1) {
        this.task1 = task1;
    }

    public String getTask2() {
        return task2;
    }

    public void setTask2(String task2) {
        this.task2 = task2;
    }

    public String getTask3() {
        return task3;
    }

    public void setTask3(String task3) {
        this.task3 = task3;
    }

    public String getTask4() {
        return task4;
    }

    public void setTask4(String task4) {
        this.task4 = task4;
    }

    public String getTask5() {
        return task5;
    }

    public void setTask5(String task5) {
        this.task5 = task5;
    }

    public String getTask6() {
        return task6;
    }

    public void setTask6(String task6) {
        this.task6 = task6;
    }

    public String getHours1() {
        return hours1;
    }

    public void setHours1(String hours1) {
        this.hours1 = hours1;
    }

    public String getHours2() {
        return hours2;
    }

    public void setHours2(String hours2) {
        this.hours2 = hours2;
    }

    public String getHours3() {
        return hours3;
    }

    public void setHours3(String hours3) {
        this.hours3 = hours3;
    }

    public String getHours4() {
        return hours4;
    }

    public void setHours4(String hours4) {
        this.hours4 = hours4;
    }

    public String getHours5() {
        return hours5;
    }

    public void setHours5(String hours5) {
        this.hours5 = hours5;
    }

    public String getHours6() {
        return hours6;
    }

    public void setHours6(String hours6) {
        this.hours6 = hours6;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(days);
        dest.writeString(task1);
        dest.writeString(task2);
        dest.writeString(task3);
        dest.writeString(task4);
        dest.writeString(task5);
        dest.writeString(task6);
        dest.writeString(hours1);
        dest.writeString(hours2);
        dest.writeString(hours3);
        dest.writeString(hours4);
        dest.writeString(hours5);
        dest.writeString(hours6);
        dest.writeString(totalHours);
    }
}
