package com.nayam.itunesdiscover.view.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nayam.itunesdiscover.R;
import com.nayam.itunesdiscover.databinding.ItemListBinding;
import com.nayam.itunesdiscover.model.Track;
import com.nayam.itunesdiscover.model.TrackEvent;
import com.nayam.itunesdiscover.view.ui.TrackDetailActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 *
 * Adapter class for our {@link RecyclerView} of {@link Track} list
 *
 * @author May Ann Palencia on 01/09/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @since 1.0
 * Copyright (c) 2019
 */
public class TrackRecyclerViewAdapter extends RecyclerView.Adapter<TrackRecyclerViewAdapter.TrackViewHolder> {

    private final Activity mActivity;
    private final ArrayList<Track> mValues;

    public TrackRecyclerViewAdapter(Activity activity, ArrayList<Track> items) {
        mActivity = activity;
        mValues = items;
    }

    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListBinding itemListBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_list, parent, false);
        return new TrackViewHolder(itemListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final TrackViewHolder holder, final int position) {
        Track track = mValues.get(position);

        // Bind value of track to this adapter UI
        holder.itemListBinding.setTrack(track);

        holder.itemListBinding.layoutItem.setOnClickListener(view -> {

            // Post event in EventBus so the subscriber can observe
            EventBus.getDefault().postSticky(new TrackEvent(track));

            // Call activity to display detail with Shared element transition
            Intent in = new Intent(mActivity, TrackDetailActivity.class);

            Pair<View, String> pair1 = Pair.create(holder.itemListBinding.imageArtWork, "artWork");
            Pair<View, String> pair2 = Pair.create(holder.itemListBinding.textTrackName, "trackTitle");
            Pair<View, String> pair3 = Pair.create(holder.itemListBinding.textPrice, "trackPrice");
            Pair<View, String> pair4 = Pair.create(holder.itemListBinding.textGenre, "trackGenre");

            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(mActivity, pair1, pair2, pair3, pair4);

            mActivity.startActivity(in, options.toBundle());
        });


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    class TrackViewHolder extends RecyclerView.ViewHolder {

        private ItemListBinding itemListBinding;

        public TrackViewHolder(@NonNull ItemListBinding mItemListBinding) {
            super(mItemListBinding.getRoot());

            this.itemListBinding = mItemListBinding;
        }
    }


}
