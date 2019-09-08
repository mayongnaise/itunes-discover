package com.nayam.itunesdiscover.di.component;

import android.content.Context;

import com.nayam.itunesdiscover.data.local.SharedPreferenceManager;
import com.nayam.itunesdiscover.di.module.SharedPreferenceManagerModule;

import javax.inject.Singleton;

import dagger.Component;

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
@Component(modules = SharedPreferenceManagerModule.class)
public interface ApplicationComponent {

    SharedPreferenceManager initializeSharedPreferenceManager();
}
