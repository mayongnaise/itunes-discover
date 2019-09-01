package com.nayam.itunesdiscover.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nayam.itunesdiscover.data.remote.TrackRepository;
import com.nayam.itunesdiscover.model.TrackResponseResult;

/**
 * @author May Ann Palencia on 31/08/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @link https://www.sidekickdigital.co.uk/
 * @since 1.0
 * Copyright (c) 2019 Sidekick Digital Limited
 */
public class TrackViewModel extends ViewModel {

    private MutableLiveData<TrackResponseResult> mutableLiveData;
    private TrackRepository trackRepository;

    public void searchTrack(String term, String country, String mediaType){
        trackRepository = TrackRepository.getInstance();
        mutableLiveData = trackRepository.searchTrack(term, country, mediaType);
    }

    public LiveData<TrackResponseResult> getTrackRepository() {
        return mutableLiveData;
    }

}
