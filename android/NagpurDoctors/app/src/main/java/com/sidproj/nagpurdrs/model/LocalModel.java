package com.sidproj.nagpurdrs.model;

import android.content.Context;

import com.sidproj.nagpurdrs.entities.AppointmentDo;
import com.sidproj.nagpurdrs.entities.UserProfileDo;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by siddharth on 1/31/2017.
 */
public class LocalModel {
    public static LocalModel instance = null;

    private UserProfileDo userProfileDo = null;
    private ArrayList<AppointmentDo> appointmentList = new ArrayList<>();

    public static LocalModel getInstance() {
        if (instance == null) {
            instance = new LocalModel();
        }
        return instance;
    }

    public UserProfileDo loadUserProfileIfExist(Context context) {
        userProfileDo = Realm.getInstance(context).where(UserProfileDo.class).findFirst();
        return userProfileDo;
    }

    public void saveUserProfile(Context context, UserProfileDo userProfileDo) {
        this.userProfileDo = userProfileDo;
        Realm myRealm = Realm.getInstance(context);
        myRealm.beginTransaction();
        myRealm.copyToRealm(userProfileDo);
        myRealm.commitTransaction();
    }

    public void removeUserProfileAndAppointmentsInfoOnLogout(Context context) {
        Realm myRealm = Realm.getInstance(context);
        myRealm.beginTransaction();
        UserProfileDo userProfileDo = myRealm.where(UserProfileDo.class).findFirst();
        userProfileDo.removeFromRealm();
        myRealm.commitTransaction();
        appointmentList.clear();
    }

    public UserProfileDo getUserProfileDo() {
        return userProfileDo;
    }

    public void setUserProfileDo(UserProfileDo userProfileDo) {
        this.userProfileDo = userProfileDo;
    }

    public ArrayList<AppointmentDo> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(ArrayList<AppointmentDo> appointmentList) {
        this.appointmentList = appointmentList;
    }
}
