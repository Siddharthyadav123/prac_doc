package com.sidproj.nagpurdrs.volly;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sidproj.nagpurdrs.application.MyApplication;

import java.util.Map;

public class APIHandler implements Response.Listener<Object>, Response.ErrorListener {
    private Context context;
    private int requestId;
    private int methodType;
    private boolean showLoading = false;
    private String loadingText;
    private String url;

    private ProgressDialog pDialog = null;
    private APICallback apiCallback = null;
    private String requestBody = null;
    private Map<String, String> headers = null;
    private boolean showToastOnRespone = true;

    public APIHandler(Context context, APICallback apiCallback, int requestId, int methodType, String url,
                      boolean showLoading, String loadingText, String requestBody) {
        this.context = context;
        this.apiCallback = apiCallback;
        this.requestId = requestId;
        this.methodType = methodType;
        this.url = url;
        this.showLoading = showLoading;
        this.loadingText = loadingText;
        this.requestBody = requestBody;
    }

    private void showLoading() {
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    pDialog = new ProgressDialog(context);
                    pDialog.setMessage(loadingText);
                    pDialog.show();
                }
            });
        }
    }

    private void hideLoading() {
        try {
            if (context instanceof Activity) {
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (pDialog != null)
                            pDialog.dismiss();
                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void requestAPI() {
        //check if internet connect found or not
        if (!MyApplication.getInstance().checkConnection(context)) {
            if (showToastOnRespone) {
                String noInternetConnection = "No internet connection found";
                if (apiCallback != null) {
                    apiCallback.onAPIResponse(requestId, false, null, noInternetConnection);
                }

                MyApplication.getInstance().showNormalDailog(context, noInternetConnection);
            }
            return;
        }

        System.out.println("[API] request url = " + url);
        System.out.println("[API] request body = " + requestBody);
        if (showLoading) {
            showLoading();
        }

        GenericRequest genericRequest = new GenericRequest(methodType, url, requestBody, this, this, null);
        MyApplication.getInstance().addToRequestQueue(genericRequest, requestId);


    }


    private void showToast(final String text) {
        if (showToastOnRespone) {
            if (context instanceof Activity) {
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (text != null && text.length() > 0)
                            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }


    @Override
    public void onResponse(Object response) {
        hideLoading();
        try {
            System.out.println("[API] response body = " + response.toString());
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(response.toString()).getAsJsonObject();
            boolean isSuccess = jsonObject.get("success").getAsBoolean();
            String msg = parseMsg(jsonObject);

            if (isSuccess && jsonObject.has("response")) {
                JsonElement jsonElement = jsonObject.get("response");
                if (jsonElement instanceof JsonArray) {
                    apiCallback.onAPIResponse(requestId, isSuccess, jsonElement.toString(), msg);
                } else {
                    apiCallback.onAPIResponse(requestId, isSuccess, jsonElement.toString(), msg);
                }
            } else {
                MyApplication.getInstance().showNormalDailog(context, msg);
                apiCallback.onAPIResponse(requestId, false, null, msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MyApplication.getInstance().showNormalDailog(context, e.getMessage());
            apiCallback.onAPIResponse(requestId, false, null, e.getMessage());
        }
    }

    private String parseMsg(JsonObject jsonObject) {
        String msg = "";
        try {
            if (jsonObject.has("msg") && jsonObject.get("msg").toString() != null) {
                msg = jsonObject.get("msg").getAsString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        if (apiCallback != null) {
            apiCallback.onAPIResponse(requestId, false, null, error.getMessage());
        }
        hideLoading();
    }


    public boolean isShowToastOnRespone() {
        return showToastOnRespone;
    }

    public void setShowToastOnRespone(boolean showToastOnRespone) {
        this.showToastOnRespone = showToastOnRespone;
    }

}
