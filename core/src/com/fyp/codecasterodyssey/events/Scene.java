package com.fyp.codecasterodyssey.events;

import java.util.ArrayList;

public class Scene {
    
    private String id;
    private String nextId; // TODO decide how to trigger the next scene. immediately after a scene? or... need a trigger... e.g. talk to NPC (but...)
    private String spell;
    private String quest;
    private ArrayList<Event> events;

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
}
