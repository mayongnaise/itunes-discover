package com.nayam.itunesdiscover.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.nayam.itunesdiscover.model.TrackResponse;
import com.nayam.itunesdiscover.network.TrackRepository;

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

    private MutableLiveData<TrackResponse> mutableLiveData;
    private TrackRepository trackRepository;

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        trackRepository = TrackRepository.getInstance();
        mutableLiveData = trackRepository.searchTrack("", "PH", "all");

    }

    public LiveData<TrackResponse> getTrackRepository() {
        return mutableLiveData;
    }
}
