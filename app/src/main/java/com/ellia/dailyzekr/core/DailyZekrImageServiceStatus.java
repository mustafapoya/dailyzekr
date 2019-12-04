package com.ellia.dailyzekr.core;

public enum DailyZekrImageServiceStatus {
    ON(1),
    OFF(0);
    int value;
    DailyZekrImageServiceStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
