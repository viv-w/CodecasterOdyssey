package com.fyp.codecasterodyssey;

import java.util.ArrayList;

public class User {
    private String username;
    private ArrayList<String> completedPaths = new ArrayList<String>();
    private ArrayList<String> collectedSpells = new ArrayList<String>();
    private ArrayList<String> completedQuests = new ArrayList<String>();
    private String currentPath;
    private String currentQuest;
    
    public User() {}

    public User(String username) {
        this.username = username;
        this.currentPath = FileUtility.loadPaths().get(0).getId();

        FileUtility.updateUserJSON(this);
    }

    public String getUsername() {
        return username;
    }

    public String getCurrentPath() {
        return currentPath;
    }

    public String getCurrentQuest() {
        return currentQuest;
    }

    public ArrayList<String> getCompletedPaths() {
        return completedPaths;
    }

    public ArrayList<String> getCollectedSpells() {
        return collectedSpells;
    }

    public ArrayList<String> getCompletedQuests() {
        return completedQuests;
    }

    public void setCurrentPath(String path) {
        this.currentPath = path;
        FileUtility.updateUserJSON(this);
    }

    public void setCurrentQuest(String quest) {
        this.currentQuest = quest;
    }
    
}
