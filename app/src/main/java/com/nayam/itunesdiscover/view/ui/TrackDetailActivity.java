package com.nayam.itunesdiscover.view.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.nayam.itunesdiscover.R;
import com.nayam.itunesdiscover.data.local.SharedPreferenceHelper;
import com.nayam.itunesdiscover.databinding.ActivityTrackDetailBinding;
import com.nayam.itunesdiscover.model.Track;
import com.nayam.itunesdiscover.model.TrackEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Activity to display the detail of the selected track
 *
 * This activity is used to display the detail of the track selected by the user from {@link MainActivity}'s {@link androidx.recyclerview.widget.RecyclerView} list
 * Additional parameters displayed aside from {@link Track} [trackName, artworkUrl100, trackPrice, primaryGenreName] here are {@link Track} kind and [description/shortDescription/longDescription]
 *
 * @author May Ann Palencia on 01/09/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @since 1.0
 * Copyright (c) 2019
 */
public class TrackDetailActivity extends AppCompatActivity {

    /**
     * Data binding for activity
     */
    ActivityTrackDetailBinding activityTrackDetailBinding;

    /**
     * Activity UI components
     */
    @BindView(R.id.toolbar)
    Toolbar mToolBar;

    /**
     * Helper class to manage {@link android.content.SharedPreferences}
     */
    SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper();

    /**
     * Whether the activity is called from {@link HistoryHandlerActivity}
     */
    boolean fromHistory = false;

    /**
     * Override onBackPressed action when the activity is called from {@link HistoryHandlerActivity}. Call {@link MainActivity} if value is true.
     */
    @Override
    public void onBackPressed(){

        if(fromHistory){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        super.onBackPressed();
    }

    /**
     * Saving the {@link TrackDetailActivity} as the last visited activity
     */
    @Override
    public void onResume() {
        sharedPreferenceHelper.setLastActivity(getClass().getName());
        super.onResume();
    }

    /**
     * Register {@link EventBus} to this activity for selected {@link TrackEvent} data listener
     */
    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    /**
     * Unregister {@link EventBus} when activity is destroyed
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Obtain binding object using the Data Binding library
        activityTrackDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_track_detail);
        ButterKnife.bind(this);

        setToolBar();

        Intent intent = getIntent();
        fromHistory = intent.getBooleanExtra("from_history", false);
        if(fromHistory){
            // Get the last selected track saved from preferences and assign as the current track to bind on UI
            Track lastTrackSaved = convertStringToTrack(sharedPreferenceHelper.getLastTrackSaved());
            activityTrackDetailBinding.layoutContentDetail.setTrack(lastTrackSaved);
            setToolBarTitle(lastTrackSaved);
        }

    }

    /**
     * Method to setup the {@link Toolbar} widget and handle it's navigation
     */
    private void setToolBar(){
        setSupportActionBar(mToolBar);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        mToolBar.setNavigationOnClickListener(v -> onBackPressed());
    }

    /**
     * {@link EventBus} subscriber method. If a track is selected, saved it as the last track saved in preference then bind the track to UI
     * @param trackEvent The value of the event result
     */
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(TrackEvent trackEvent) {
        sharedPreferenceHelper.setLastTrackSaved(convertTrackToString(trackEvent.getTrack()));
        activityTrackDetailBinding.layoutContentDetail.setTrack(trackEvent.getTrack());

        setToolBarTitle(trackEvent.getTrack());

    }

    /**
     * Method to setup the {@link Toolbar} widget title based on {@link Track} trackName or collectionName
     * @param track The current selected track
     */
    public void setToolBarTitle(Track track){
        mToolBar.setTitle(track.getTrackName()==null ? track.getCollectionName() : track.getTrackName());
    }

    /**
     * Helper method to convert {@link Track} object to String so we can saved it on preference
     * @param track The current selected track
     * @return The result is the Json string representation of track
     */
    public static String convertTrackToString(Track track){
        return new Gson().toJson(track);
    }

    /**
     * Helper method to convert the {@link Track} Json string representation back to {@link Track} object
     * @param track The Json string representation of {@link Track} saved in preference
     * @return The result is the {@link Track} object
     */
    public static Track convertStringToTrack(String track){
        return new Gson().fromJson(track, Track.class);
    }
}
