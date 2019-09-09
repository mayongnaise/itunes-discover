package com.nayam.itunesdiscover.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nayam.itunesdiscover.data.local.SharedPreferenceManager;
import com.nayam.itunesdiscover.data.remote.TrackRepository;
import com.nayam.itunesdiscover.di.component.ApplicationComponent;
import com.nayam.itunesdiscover.di.component.DaggerApplicationComponent;
import com.nayam.itunesdiscover.di.module.ContextModule;

import javax.inject.Inject;

/**
 * @author May Ann Palencia on 09/09/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @link https://www.sidekickdigital.co.uk/
 * @since 1.0
 * Copyright (c) 2019 Sidekick Digital Limited
 */
public abstract class DaggerBaseActivity extends AppCompatActivity {

    @Inject
    SharedPreferenceManager sharedPreferenceManager;

    /**
     * Repository of {@link com.nayam.itunesdiscover.model.Track} api call
     */
    @Inject
    TrackRepository trackRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ApplicationComponent component = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
        sharedPreferenceManager = component.initializeSharedPreferenceManager();
        trackRepository = component.getTrackRepository();

    }

    public SharedPreferenceManager getSharedPreferenceManager() {
        return sharedPreferenceManager;
    }

    public TrackRepository getTrackRepository() {
        return trackRepository;
    }

}
