package com.worknote.worknote;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String weekDate;
    private String lastUpdated;
    private String mon;
    private String tue;
    private String wed;
    private String thu;
    private String fri;
    private String sat;
    private String sun;
    private Boolean m;
    private Boolean tu;
    private Boolean w;
    private Boolean th;
    private Boolean f;
    private Boolean sa;
    private Boolean su;

    public Note(String weekDate,
                String lastUpdated,
                String mon,
                String tue,
                String wed,
                String thu,
                String fri,
                String sat,
                String sun,
                Boolean m,
                Boolean tu,
                Boolean w,
                Boolean th,
                Boolean f,
                Boolean sa,
                Boolean su) {
        this.weekDate = weekDate;
        this.lastUpdated = lastUpdated;
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thu = thu;
        this.fri = fri;
        this.sat = sat;
        this.sun = sun;
        this.m = m;
        this.tu = tu;
        this.w = w;
        this.th = th;
        this.f = f;
        this.sa = sa;
        this.su = su;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Boolean getM() {
        return m;
    }

    public Boolean getTu() {
        return tu;
    }

    public Boolean getW() {
        return w;
    }

    public Boolean getTh() {
        return th;
    }

    public Boolean getF() {
        return f;
    }

    public Boolean getSa() {
        return sa;
    }

    public Boolean getSu() {
        return su;
    }

    public String getWeekDate() {
        return weekDate;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public String getMon() {
        return mon;
    }

    public String getTue() {
        return tue;
    }

    public String getWed() {
        return wed;
    }

    public String getThu() {
        return thu;
    }

    public String getFri() {
        return fri;
    }

    public String getSat() {
        return sat;
    }

    public String getSun() {
        return sun;
    }
}
