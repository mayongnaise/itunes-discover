package com.nayam.itunesdiscover.di.module;

import android.content.Context;

import com.nayam.itunesdiscover.data.local.SharedPreferenceManager;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * @author May Ann Palencia on 09/09/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @link https://www.sidekickdigital.co.uk/
 * @since 1.0
 * Copyright (c) 2019 Sidekick Digital Limited
 */

@Singleton
@Module(includes = ContextModule.class)
public class SharedPreferenceManagerModule {

    @Singleton
    @Provides
    SharedPreferenceManager initializeSharedPreferences(Context context){
        return new SharedPreferenceManager(context);
    }
}
