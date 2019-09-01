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

public class TrackDetailActivity extends AppCompatActivity {

    ActivityTrackDetailBinding bindings;

    @BindView(R.id.toolbar)
    Toolbar mToolBar;

    SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper();

    boolean fromHistory = false;

    @Override
    public void onBackPressed(){
        if(fromHistory){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        super.onBackPressed();
    }

    @Override
    public void onResume() {
        sharedPreferenceHelper.setLastActivity(getClass().getSimpleName());
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindings = DataBindingUtil.setContentView(this, R.layout.activity_track_detail);

        ButterKnife.bind(this);

        setToolBar();

        Intent intent = getIntent();
        fromHistory = intent.getBooleanExtra("from_history", false);
        if(fromHistory){
            Track lastTrackSaved = convertStringToTrack(sharedPreferenceHelper.getLastTrackSaved());
            bindings.layoutContentDetail.setTrack(lastTrackSaved);
            setToolBarTitle(lastTrackSaved);
        }

    }

    private void setToolBar(){
        setSupportActionBar(mToolBar);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        mToolBar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(TrackEvent trackEvent) {
        sharedPreferenceHelper.setLastTrackSaved(convertTrackToString(trackEvent.getTrack()));
        bindings.layoutContentDetail.setTrack(trackEvent.getTrack());

        setToolBarTitle(trackEvent.getTrack());

    }

    public void setToolBarTitle(Track track){
        mToolBar.setTitle(track.getTrackName()==null ? track.getCollectionName() : track.getTrackName());
    }

    public static String convertTrackToString(Track track){
        return new Gson().toJson(track);
    }

    public static Track convertStringToTrack(String track){
        return new Gson().fromJson(track, Track.class);
    }
}
