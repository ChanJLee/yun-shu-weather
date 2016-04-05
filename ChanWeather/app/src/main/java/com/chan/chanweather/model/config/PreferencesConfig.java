package com.chan.chanweather.model.config;

import android.content.Context;
import android.content.SharedPreferences;

import com.chan.chanweather.injector.annotation.ContextLife;

/**
 * Created by chan on 16/3/25.
 */
public class PreferencesConfig {
    private static final String FILE_NAME = "preferences_config";
    private static final String KEY_FIRST_USE = "first_use";
    private SharedPreferences m_sharedPreferences;

    public PreferencesConfig(@ContextLife("application") Context context) {
        m_sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public boolean isFirstUse() {
        return m_sharedPreferences.getBoolean(KEY_FIRST_USE, true);
    }

    public void setFirstUse(boolean isFirstUse) {
        SharedPreferences.Editor editor = m_sharedPreferences.edit();
        editor.putBoolean(KEY_FIRST_USE, isFirstUse);
        editor.commit();
    }
}
