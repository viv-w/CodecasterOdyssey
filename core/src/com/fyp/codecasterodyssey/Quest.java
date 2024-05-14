package com.fyp.codecasterodyssey;

public class Quest {
    private String id;
    private String name;

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
}
