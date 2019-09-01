package com.nayam.itunesdiscover.data.local;

/**
 * @author May Ann Palencia on 01/09/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @link https://www.sidekickdigital.co.uk/
 * @since 1.0
 * Copyright (c) 2019 Sidekick Digital Limited
 */
public interface PreferenceHelper {

    String getLastActivity();

    void setLastActivity(String activity);

    String getLastTrackSaved();

    void setLastTrackSaved(String track);

    String getLastSearchDate();

    void setLastSearchDate(String date);

    int getMediaTypePosition();

    void setMediaTypePosition(int position);

    String getCountry();

    void setCountry(String country);

    String getTerm();

    void setTerm(String term);
}
