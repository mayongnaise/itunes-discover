package com.nayam.itunesdiscover.view.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.nayam.itunesdiscover.data.local.SharedPreferenceHelper;

/**
 * @author May Ann Palencia on 02/09/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @link https://www.sidekickdigital.co.uk/
 * @since 1.0
 * Copyright (c) 2019 Sidekick Digital Limited
 */
public class HistoryHandlerActivity extends Activity {

    SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        handleHistory(sharedPreferenceHelper.getLastActivity());

    }

    public void handleHistory(String lastActivity){
        Intent intent;

        if (lastActivity.equalsIgnoreCase(MainActivity.class.getSimpleName())) {
            intent = new Intent(this, MainActivity.class);
        } else if (lastActivity.equalsIgnoreCase(TrackDetailActivity.class.getSimpleName())) {
            intent = new Intent(this, TrackDetailActivity.class);
            intent.putExtra("from_history", true);
        } else {
            // assume default activity
            intent = new Intent(this, MainActivity.class);
        }

        startActivity(intent);
        finish();
    }
}
