package com.nayam.itunesdiscover.viewmodel;

import android.content.Context;

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

    public void searchTrack(SearchViewModel searchViewModel, Context context){

        trackRepository = TrackRepository.getInstance();
        mutableLiveData = trackRepository.searchTrack(searchViewModel.getTerm(context), searchViewModel.getCountry(context),
                searchViewModel.getMediaType(context));
    }

    public LiveData<TrackResponse> getTrackRepository() {
        return mutableLiveData;
    }

}
