package com.nayam.itunesdiscover.data.local;

/**
 *
 * Interface to handle {@link SharedPreferenceHelper} methods
 *
 * @author May Ann Palencia on 01/09/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @since 1.0
 * Copyright (c) 2019
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

    String getCountryCode();

    void setCountryCode(String countryCode);

    String getTerm();

    void setTerm(String term);
}
