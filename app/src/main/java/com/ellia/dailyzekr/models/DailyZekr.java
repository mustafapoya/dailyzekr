package com.ellia.dailyzekr.models;

public class DailyZekr {
    private String day, zekr, farsi, hindi, turkey, english;

    public DailyZekr(){

    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setZekr(String zekr) {
        this.zekr = zekr;
    }

    public void setFarsi(String farsi) {
        this.farsi = farsi;
    }

    public void setHindi(String hindi) {
        this.hindi = hindi;
    }

    public void setTurkey(String turkey) {
        this.turkey = turkey;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getDay() {
        return day;
    }

    public String getZekr() {
        return zekr;
    }

    public String getFarsi() {
        return farsi;
    }

    public String getHindi() {
        return hindi;
    }

    public String getTurkey() {
        return turkey;
    }

    public String getEnglish() {
        return english;
    }

    @Override
    public String toString() {
        return "DailyZekr{" +
                "day='" + day + '\'' +
                ", zekr='" + zekr + '\'' +
                ", farsi='" + farsi + '\'' +
                ", hindi='" + hindi + '\'' +
                ", turkey='" + turkey + '\'' +
                ", english='" + english + '\'' +
                '}';
    }
}
