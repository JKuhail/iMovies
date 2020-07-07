package com.jkuhail.imovies.model;

public enum MediaType{
    MOVIE("movie"),
    TV("tv");

    String value;

    MediaType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
