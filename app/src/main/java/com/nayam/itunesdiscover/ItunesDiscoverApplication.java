package com.nayam.itunesdiscover;

import android.app.Application;
import android.content.Context;

import com.nayam.itunesdiscover.data.local.SharedPreferenceManager;

/**
 * @author May Ann Palencia on 01/09/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @link https://www.sidekickdigital.co.uk/
 * @since 1.0
 * Copyright (c) 2019 Sidekick Digital Limited
 */
public class ItunesDiscoverApplication extends Application {

    private static ItunesDiscoverApplication instance;

    public static Context getContext(){
        return instance;
    }

    @Override
    public void onCreate() {

        instance = this;

        super.onCreate();

        SharedPreferenceManager.initialize(this);
    }

}
