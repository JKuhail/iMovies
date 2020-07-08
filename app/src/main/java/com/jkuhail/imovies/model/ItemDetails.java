package com.jkuhail.imovies.model;

public class ItemDetails {
    String name , overview;

    public ItemDetails(String name, String overview) {
        this.name = name;
        this.overview = overview;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

}
