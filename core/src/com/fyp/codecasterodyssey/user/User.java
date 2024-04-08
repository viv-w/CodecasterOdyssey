package com.fyp.codecasterodyssey.user;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class User {
    private static final String PREFS_PROFILE = "Profile_";
    private static final String PREF_USERNAME = "username";
    private static final String PREF_LEARNINGPATH = "learningPath";

    private Preferences preferences;

    public User(String username) {
        preferences = Gdx.app.getPreferences(PREFS_PROFILE + username);

    }

    public String getUsername() {
        return preferences.getString(PREF_USERNAME, "nil");
    }

    public String getLearningPath() {
        return preferences.getString(PREF_LEARNINGPATH, "nil");
    }

    public void setUsername(String username) {
        preferences.putString(PREF_USERNAME, username);
        preferences.flush();
    }

    // a temporary(?) indicator to prove new player or nah
    public void setLearningPath(String learningPath) {
        preferences.putString(PREF_LEARNINGPATH, learningPath);
        preferences.flush();
    }

    public void setupNewUser(String username) {
        this.setUsername(username);
    }
}
