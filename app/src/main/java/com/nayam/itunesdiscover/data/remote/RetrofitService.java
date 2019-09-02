package com.nayam.itunesdiscover.data.remote;

import com.nayam.itunesdiscover.ItunesDiscoverApplication;
import com.nayam.itunesdiscover.utility.Constants;
import com.nayam.itunesdiscover.utility.Utility;
import com.ncornette.cache.OkCacheControl;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * Create service for HTTP calls using {@link Retrofit} library
 * Handles instances when the network is down to retrieve data from cache
 *
 * @author May Ann Palencia on 31/08/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @since 1.0
 * Copyright (c) 2019
 */
public class RetrofitService {

    // Creating network monitor object to observe network's availability
    private static OkCacheControl.NetworkMonitor networkMonitor = () -> Utility.isNetworkAvailable(ItunesDiscoverApplication.getContext());

    /**
     * Create the service instance based on the provided interface
     * @param serviceClass The interface to implement
     * @param <S> The interface type
     * @return This returns a service to represent HTTP request
     */
    public static <S> S createService(Class<S> serviceClass) {

        // Create cache object
        int cacheSize = 10 * 1024 * 1024; // 10 MB
        Cache cache = new Cache(ItunesDiscoverApplication.getContext().getCacheDir(), cacheSize);

        // Create an OkHttpClient object with an interceptor and cache
        OkHttpClient okHttpClient = OkCacheControl.on(new OkHttpClient.Builder())
                .overrideServerCachePolicy(1, TimeUnit.MINUTES)
                .forceCacheWhenOffline(networkMonitor) // force to use cached data when offline
                .apply()
                .cache(cache)
                .build();

        // Building retrofit instance with the okHttpClient object attached to handle cache
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(serviceClass);
    }

}
