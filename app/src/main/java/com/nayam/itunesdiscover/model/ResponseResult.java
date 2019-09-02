package com.nayam.itunesdiscover.model;

import okhttp3.Response;

/**
 * Model class wrapper for an API Call response with generic type
 *
 * @author May Ann Palencia on 01/09/2019
 * @version 1.0.0
 * @use
 * @desc Android Developer
 * @since 1.0
 * Copyright (c) 2019
 */
public class ResponseResult<T> {

    private T response;
    private Response responseBody;

    public ResponseResult(T response, Response responseBody){
        this.response = response;
        this.responseBody = responseBody;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public Response getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(Response responseBody) {
        this.responseBody = responseBody;
    }
}
