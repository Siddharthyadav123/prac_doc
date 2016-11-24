package com.sidproj.nagpurdrs.volly;

/**
 * Created by sid on 07/08/2016.
 */
public interface APICallback {
    public void onAPIResponse(int requestId, boolean isSuccess, String response, String errorString);

}
