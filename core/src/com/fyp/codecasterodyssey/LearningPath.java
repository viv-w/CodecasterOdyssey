package com.fyp.codecasterodyssey;

import java.util.ArrayList;

public class LearningPath {
    private String id;
    private String description;
    private ArrayList<Quest> quests = new ArrayList<>();
    private ArrayList<Spell> spells = new ArrayList<>();

    public LearningPath() {}

    public LearningPath(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public int getNum() {
        return Integer.parseInt(id.substring(4));
    }

    public String getDesc() {
        return description;
    }

    public ArrayList<Quest> getQuests() {
        return quests;
    }

    public ArrayList<Spell> getSpells() {
        return spells;
    }
}
