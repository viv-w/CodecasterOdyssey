package com.fyp.codecasterodyssey;

import java.util.ArrayList;

public class Quest {
    private String id;
    private String name;
    private ArrayList<String> content = new ArrayList<>();

    public Quest() {}

    public Quest(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getContent() {
        return content;
    }
}
