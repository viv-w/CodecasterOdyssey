package com.fyp.codecasterodyssey;

public class LearningMetrics {
    private int totalCompleted;
    private int totalAttempts;
    private long totalTimeTaken;
    private int totalHintsUsed;
    private Level level;

    public LearningMetrics() {}

    public enum Level {
        EASY,
        NORMAL,
        HARD
    }

    public void initialiseData() {
        totalAttempts = 0;
        totalCompleted = 0;
        totalTimeTaken = 0;
        totalHintsUsed = 0;
        level = Level.NORMAL;
    }

    public void log(int attempts, int hintsUsed, long timeTaken, int totalQuestsCompleted) {
        totalCompleted++;
        totalAttempts += attempts;
        totalHintsUsed += hintsUsed;
        totalTimeTaken += timeTaken;

        updateLevel(totalQuestsCompleted);
    }

    public Level updateLevel(int totalQuestsCompleted) {
        double accuracy = (double) totalCompleted / totalAttempts;
        double avgTime = (double) totalTimeTaken / totalAttempts; // 10 min avg
        int maxHints = totalQuestsCompleted * 3;
        
        if(accuracy >= 0.8 && avgTime < 600000 && totalHintsUsed <= maxHints) {
            return Level.HARD;
        } else if (accuracy >= 0.5) {
            return Level.NORMAL;
        } else {
            return Level.EASY;
        }
    }

    // getters/setters for JSON 
    public Level getLevel() {
        return level;
    }

    public int getTotalAttempts() {
        return totalAttempts;
    }

    public int getTotalCompleted() {
        return totalCompleted;
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


