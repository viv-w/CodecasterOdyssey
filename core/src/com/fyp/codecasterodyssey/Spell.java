package com.fyp.codecasterodyssey;

import java.util.ArrayList;

public class Spell {
    private String id;
    private String name;
    private ArrayList<String> content = new ArrayList<>();
    // private ArrayList<String> quests = new ArrayList<>();
    
    public Spell() {}

    public Spell(String id, ArrayList<String> content) {
        this.id = id;
        this.content = content;
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

    // public ArrayList<String> getQuests() {
    //     return quests;
    // }
}
