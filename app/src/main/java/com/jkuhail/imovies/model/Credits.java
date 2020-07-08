package com.jkuhail.imovies.model;

public class Credits {
    String character, name;

    public Credits(String character, String name) {
        this.character = character;
        this.name = name;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
