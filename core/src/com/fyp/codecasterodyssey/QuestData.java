package com.fyp.codecasterodyssey;

import java.util.ArrayList;

import com.fyp.codecasterodyssey.LearningMetrics.Level;

public class QuestData {
    private Level level;
    private ArrayList<String> question = new ArrayList<>();
    private ArrayList<String> template;
    private ArrayList<String> solution;
    private ArrayList<String> hints;
    private String output;
    private String successMessage;

    public QuestData() {}

    public Level getLevel() {
        return level;
    }

    public ArrayList<String> getQuestion() {
        return question;
    }

    public ArrayList<String> getTemplate() {
        return template;
    }

    public ArrayList<String> getSolution() {
        return solution;
    }

    public ArrayList<String> getHints() {
        return hints;
    }

    public String getOutput() {
        return output;
    }

    public String getSuccessMessage() {
        return successMessage;
    }
}
