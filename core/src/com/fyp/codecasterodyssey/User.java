package com.fyp.codecasterodyssey;

import java.util.ArrayList;

public class User {
    private String username;
    private ArrayList<String> completedPaths = new ArrayList<>();
    private ArrayList<String> collectedSpells = new ArrayList<>();
    private ArrayList<String> completedQuests = new ArrayList<>();
    private ArrayList<String> completedScenes = new ArrayList<>();
    private String currentPath;
    private String currentQuest;
    private float progressPercentage;
    
    public User() {}

    public User(String username) {
        this.username = username;
        this.currentPath = FileUtility.getAllPaths().get(0).getId();

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

    public ArrayList<String> getCompletedScenes() {
        return completedScenes;
    }

    public float getProgressPercentage() {
        return progressPercentage;
    }

    public void setCurrentPath(String path) {
        this.currentPath = path;
        FileUtility.updateUserJSON(this);
    }

    public void setCurrentQuest(String quest) {
        this.currentQuest = quest;
    }
    
    public void setProgressPercentage(float progressPercentage) {
        this.progressPercentage = progressPercentage;
    }
}
