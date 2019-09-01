package com.nayam.itunesdiscover.data.remote;

import com.nayam.itunesdiscover.ItunesDiscoverApplication;
import com.nayam.itunesdiscover.utility.Utility;
import com.ncornette.cache.OkCacheControl;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author May Ann Palencia on 31/08/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @link https://www.sidekickdigital.co.uk/
 * @since 1.0
 * Copyright (c) 2019 Sidekick Digital Limited
 */
public class RetrofitService {

    private static OkCacheControl.NetworkMonitor networkMonitor = () -> Utility.isNetworkAvailable(ItunesDiscoverApplication.getContext());

    public static <S> S createService(Class<S> serviceClass) {

        int cacheSize = 10 * 1024 * 1024; // 10 MB
        Cache cache = new Cache(ItunesDiscoverApplication.getContext().getCacheDir(), cacheSize);

        OkHttpClient okHttpClient = OkCacheControl.on(new OkHttpClient.Builder())
                .overrideServerCachePolicy(1, TimeUnit.MINUTES)
                .forceCacheWhenOffline(networkMonitor)
                .apply() // return to the OkHttpClient.Builder instance
                .cache(cache)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(serviceClass);
    }

}
