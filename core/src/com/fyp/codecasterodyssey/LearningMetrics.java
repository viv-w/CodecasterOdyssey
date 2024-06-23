package com.fyp.codecasterodyssey;

public class LearningMetrics {
    private int totalAttempts;
    private int totalCorrect;
    private long totalTimeTaken;
    private int totalHintsUsed;
    private Level level;

    public LearningMetrics() {}

    public enum Level {
        HIGH,
        MEDIUM,
        LOW
    }

    public void initialiseData() {
        totalAttempts = 0;
        totalCorrect = 0;
        totalTimeTaken = 0;
        totalHintsUsed = 0;
        level = Level.MEDIUM;
    }

    public void logQuest(boolean correct, boolean hintsUsed, long timeTaken, int totalQuestsCompleted) {
        totalAttempts++;
        if(correct) totalCorrect++;
        if(hintsUsed) totalHintsUsed++;
        totalTimeTaken += timeTaken;
        updateLevel(totalQuestsCompleted);
    }

    public Level updateLevel(int totalQuestsCompleted) {
        double accuracy = (double) totalCorrect / totalAttempts;
        double avgTime = (double) totalTimeTaken / totalAttempts; // 10 min avg
        int maxHints = Math.max(3, totalQuestsCompleted / 2);
        
        if(accuracy >= 0.8 && avgTime > 600000 && totalHintsUsed <= maxHints) {
            return Level.HIGH;
        } else if (accuracy >= 0.5) {
            return Level.MEDIUM;
        } else {
            return Level.LOW;
        }
    }

    // getters/setters for JSON 
    public Level getLevel() {
        return level;
    }

    public int getTotalAttempts() {
        return totalAttempts;
    }

    public int getTotalCorrect() {
        return totalCorrect;
    }

    public long getTotalTimeTaken() {
        return totalTimeTaken;
    }

    public int getTotalHintsUsed() {
        return totalHintsUsed;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setTotalAttempts(int totalAttempts) {
        this.totalAttempts = totalAttempts;
    }

    public void setTotalTimeTaken(long totalTimeTaken) {
        this.totalTimeTaken = totalTimeTaken;
    }

    public void setTotalHintsUsed(int totalHintsUsed) {
        this.totalHintsUsed = totalHintsUsed;
    }
}


