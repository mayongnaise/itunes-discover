package com.nayam.itunesdiscover.view.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.nayam.itunesdiscover.R;
import com.nayam.itunesdiscover.databinding.ActivityTrackDetailBinding;
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

    }

    private void setToolBar(){
        setSupportActionBar(mToolBar);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        mToolBar.setTitle("");
        mToolBar.setSubtitle("");
        mToolBar.setNavigationOnClickListener(v -> onBackPressed());
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(TrackEvent trackEvent) {
        Log.d("TRACK", "Event: " + trackEvent.getTrack().getArtistName());
        bindings.layoutContentDetail.setTrack(trackEvent.getTrack());
    }

}
