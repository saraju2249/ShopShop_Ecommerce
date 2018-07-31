package com.esperer.shopshop.api;

public interface ServiceRequest {

    void onResponse(String response);

    void onError(String errorResponse);

}
