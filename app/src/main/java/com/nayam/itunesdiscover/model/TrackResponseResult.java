package com.nayam.itunesdiscover.model;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author May Ann Palencia on 01/09/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @link https://www.sidekickdigital.co.uk/
 * @since 1.0
 * Copyright (c) 2019 Sidekick Digital Limited
 */
public class TrackResponseResult<T> {

    private T trackResponse;
    private Response responseBody;

    public TrackResponseResult(T trackResponse, Response responseBody){
        this.trackResponse = trackResponse;
        this.responseBody = responseBody;
    }

    public T getTrackResponse() {
        return trackResponse;
    }

    public void setTrackResponse(T trackResponse) {
        this.trackResponse = trackResponse;
    }

    public Response getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(Response responseBody) {
        this.responseBody = responseBody;
    }
}
