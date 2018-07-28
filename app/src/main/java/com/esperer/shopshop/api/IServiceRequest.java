package com.esperer.shopshop.api;

public interface IServiceRequest {

    void onResponse(String response);

    void onError(String errorResponse);

}
