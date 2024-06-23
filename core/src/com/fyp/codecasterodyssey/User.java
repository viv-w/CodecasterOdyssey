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
    private LearningMetrics learningMetrics = new LearningMetrics();

    private long playtime;
    private String lastSaved;
    
    public User() {}

    public User(String username) {
        this.username = username;
        this.currentPath = FileUtility.getAllPaths().get(0).getId();
        this.learningMetrics.initialiseData();
        this.lastSaved = "";
        FileUtility.updateUserJSON(this);
    }

    // setters
    public void setCurrentQuest(String quest) {
        this.currentQuest = quest;
        FileUtility.updateUserJSON(this);
    }

    public void setCurrentPath(String path) {
        this.currentPath = path;
        FileUtility.updateUserJSON(this);
    }
    
    public void addSpell(String spellId) {
        if (!collectedSpells.contains(spellId)) collectedSpells.add(spellId);
        FileUtility.updateUserJSON(this);
    }

    public void addQuest(String questId) {
        if (!completedQuests.contains(questId)) completedQuests.add(questId);
        FileUtility.updateUserJSON(this);
    }

    public void addCompletedScene(String sceneId) {
        if (!completedScenes.contains(sceneId)) completedScenes.add(sceneId);
        FileUtility.updateUserJSON(this);
    }
    
    public void setLearningMetrics(LearningMetrics learningMetrics) {
        this.learningMetrics = learningMetrics;
    }

    public void addPlaytime(long playtime) {
        this.playtime += playtime;
        FileUtility.updateUserJSON(this);
    }

    public void setLastSaved(String lastSaved) {
        this.lastSaved = lastSaved;
    }

    // getters
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

    public LearningMetrics getLearningMetrics() {
        return learningMetrics;
    }

    public long getPlaytime() {
        return playtime;
    }

    public String getFormattedPlaytime() {
        long seconds = playtime / 1000;
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long remainingSeconds = seconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
    }

    public String getLastSaved() {
        return lastSaved;
    }

    public int getSpellPercentage(int totalSpells) {
        float percentage = 0;
        if(!collectedSpells.isEmpty()) {
            percentage = ((float) collectedSpells.size() / totalSpells) * 100;
        }

        return Math.round(percentage);
    }

    public int getQuestPercentage(int totalQuests) {
        float percentage = 0;
        if (!completedQuests.isEmpty()) {
            percentage = ((float) completedQuests.size() / totalQuests) * 100;
        }

        return Math.round(percentage);
    }

    public int getProgressPercentage(int totalSpells, int totalQuests) {
        float percentage = 0;
        percentage = ((float) (completedQuests.size() + collectedSpells.size()) / (totalQuests + totalSpells)) * 100;

        return Math.round(percentage);
    }

}
