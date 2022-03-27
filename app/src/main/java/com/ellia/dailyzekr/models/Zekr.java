package com.ellia.dailyzekr.models;

import androidx.annotation.NonNull;

public class Zekr {
    private String zekr, transFarsi, transEnglish, transHindi, transTurkey;
    private int count = 0;

    public Zekr() {

    }

    public Zekr(String zekr, String transFarsi, String transEnglish, String transHindi, String transTurkey) {
        this.zekr = zekr;
        this.transFarsi = transFarsi;
        this.transEnglish = transEnglish;
        this.transHindi = transHindi;
        this.transTurkey = transTurkey;
    }

    public void setZekr(String zekr) {
        this.zekr = zekr;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public String getZekr() {
        return zekr;
    }

    public String getTransFarsi() {
        return transFarsi;
    }

    public void setTransFarsi(String transFarsi) {
        this.transFarsi = transFarsi;
    }

    public String getTransEnglish() {
        return transEnglish;
    }

    public void setTransEnglish(String transEnglish) {
        this.transEnglish = transEnglish;
    }

    public String getTransHindi() {
        return transHindi;
    }

    public void setTransHindi(String transHindi) {
        this.transHindi = transHindi;
    }

    public String getTransTurkey() {
        return transTurkey;
    }

    public void setTransTurkey(String transTurkey) {
        this.transTurkey = transTurkey;
    }

    @Override
    public String toString() {
        return "Zekr{" +
                "zekr='" + zekr + '\'' +
                ", transFarsi='" + transFarsi + '\'' +
                ", transEnglish='" + transEnglish + '\'' +
                ", transHindi='" + transHindi + '\'' +
                ", transTurkey='" + transTurkey + '\'' +
                '}';
    }
}
