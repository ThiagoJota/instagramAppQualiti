package com.pitang.mobile.infraestrutura.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;
import java.util.TreeSet;

public class SharePreferenceUtils {

    private static final String SHAREPREFERENCE_NAME = "SharePreference";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private Context context;

    public SharePreferenceUtils(Context context) {
        this.context = context;
        this.sharedPreferences = this.context.getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    public void put(String key, Integer i) {
        if (i != null) {
            editor.putInt(key, i).commit();
        }
    }

    public void put(String key, String s) {
        if (s != null) {
            editor.putString(key, s).commit();
        }
    }

    public void put(String key, Set<String> set) {
        if (set != null) {
            editor.putStringSet(key, set).commit();
        }
    }

    public void put(String key, Float f) {
        if (f != null) {
            editor.putFloat(key, f).commit();
        }
    }

    public void put(String key, Boolean b) {
        if (b != null) {
            editor.putBoolean(key, b).commit();
        }
    }

    public void put(String key, Long l) {
        if (l != null) {
            editor.putLong(key, l).commit();
        }
    }

    public void remover(String key) {
        if (key != null) {
            editor.remove(key).commit();
        }
    }

    public void removerTodos() {
        editor.clear().commit();
    }

    public String getString(String key) {
        return  sharedPreferences.getString(key, "");
    }

    public String getString(String key, String defaultString) {
        return sharedPreferences.getString(key, defaultString);
    }

    public Set<String> getStringSet(String key){
        Set<String> setDefault = new TreeSet<String>();
        return sharedPreferences.getStringSet(key, setDefault);
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public int getInt(String key, int defaultInt) {
        return sharedPreferences.getInt(key, defaultInt);
    }

    public long getLong(String key) {
        return sharedPreferences.getLong(key, 0l);
    }

    public boolean getBoolean(String key, boolean defaultBoolean) {
        return sharedPreferences.getBoolean(key, defaultBoolean);
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public float getFloat(String key) {
        return sharedPreferences.getFloat(key, 0f);
    }

    public boolean has(String key) {
        return sharedPreferences.contains(key);
    }

    private void commit() {
        editor.apply();
    }
}
