package com.nayam.itunesdiscover.data.remote;

import androidx.lifecycle.MutableLiveData;

import com.nayam.itunesdiscover.model.ResponseResult;
import com.nayam.itunesdiscover.model.TrackResponse;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class to handle network requests
 * LiveData is used to observe the responses from the API
 *
 * @author May Ann Palencia on 31/08/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @since 1.0
 * Copyright (c) 2019
 */
public class TrackRepository {

    /**
     * The API interface to use
     */

    private TrackApi trackApi;

    /**
     * Initializing the interface to implement
     */

    @Inject
    public TrackRepository(TrackApi trackApi){
        this.trackApi = trackApi;
    }

    /**
     * Calling {@link TrackApi} call for searching track
     * @param term
     * @param country
     * @param media
     * @return This will return a LiveData that the UI will observe
     */
    public MutableLiveData<ResponseResult> searchTrack(String term, String country, String media){
        MutableLiveData<ResponseResult> trackData = new MutableLiveData<>();

        trackApi.searchTracks(term, country, media).enqueue(new Callback<TrackResponse>() {
            @Override
            public void onResponse(@NotNull Call<TrackResponse> call,
                                   @NotNull Response<TrackResponse> response) {

                trackData.setValue(new ResponseResult<>(response.body(), response.raw()));
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
