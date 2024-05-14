package com.fyp.codecasterodyssey;

import java.util.ArrayList;

public class Spell {
    private String id;
    private ArrayList<String> content = new ArrayList<>();
    
    public Spell() {}

    public Spell(String id, ArrayList<String> content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public ArrayList<String> getContent() {
        return content;
    }
}
