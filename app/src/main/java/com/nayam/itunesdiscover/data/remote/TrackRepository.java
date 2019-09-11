package com.nayam.itunesdiscover.data.remote;

import androidx.lifecycle.MutableLiveData;

import com.nayam.itunesdiscover.model.ResponseResult;
import com.nayam.itunesdiscover.model.TrackResponse;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
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

    private CompositeDisposable disposable;

    /**
     * The API interface to use
     */

    private TrackApi trackApi;

    /**
     * Initializing the interface to implement
     */

    @Inject
    public TrackRepository(TrackApi trackApi, CompositeDisposable disposable){
        this.trackApi = trackApi;
        this.disposable = disposable;

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

        disposable.add(trackApi.searchTracks(term, country, media)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<TrackResponse>>(){

                    @Override
                    public void onSuccess(Response<TrackResponse> trackResponseResponse) {

                        trackData.setValue(new ResponseResult<>(trackResponseResponse.body(), trackResponseResponse.raw()));
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        trackData.setValue(null);
                    }
                })

        );

       /* trackApi.searchTracks(term, country, media).enqueue(new Callback<TrackResponse>() {
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
        });*/
        return trackData;
    }

}
