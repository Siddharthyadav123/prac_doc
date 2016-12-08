package com.sidproj.nagpurdrs.volly;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.sidproj.nagpurdrs.application.MyApplication;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by sid on 07/08/2016.
 */
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
    private boolean isResponseFormatIsArray = true;

    public APIHandler(Context context, APICallback apiCallback, int requestId, int methodType, String url,
                      boolean showLoading, String loadingText, String requestBody, boolean isResponseFormatIsArray) {
        this.context = context;
        this.apiCallback = apiCallback;
        this.requestId = requestId;
        this.methodType = methodType;
        this.url = url;
        this.showLoading = showLoading;
        this.loadingText = loadingText;
        this.requestBody = requestBody;
        this.isResponseFormatIsArray = isResponseFormatIsArray;
//        this.headers = headers;
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
            JSONObject jsonObject = new JSONObject(response.toString());
            boolean isSuccess = jsonObject.getBoolean("success");
            String msg = jsonObject.getString("msg");

            if (isResponseFormatIsArray) {
                JSONArray resJsonArray = jsonObject.getJSONArray("response");
                apiCallback.onAPIResponse(requestId, isSuccess, resJsonArray.toString(), msg);
            } else {
                JSONObject resJsonObject = jsonObject.getJSONObject("response");
                apiCallback.onAPIResponse(requestId, isSuccess, resJsonObject.toString(), msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MyApplication.getInstance().showNormalDailog(context, e.getMessage());
            apiCallback.onAPIResponse(requestId, false, null, e.getMessage());
        }


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
