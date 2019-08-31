package com.nayam.itunesdiscover.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.nayam.itunesdiscover.model.Track;
import com.nayam.itunesdiscover.model.TrackResponse;
import com.nayam.itunesdiscover.utility.SharedPreferenceManager;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author May Ann Palencia on 31/08/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @link https://www.sidekickdigital.co.uk/
 * @since 1.0
 * Copyright (c) 2019 Sidekick Digital Limited
 */
public class TrackRepository {

    private static final String TAG = TrackRepository.class.getSimpleName();

    private static TrackRepository trackRepository;

    public static TrackRepository getInstance(){
        if (trackRepository == null){
            trackRepository = new TrackRepository();
        }
        return trackRepository;
    }

    private TrackApi trackApi;

    public TrackRepository(){
        trackApi = RetrofitService.createService(TrackApi.class);
    }

    public MutableLiveData<TrackResponse> searchTrack(String term, String country, String media){
        MutableLiveData<TrackResponse> trackData = new MutableLiveData<>();
        trackApi.searchTracks(term, country, media).enqueue(new Callback<TrackResponse>() {
            @Override
            public void onResponse(@NotNull Call<TrackResponse> call,
                                   @NotNull Response<TrackResponse> response) {
                Log.d(TAG, "Response: " + response.raw());
                if (response.isSuccessful()){
                    trackData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<TrackResponse> call, @NotNull Throwable t) {
                t.printStackTrace();
                trackData.setValue(null);
            }
        });
        return trackData;
    }
}
