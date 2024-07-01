package com.fyp.codecasterodyssey;

public class QuestLog {
    private String questId;
    private long timeTaken;
    private boolean hintUsed;
    private boolean isCompleted;

    public QuestLog() {}

    public QuestLog(String questId, long timeTaken, boolean hintUsed, boolean isCompleted) {
        this.questId = questId;
        this.timeTaken = timeTaken;
        this.hintUsed = hintUsed;
        this.isCompleted = isCompleted;
    }

    public void setQuestId(String questId) {
        this.questId = questId;
    }

    public void setTimeTaken(long timeTaken) {
        this.timeTaken = timeTaken;
    }

    public void setHintUsed(boolean hintUsed) {
        this.hintUsed = hintUsed;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String getQuestId() {
        return questId;
    }

    public long getTimeTaken() {
        return timeTaken;
    }

    public boolean isHintUsed() {
        return hintUsed;
    }

    public boolean isCompleted() {
        return isCompleted;
    }


}
