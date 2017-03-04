package com.sidproj.nagpurdrs.application;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.multidex.MultiDex;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sidproj.nagpurdrs.R;
import com.sidproj.nagpurdrs.constants.RequestConstant;
import com.sidproj.nagpurdrs.constants.URLConstants;
import com.sidproj.nagpurdrs.entities.AppointmentDo;
import com.sidproj.nagpurdrs.entities.DoctorLoginProfileDo;
import com.sidproj.nagpurdrs.entities.UserProfileDo;
import com.sidproj.nagpurdrs.location.LocationModel;
import com.sidproj.nagpurdrs.model.LocalModel;
import com.sidproj.nagpurdrs.screens.SplashActivity;
import com.sidproj.nagpurdrs.volly.APICallback;
import com.sidproj.nagpurdrs.volly.APIHandler;
import com.sidproj.nagpurdrs.volly.LruBitmapCache;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Random;

public class MyApplication extends Application implements APICallback {
    public static MyApplication myApplication = null;

    public static final String TAG = MyApplication.class
            .getSimpleName();
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    public LocationModel locationModel;

    private UserProfileDo userProfileDo;
    private DoctorLoginProfileDo doctorLoginProfileDo;

    private Activity currentActivity;
    private Handler patientNotificationHandler = null;
    private Handler drNotificationHandler = null;

    public static MyApplication getInstance() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        MultiDex.install(this);

        //location model
        locationModel = new LocationModel(this);

    }

    public void requestPatientNotification(boolean needLoading) {
        userProfileDo = LocalModel.getInstance().getUserProfileDo();

        if (userProfileDo != null) {
            String url = URLConstants.URL_GET_APPOINTMENT_LIST;
            url = url.replace("{user_id}", userProfileDo.getId() + "");
            APIHandler apiHandler = new APIHandler(this, this, RequestConstant.REQUEST_GET_APPOINTMENT_LIST,
                    Request.Method.GET, url, needLoading, "Refreshing Appointment List...", null);
            apiHandler.requestAPI();

            //this will keep on calling this method recursively after a time interval
            if (patientNotificationHandler == null)
                patientNotificationHandler = new Handler();

            patientNotificationHandler.removeCallbacks(patientNotificationRunnable);
            patientNotificationHandler.postDelayed(patientNotificationRunnable, 10000);
        }
    }

    public void requestDrNotification(boolean needLoading) {
        doctorLoginProfileDo = LocalModel.getInstance().getDoctorLoginProfileDo();

        if (doctorLoginProfileDo != null) {
            String url = URLConstants.URL_GET_DR_APPOINTMENT_LIST;
            url = url.replace("{dr_id}", doctorLoginProfileDo.getId() + "");
            APIHandler apiHandler = new APIHandler(this, this, RequestConstant.REQUEST_GET_DR_APPOINTMENT_LIST,
                    Request.Method.GET, url, needLoading, "Refreshing Appointment List...", null);
            apiHandler.requestAPI();

            //this will keep on calling this method recursively after a time interval
            if (drNotificationHandler == null)
                drNotificationHandler = new Handler();

            drNotificationHandler.removeCallbacks(drNotificationRunnable);
            drNotificationHandler.postDelayed(drNotificationRunnable, 10000);
        }
    }

    public void stopNotificationCheck() {
        if (patientNotificationHandler != null) {
            patientNotificationHandler.removeCallbacks(patientNotificationRunnable);
        }
        if (drNotificationHandler != null) {
            drNotificationHandler.removeCallbacks(drNotificationRunnable);
        }
    }

    private Runnable patientNotificationRunnable = new Runnable() {
        @Override
        public void run() {
            requestPatientNotification(false);
        }
    };

    private Runnable drNotificationRunnable = new Runnable() {
        @Override
        public void run() {
            requestDrNotification(false);
        }
    };


    public void enableGPS(final Activity activity) {
        float sourceLat = locationModel.getLatitude();
        float sourceLong = locationModel.getLongitude();
        if (sourceLat == 0.0 && sourceLong == 0.0) {
            LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle(getResources().getString(R.string.enableGPSText));  // GPS not found
                builder.setMessage(getResources().getString(R.string.enableGPSBody)); // Want to enable?
                builder.setPositiveButton(getResources().getString(R.string.settingsGPSText), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        activity.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setCancelable(false);
                builder.create().show();
            }
        }

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue, new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, int tag) {
        // set the default tag if tag is empty
        req.setTag(tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(int tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }


    /**
     * Checking for all possible internet providers
     **/
    public boolean isConnectingToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        return false;
    }

    public boolean checkConnection(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();
        if (activeNetworkInfo != null) { // connected to the internet
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                return true;
            } else if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                return true;
            }
        }
        return false;
    }

    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public boolean checkPermission(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("External storage permission is necessary");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }


    public void showAleart(Context context, final DailogCallback dailogCallback, String title, String bodyText, String yesBtnText, String noBtnText) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage(bodyText);
        builder1.setTitle(title);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                yesBtnText,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        dailogCallback.onDailogYesClick();
                    }
                });

        builder1.setNegativeButton(
                noBtnText,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        dailogCallback.onDailogNoClick();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    @Override
    public void onAPIResponse(int requestId, boolean isSuccess, String response, String errorString) {
        switch (requestId) {
            case RequestConstant.REQUEST_GET_APPOINTMENT_LIST:
                if (isSuccess && response != null) {
                    System.out.println(">>Appointment response >> " + response.toString());
                    Gson gson = new Gson();
                    ArrayList<AppointmentDo> appointmentDos = gson.fromJson(response.toString(), new TypeToken<ArrayList<AppointmentDo>>() {
                    }.getType());
                    checkIfAnyNewNotficationArrives(appointmentDos);
                }
                break;
            case RequestConstant.REQUEST_GET_DR_APPOINTMENT_LIST:
                if (isSuccess && response != null) {
                    System.out.println(">>Dr Appointment response >> " + response.toString());
                    Gson gson = new Gson();
                    ArrayList<AppointmentDo> appointmentDos = gson.fromJson(response.toString(), new TypeToken<ArrayList<AppointmentDo>>() {
                    }.getType());
                    checkIfAnyNewNotficationArrivesForDr(appointmentDos);
                }
                break;
        }

    }

    private void checkIfAnyNewNotficationArrivesForDr(ArrayList<AppointmentDo> newAppointments) {
        boolean anyNewAppointment = false;
        ArrayList<AppointmentDo> oldAppointments = LocalModel.getInstance().getAppointmentList();
        if (oldAppointments != null && newAppointments != null) {
            for (int i = 0; i < newAppointments.size(); i++) {
                boolean isFoundInOldOne = false;
                AppointmentDo newAppointment = newAppointments.get(i);

                for (int j = 0; j < oldAppointments.size(); j++) {
                    if (oldAppointments.get(j).getId() == newAppointment.getId()) {
                        if (oldAppointments.get(j).getStatus() == newAppointment.getStatus()) {
                            isFoundInOldOne = true;
                            break;
                        }
                    }
                }
                if (!isFoundInOldOne) {
                    anyNewAppointment = true;
                    showLocalNotification(newAppointment, true);
                    if (currentActivity != null) {
                        showSnackBar(currentActivity, formNotificationMsgBasedOnStatusForDr(newAppointment));
                    }
                }
            }
        } else if (newAppointments != null) {
            for (int i = 0; i < newAppointments.size(); i++) {
                showLocalNotification(newAppointments.get(i), true);
            }
        }
        LocalModel.getInstance().setAppointmentList(newAppointments);
        if (anyNewAppointment) {
            EventBus.getDefault().post(newAppointments);
        }
    }

    private void checkIfAnyNewNotficationArrives(ArrayList<AppointmentDo> newAppointments) {
        boolean anyNewAppointment = false;
        ArrayList<AppointmentDo> oldAppointments = LocalModel.getInstance().getAppointmentList();
        if (oldAppointments != null && newAppointments != null) {
            for (int i = 0; i < newAppointments.size(); i++) {
                boolean isFoundInOldOne = false;
                AppointmentDo newAppointment = newAppointments.get(i);

                for (int j = 0; j < oldAppointments.size(); j++) {
                    if (oldAppointments.get(j).getId() == newAppointment.getId()) {
                        if (oldAppointments.get(j).getStatus() == newAppointment.getStatus()) {
                            isFoundInOldOne = true;
                            break;
                        }
                    }
                }
                if (!isFoundInOldOne) {
                    anyNewAppointment = true;
                    showLocalNotification(newAppointment, false);
                    if (currentActivity != null) {
                        showSnackBar(currentActivity, formNotificationMsgBasedOnStatus(newAppointment));
                    }
                }
            }
        } else if (newAppointments != null) {
            for (int i = 0; i < newAppointments.size(); i++) {
                showLocalNotification(newAppointments.get(i), false);
            }
        }

        LocalModel.getInstance().setAppointmentList(newAppointments);

        if (anyNewAppointment) {
            EventBus.getDefault().post(newAppointments);
        }
    }


    public interface DailogCallback {
        public void onDailogYesClick();

        public void onDailogNoClick();
    }

    public void showNormalDailog(Context context, String bodyText) {
        try {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage(bodyText);
            builder1.setCancelable(true);

            builder1.setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void showLocalNotification(AppointmentDo appointmentDo, boolean isDr) {
        if (appointmentDo.getStatus() == AppointmentDo.STATUS_PENDING
                || appointmentDo.getStatus() == AppointmentDo.STATUS_PROCESSED) {
            return;
        }
        String title = "Nagpur Doctors";

        String description = "";
        if (isDr) {
            description = formNotificationMsgBasedOnStatusForDr(appointmentDo);
        } else {
            description = formNotificationMsgBasedOnStatus(appointmentDo);
        }

        if (description.trim().length() == 0) {
            return;
        }

        Intent intent = new Intent(MyApplication.getInstance(), SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(MyApplication.getInstance(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder b = new NotificationCompat.Builder(MyApplication.getInstance());
        Bitmap largeIcon = BitmapFactory.decodeResource(MyApplication.getInstance().getResources(), R.drawable.dr_logo);

        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle(title);
        bigTextStyle.bigText(description);

        b.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.dr_logo)
                .setLargeIcon(largeIcon)
                .setTicker(title + ": " + description)
                .setContentTitle(title)
                .setStyle(bigTextStyle)
                .setContentText(description)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                .setContentIntent(contentIntent);


        NotificationManager notificationManager = (NotificationManager) MyApplication.getInstance().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(generateRandomNum(), b.build());
    }

    public void showSnackBar(Activity activity, String textToShow) {
        if (textToShow != null && textToShow.length() > 0) {
            Snackbar snack = Snackbar.make(activity.findViewById(android.R.id.content), textToShow, Snackbar.LENGTH_LONG);
            View view = snack.getView();
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
            params.gravity = Gravity.TOP;
            view.setLayoutParams(params);
            snack.show();
        }
    }

    public int generateRandomNum() {
        int min = 1;
        int max = 100;
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }

    private String formNotificationMsgBasedOnStatus(AppointmentDo appointmentDo) {
        String msg = "";
        switch (appointmentDo.getStatus()) {
            case AppointmentDo.STATUS_PENDING:
                msg = "Your Appointment still in pending. Wait for doctor to approve.";
                break;
            case AppointmentDo.STATUS_APPROVED:
                msg = "Congrats your Appointment of " + appointmentDo.getDr_name() + " has been approved. Please be present on " + appointmentDo.getDate_time();
                break;
            case AppointmentDo.STATUS_CANCELLED:
                msg = "Sorry your Appointment of " + appointmentDo.getDr_name() + " has been cancelled. Please try to take new appointment or contact doctor";
                break;
            case AppointmentDo.STATUS_PROCESSED:
                msg = "Appointment of " + appointmentDo.getDr_name() + " has been already processed. Thanks.";
                break;
        }
        return msg;
    }

    private String formNotificationMsgBasedOnStatusForDr(AppointmentDo appointmentDo) {
        String msg = "";
        switch (appointmentDo.getStatus()) {
            case AppointmentDo.STATUS_PENDING:
                msg = "You got a new Appointment request from " + appointmentDo.getPatient_name()
                        + " of dated " + appointmentDo.getDate_time() + ". Please take action.";
                break;
//            case AppointmentDo.STATUS_APPROVED:
//                msg = "Congrats your Appointment of " + appointmentDo.getDr_name() + " has been approved. Please be present on " + appointmentDo.getDate_time();
//                break;
//            case AppointmentDo.STATUS_CANCELLED:
//                msg = "Sorry your Appointment of " + appointmentDo.getDr_name() + " has been cancelled. Please try to take new appointment or contact doctor";
//                break;
//            case AppointmentDo.STATUS_PROCESSED:
//                msg = "Appointment of " + appointmentDo.getDr_name() + " has been already processed. Thanks.";
//                break;
        }
        return msg;
    }

    public Activity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(Activity currentActivity) {
        this.currentActivity = currentActivity;
    }
}

