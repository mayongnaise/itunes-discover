package com.nayam.itunesdiscover.di.module;

import com.nayam.itunesdiscover.data.remote.TrackApi;
import com.nayam.itunesdiscover.data.remote.TrackRepository;
import com.nayam.itunesdiscover.utility.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author May Ann Palencia on 09/09/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @link https://www.sidekickdigital.co.uk/
 * @since 1.0
 * Copyright (c) 2019 Sidekick Digital Limited
 */

@Module(includes = OkHttpClientModule.class)
public class TrackRepositoryModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    public TrackApi getTrackService(Retrofit retrofit){
        return retrofit.create(TrackApi.class);
    }


    @Provides
    public CompositeDisposable provideDisposable(){
        return new CompositeDisposable();
    }

    @Provides
    @Singleton
    public TrackRepository provideTrackRepository(TrackApi trackApi, CompositeDisposable disposable){
        return new TrackRepository(trackApi, disposable);
    }
}
