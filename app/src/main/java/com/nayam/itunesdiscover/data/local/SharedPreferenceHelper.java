package com.nayam.itunesdiscover.data.local;

import com.nayam.itunesdiscover.utility.Utility;

/**
 *
 * Helper class to manage {@link SharedPreferenceManager} methods
 *
 * @author May Ann Palencia on 01/09/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @since 1.0
 * Copyright (c) 2019
 */
public class SharedPreferenceHelper implements PreferenceHelper {

    public SharedPreferenceHelper(){

    }

    @Override
    public String getLastActivity() {
        return SharedPreferenceManager.getLastActivity();
    }

    @Override
    public void setLastActivity(String activity) {
        SharedPreferenceManager.setLastActivity(activity);
    }

    @Override
    public String getLastTrackSaved() {
        return SharedPreferenceManager.getLastTrackSaved();
    }

    @Override
    public void setLastTrackSaved(String track) {
        SharedPreferenceManager.setLastTrackSaved(track);
    }

    @Override
    public String getLastSearchDate() {
        return SharedPreferenceManager.getLastSearchDate();
    }

    @Override
    public void setLastSearchDate(String date) {
        SharedPreferenceManager.setLastSearchDate(Utility.getCurrentDateTime());
    }

    @Override
    public int getMediaTypePosition() {
        return SharedPreferenceManager.getMediaTypePosition();
    }

    @Override
    public void setMediaTypePosition(int position) {
        SharedPreferenceManager.setMediaTypePosition(position);
    }

    @Override
    public String getCountryCode() {
        return SharedPreferenceManager.getCountryCode();
    }

    @Override
    public void setCountryCode(String countryCode) {
        SharedPreferenceManager.setCountryCode(countryCode);
    }

    @Override
    public String getTerm() {
        return SharedPreferenceManager.getTerm();
    }

    @Override
    public void setTerm(String term) {
        SharedPreferenceManager.setTerm(term);
    }
}
