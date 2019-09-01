package com.nayam.itunesdiscover.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.nayam.itunesdiscover.utility.Constants;
import com.nayam.itunesdiscover.utility.Utility;

/**
 * <h1>SharedPreferenceManager</h1>
 *
 * @author  May Ann Palencia
 * @version 1.0
 * @since   2019-09-01
 *
 * Copyright © 2019 Sidekick Digital Limited. All rights reserved.
 */
public class SharedPreferenceManager {

    private static final String PREF_KEY_LAST_ACTIVITY = "PREF_KEY_LAST_ACTIVITY";

    private static final String PREF_KEY_LAST_TRACK_SAVED = "PREF_KEY_LAST_TRACK_SAVED";

    private static final String PREF_KEY_LAST_SEARCH_DATE = "PREF_KEY_LAST_SEARCH_DATE";

    private static final String PREF_KEY_SEARCH_MEDIA_TYPE_POSITION = "PREF_KEY_SEARCH_MEDIA_TYPE_POSITION";

    private static final String PREF_KEY_SEARCH_COUNTRY = "PREF_KEY_SEARCH_COUNTRY";

    private static final String PREF_KEY_SEARCH_TERM = "PREF_KEY_SEARCH_TERM";

    private static SharedPreferences sharedPreferences;

    public static void initialize(Context context){
        sharedPreferences = getSharedPreference(context);
    }

    public static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static String getLastActivity() {
        return sharedPreferences.getString(PREF_KEY_LAST_ACTIVITY, "");
    }

    public static void setLastActivity(String activity) {
        sharedPreferences.edit().putString(PREF_KEY_LAST_ACTIVITY, activity).apply();
    }

    public static String getLastTrackSaved() {
        return sharedPreferences.getString(PREF_KEY_LAST_TRACK_SAVED, "");
    }

    public static void setLastTrackSaved(String track) {
        sharedPreferences.edit().putString(PREF_KEY_LAST_TRACK_SAVED, track).apply();
    }

    public static String getLastSearchDate() {
        return sharedPreferences.getString(PREF_KEY_LAST_SEARCH_DATE, "");
    }

    public static void setLastSearchDate(String date) {
        sharedPreferences.edit().putString(PREF_KEY_LAST_SEARCH_DATE, date).apply();
    }

    public static int getMediaTypePosition() {
        return sharedPreferences.getInt(PREF_KEY_SEARCH_MEDIA_TYPE_POSITION, Constants.DEFAULT_MEDIA_POSITION);
    }

    public static void setMediaTypePosition(int position) {
        sharedPreferences.edit().putInt(PREF_KEY_SEARCH_MEDIA_TYPE_POSITION, position).apply();
    }

    public static String getCountry() {
        return sharedPreferences.getString(PREF_KEY_SEARCH_COUNTRY, Constants.DEFAULT_COUNTRY);
    }

    public static void setCountry(String country) {
        sharedPreferences.edit().putString(PREF_KEY_SEARCH_COUNTRY, country).apply();
    }

    public static String getTerm() {
        return sharedPreferences.getString(PREF_KEY_SEARCH_TERM, Constants.DEFAULT_TERM);
    }

    public static void setTerm(String term) {
        sharedPreferences.edit().putString(PREF_KEY_SEARCH_TERM, term).apply();
    }
}
