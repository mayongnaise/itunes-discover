package com.nayam.itunesdiscover.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nayam.itunesdiscover.data.remote.TrackRepository;
import com.nayam.itunesdiscover.model.ResponseResult;

/**
 * ViewModel of {@link com.nayam.itunesdiscover.model.Track} to manage UI data
 *
 * @author May Ann Palencia on 31/08/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @since 1.0
 * Copyright (c) 2019
 */
public class TrackViewModel extends ViewModel {

    /**
     * LiveData representing the response result of the API call
     */
    private MutableLiveData<ResponseResult> mutableLiveData;

    /**
     * Repository of {@link com.nayam.itunesdiscover.model.Track} api call
     */
    private TrackRepository trackRepository;

    /**
     * Call Search API and retrieve a LiveData to be observed by the UI
     * @param term
     * @param country
     * @param mediaType
     */
    public void searchTrack(String term, String country, String mediaType){
        trackRepository = TrackRepository.getInstance();
        mutableLiveData = trackRepository.searchTrack(term, country, mediaType);
    }

    /**
     * Expose the LiveData {@link com.nayam.itunesdiscover.model.TrackResponse} query so the UI can observe it.
     */
    public LiveData<ResponseResult> getTrackRepository() {
        return mutableLiveData;
    }

}
