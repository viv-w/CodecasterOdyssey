package com.fyp.codecasterodyssey;

import java.util.ArrayList;

public class Quest {
    private String id;
    private String name;
    private ArrayList<QuestData> questData = new ArrayList<>();

    public Quest() {}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<QuestData> getQuestData() {
        return questData;
    }

}
