package com.nayam.itunesdiscover;

import android.app.Application;
import android.content.Context;

import com.nayam.itunesdiscover.data.local.SharedPreferenceManager;

/**
 * Application Class
 *
 * @author May Ann Palencia on 01/09/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @since 1.0
 * Copyright (c) 2019
 */
public class ItunesDiscoverApplication extends Application {

    /**
     * Instance of our application
     */
    private static ItunesDiscoverApplication instance;

    /**
     *
     * @return Context of our application
     */
    public static Context getContext(){
        return instance;
    }

    @Override
    public void onCreate() {

        instance = this;

        super.onCreate();

        // Initialize SharedPreferences
        SharedPreferenceManager.initialize(this);
    }

}
