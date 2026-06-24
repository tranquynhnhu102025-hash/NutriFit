package com.example.nutrifit.data.local;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {

    private static final String PREF_NAME = "nutrifit_prefs";
    private final SharedPreferences prefs;

    public SharedPrefs(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveLogin(int userId, String name, String email) {
        prefs.edit()
                .putBoolean("is_logged_in", true)
                .putInt("user_id", userId)
                .putString("user_name", name)
                .putString("user_email", email)
                .apply();
    }

    public boolean isLoggedIn() {
        return prefs.getBoolean("is_logged_in", false);
    }

    public int getUserId() {
        return prefs.getInt("user_id", -1);
    }

    public String getUserName() {
        return prefs.getString("user_name", "");
    }

    public String getUserEmail() {
        return prefs.getString("user_email", "");
    }

    public void logout() {
        prefs.edit().clear().apply();
    }
}
