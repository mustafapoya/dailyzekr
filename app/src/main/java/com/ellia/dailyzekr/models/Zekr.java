package com.ellia.dailyzekr.models;

import androidx.annotation.NonNull;

public class Zekr {
    private String zekr, trans;
    private int count = 0;

    public Zekr() {

    }

    public Zekr(String zekr, String trans) {
        this.zekr = zekr;
        this.trans = trans;
    }

    public void setZekr(String zekr) {
        this.zekr = zekr;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getZekr() {
        return zekr;
    }

    public String getTrans() {
        return trans;
    }

    public int getCount() {
        return count;
    }

    @NonNull
    @Override
    public String toString() {
        return getZekr() + " , " + getTrans();
    }

}
