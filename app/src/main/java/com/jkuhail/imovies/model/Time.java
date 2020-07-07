package com.jkuhail.imovies.model;

public enum Time{
    DAY("day"),
    WEEK("week");

    String value;

    Time(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}