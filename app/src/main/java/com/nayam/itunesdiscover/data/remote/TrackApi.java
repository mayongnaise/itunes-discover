package com.nayam.itunesdiscover.data.remote;

import com.nayam.itunesdiscover.model.TrackResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author May Ann Palencia on 31/08/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @link https://www.sidekickdigital.co.uk/
 * @since 1.0
 * Copyright (c) 2019 Sidekick Digital Limited
 */
public interface TrackApi {

    @GET("/search")
    Call<TrackResponse> searchTracks(@Query("term") String term, @Query("country") String country, @Query("media") String media);

}
