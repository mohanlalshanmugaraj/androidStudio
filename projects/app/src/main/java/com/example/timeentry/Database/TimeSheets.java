package com.example.timeentry.Database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TimeSheets {
    @PrimaryKey(autoGenerate = false)

    @NonNull
    private String task1;
    private String task2;
    private String task3;
    private String task4;
    private String task5;
    private int hour1;
    private int hour2;
    private int hour3;
    private int hour4;
    private int hour5;
    private int totalhours;
    private int date;

    @NonNull
    public String getTask1() {
        return task1;
    }

    public void setTask1(@NonNull String task1) {
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

    public int getHour1() {
        return hour1;
    }

    public void setHour1(int hour1) {
        this.hour1 = hour1;
    }

    public int getHour2() {
        return hour2;
    }

    public void setHour2(int hour2) {
        this.hour2 = hour2;
    }

    public int getHour3() {
        return hour3;
    }

    public void setHour3(int hour3) {
        this.hour3 = hour3;
    }

    public int getHour4() {
        return hour4;
    }

    public void setHour4(int hour4) {
        this.hour4 = hour4;
    }

    public int getHour5() {
        return hour5;
    }

    public void setHour5(int hour5) {
        this.hour5 = hour5;
    }

    public int getTotalhours() {
        return totalhours;
    }

    public void setTotalhours(int totalhours) {
        this.totalhours = totalhours;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
