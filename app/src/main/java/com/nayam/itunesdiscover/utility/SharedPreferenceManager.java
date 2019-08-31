package com.nayam.itunesdiscover.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

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

    public static final String LAST_SEARCH_DATE = "last_search_date";
    public static final String LAST_OPEN_ACTIVITY = "last_open_activity";

    public static final String SEARCH_MEDIA_TYPE = "media_type";
    public static final String SEARCH_MEDIA_TYPE_POSITION = "media_type_position";

    public static final String SEARCH_COUNTRY = "country";
    public static final String SEARCH_COUNTRY_FLAG = "country_flag";

    public static final String SEARCH_TERM = "term";

    private static SharedPreferences.Editor getSharedPreferenceEditor(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).edit();
    }

    public static void setPreference(Context context, String preferenceName, String preferenceValue){
        getSharedPreferenceEditor(context).putString(preferenceName, preferenceValue).apply();
    }

    public static void setPreference(Context context, String preferenceName, boolean preferenceValue){
        getSharedPreferenceEditor(context).putBoolean(preferenceName, preferenceValue).apply();
    }

    public static void setPreference(Context context, String preferenceName, int preferenceValue){
        getSharedPreferenceEditor(context).putInt(preferenceName, preferenceValue).apply();
    }

    public static String getStringPreference(Context context, String preferenceName){
        return PreferenceManager.getDefaultSharedPreferences(context).getString(preferenceName, "");
    }

    public static boolean getBooleanPreference(Context context, String preferenceName){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(preferenceName, false);
    }

    public static int getIntPreference(Context context, String preferenceName){
        return PreferenceManager.getDefaultSharedPreferences(context).getInt(preferenceName, 0);
    }

}
