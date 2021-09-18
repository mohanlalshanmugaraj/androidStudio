package com.example.zetranexample.database;

import android.content.Context;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.zetranexample.R;

@Entity
public class PunchInOut {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String name;
    private String date;
    private String punchIn;
    private String punchOut;
    private Integer empId;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPunchIn() {
        return punchIn;
    }

    public void setPunchIn(String punchIn) {
        this.punchIn = punchIn;
    }

    public String getPunchOut() {
        return punchOut;
    }

    public void setPunchOut(String punchOut) {
        this.punchOut = punchOut;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPunchInStr(Context context) {
        return String.format("%s : %s", context.getString(R.string.punch_in), punchIn);
    }

    public String getPunchOutStr(Context context) {
        return String.format("%s : %s", context.getString(R.string.punch_out),
                punchOut == null ? "-" : punchOut);
    }
}
