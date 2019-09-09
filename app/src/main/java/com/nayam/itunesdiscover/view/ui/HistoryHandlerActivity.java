package com.nayam.itunesdiscover.view.ui;

import android.content.Intent;
import android.os.Bundle;

import com.nayam.itunesdiscover.base.BaseActivity;
import com.nayam.itunesdiscover.base.DaggerBaseActivity;

/**
 * Activity to handle activity history
 *
 * This activity will manage what activity to resume after the user kills the app. The activity screen to open will be based on
 * the last saved activity in {@link android.content.SharedPreferences}
 *
 * @author May Ann Palencia on 01/09/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @since 1.0
 * Copyright (c) 2019
 */
public class HistoryHandlerActivity extends DaggerBaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        handleHistory(getSharedPreferenceManager().getLastActivity());

    }

    /**
     * Evaluate activity to start based on class name
     * @param lastActivity The class name of the last activity visited by the user
     */
    public void handleHistory(String lastActivity){
        Intent intent;

        Class<?> activityToOpen;

        try {
            activityToOpen = Class.forName(lastActivity);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            activityToOpen = MainActivity.class;
        }

        intent = new Intent(this, activityToOpen);
        intent.putExtra("from_history", true);
        startActivity(intent);
        finish();
    }
}
