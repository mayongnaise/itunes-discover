package com.nayam.itunesdiscover.data.remote;

import com.nayam.itunesdiscover.model.TrackResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *
 * API Interface to represent HTTP Request calls
 *
 * @author May Ann Palencia on 31/08/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @since 1.0
 * Copyright (c) 2019
 */
public interface TrackApi {

    /**
     * Calling Search API request
     * @param term
     * @param country
     * @param media
     * @return This will return a Call object with type {@link TrackResponse}
     */
    @GET("/search")
    Call<TrackResponse> searchTracks(@Query("term") String term, @Query("country") String country, @Query("media") String media);

}
