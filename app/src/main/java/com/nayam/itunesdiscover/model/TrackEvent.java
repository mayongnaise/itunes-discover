package com.nayam.itunesdiscover.model;

/**
 * Model class of {@link org.greenrobot.eventbus.EventBus} event of {@link Track} object
 *
 * @author May Ann Palencia on 31/08/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @since 1.0
 * Copyright (c) 2019
 */
public class TrackEvent {

    private Track track;

    public TrackEvent(Track track) {
       this.track = track;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }
}
