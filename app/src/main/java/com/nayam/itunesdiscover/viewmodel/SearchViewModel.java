package com.nayam.itunesdiscover.viewmodel;

import android.content.Context;
import android.text.TextUtils;

import androidx.lifecycle.ViewModel;

import com.nayam.itunesdiscover.utility.SharedPreferenceManager;

/**
 * @author May Ann Palencia on 01/09/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @link https://www.sidekickdigital.co.uk/
 * @since 1.0
 * Copyright (c) 2019 Sidekick Digital Limited
 */
public class SearchViewModel extends ViewModel {

    public void init(Context context){
        setCountry(context, "au");
        setTerm(context, !TextUtils.isEmpty(getTerm(context)) ? getTerm(context) : "star");
        setMediaType(context, !TextUtils.isEmpty(getMediaType(context)) ? getMediaType(context) : "movie");
        setMediaTypePosition(context, getMediaTypePosition(context)!=0 ? getMediaTypePosition(context) : 1);
    }

    public void setLastSearchDate(Context context, String date){
        SharedPreferenceManager.setPreference(context, SharedPreferenceManager.LAST_SEARCH_DATE, date);
    }

    public String getLastSearchDate(Context context){
        return SharedPreferenceManager.getStringPreference(context, SharedPreferenceManager.LAST_SEARCH_DATE);
    }

    public void setMediaType(Context context, String mediaType){
        SharedPreferenceManager.setPreference(context, SharedPreferenceManager.SEARCH_MEDIA_TYPE, mediaType);
    }

    public String getMediaType(Context context){
        return SharedPreferenceManager.getStringPreference(context, SharedPreferenceManager.SEARCH_MEDIA_TYPE);
    }

    public void setMediaTypePosition(Context context, int mediaTypePosition){
        SharedPreferenceManager.setPreference(context, SharedPreferenceManager.SEARCH_MEDIA_TYPE_POSITION, mediaTypePosition);
    }

    public int getMediaTypePosition(Context context){
        return SharedPreferenceManager.getIntPreference(context, SharedPreferenceManager.SEARCH_MEDIA_TYPE_POSITION);
    }

    public void setCountry(Context context, String country){
        SharedPreferenceManager.setPreference(context, SharedPreferenceManager.SEARCH_COUNTRY, country);
    }

    public String getCountry(Context context){
        return SharedPreferenceManager.getStringPreference(context, SharedPreferenceManager.SEARCH_COUNTRY);
    }

    public void setTerm(Context context, String term){
        SharedPreferenceManager.setPreference(context, SharedPreferenceManager.SEARCH_TERM, term);
    }

    public String getTerm(Context context){
        return SharedPreferenceManager.getStringPreference(context, SharedPreferenceManager.SEARCH_TERM);
    }
}
