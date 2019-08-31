package com.nayam.itunesdiscover.view;

import android.content.Intent;
import android.os.Bundle;

import com.nayam.itunesdiscover.R;
import com.nayam.itunesdiscover.model.Track;
import com.nayam.itunesdiscover.viewmodel.TrackViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    ArrayList<Track> trackArrayList = new ArrayList<>();
    TrackRecyclerViewAdapter trackRecyclerViewAdapter;

    @BindView(R.id.recyclerViewTrack)
    RecyclerView rvTrackList;
    TrackViewModel trackViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        trackViewModel = ViewModelProviders.of(this).get(TrackViewModel.class);
        trackViewModel.init();
        trackViewModel.getTrackRepository().observe(this, trackResponse -> {
            List<Track> tracks = trackResponse.getResults();
            trackArrayList.addAll(tracks);
            trackRecyclerViewAdapter.notifyDataSetChanged();
        });

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        if (trackRecyclerViewAdapter == null) {
            trackRecyclerViewAdapter = new TrackRecyclerViewAdapter(MainActivity.this, trackArrayList);
            rvTrackList.setLayoutManager(new LinearLayoutManager(this));
            rvTrackList.setAdapter(trackRecyclerViewAdapter);
            rvTrackList.setItemAnimator(new DefaultItemAnimator());
            rvTrackList.setNestedScrollingEnabled(true);
        } else {
            trackRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

}
