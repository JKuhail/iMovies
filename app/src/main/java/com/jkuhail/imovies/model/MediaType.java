package com.jkuhail.imovies.model;

public enum MediaType{
    MOVIE("movie"),
    TV("tv"),
    ALL("all");

    String value;

    MediaType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
