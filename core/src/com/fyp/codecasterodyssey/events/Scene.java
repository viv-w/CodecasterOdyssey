package com.fyp.codecasterodyssey.events;

import java.util.ArrayList;

public class Scene {
    
    private String id;
    private String nextId; 
    private String spell;
    private String quest;
    private ArrayList<Event> events;
    private boolean isCompleted;

    public Scene() {}

    public String getId() {
        return id;
    }

    public String getNextId() {
        return nextId;
    }
    
    public String getSpell() {
        return spell;
    }

    public String getQuest() {
        return quest;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setCompleted(boolean b) {
        isCompleted = b;
    }

    public boolean isComplete() {
        return isCompleted;
    }
}
