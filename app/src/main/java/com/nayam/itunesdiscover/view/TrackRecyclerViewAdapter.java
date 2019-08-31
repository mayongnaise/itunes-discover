package com.nayam.itunesdiscover.view;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.nayam.itunesdiscover.R;
import com.nayam.itunesdiscover.model.Track;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;

public class TrackRecyclerViewAdapter extends RecyclerView.Adapter<TrackRecyclerViewAdapter.ViewHolder> {

    private final Activity mActivity;
    private final ArrayList<Track> mValues;

    public TrackRecyclerViewAdapter(Activity activity, ArrayList<Track> items) {
        mActivity = activity;
        mValues = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Track track = mValues.get(position);

        Picasso.get()
                .load(track.getArtworkUrl100())
                .placeholder(R.drawable.image_list_placeholder)
                .fit()
                .into(holder.mArtWork);

        Log.d("Network", track.getArtworkUrl100());

        holder.mTrackTitle.setText(track.getTrackName());
        holder.mTrackPrice.setText(String.format("%s %s", track.getCurrency(), track.getTrackPrice()));
        holder.mTrackGenre.setText(track.getPrimaryGenreName());

        holder.mArtWork.setOnClickListener(view -> {
            ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity, view,
                    "artWork");
            Intent in = new Intent(mActivity, TrackDetailActivity.class);
            mActivity.startActivity(in, activityOptionsCompat.toBundle());
        });


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        View mView;
        ImageView mArtWork;
        TextView mTrackTitle;
        TextView mTrackPrice;
        TextView mTrackGenre;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mArtWork = view.findViewById(R.id.imageArtWork);
            mTrackTitle = view.findViewById(R.id.textTrackName);
            mTrackPrice = view.findViewById(R.id.textPrice);
            mTrackGenre = view.findViewById(R.id.textGenre);
        }
    }


}
