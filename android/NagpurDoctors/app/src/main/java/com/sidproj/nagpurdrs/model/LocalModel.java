package com.sidproj.nagpurdrs.model;

import android.content.Context;

import com.sidproj.nagpurdrs.entities.AppointmentDo;
import com.sidproj.nagpurdrs.entities.DoctorLoginProfileDo;
import com.sidproj.nagpurdrs.entities.UserProfileDo;

import java.util.ArrayList;

import io.realm.Realm;

public class LocalModel {
    public static LocalModel instance = null;
    private UserProfileDo userProfileDo = null;
    private DoctorLoginProfileDo doctorLoginProfileDo = null;
    private boolean isDoctorLogin = false;

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

    public DoctorLoginProfileDo loadDrProfileIfExist(Context context) {
        doctorLoginProfileDo = Realm.getInstance(context).where(DoctorLoginProfileDo.class).findFirst();
        return doctorLoginProfileDo;
    }

    public void saveUserProfile(Context context, UserProfileDo userProfileDo) {
        this.userProfileDo = userProfileDo;
        Realm myRealm = Realm.getInstance(context);
        myRealm.beginTransaction();
        myRealm.copyToRealm(userProfileDo);
        myRealm.commitTransaction();
    }

    public void saveDoctorProfile(Context context, DoctorLoginProfileDo doctorLoginProfileDo) {
        this.doctorLoginProfileDo = doctorLoginProfileDo;
        Realm myRealm = Realm.getInstance(context);
        myRealm.beginTransaction();
        myRealm.copyToRealm(doctorLoginProfileDo);
        myRealm.commitTransaction();
    }

    public void removeUserProfileAndAppointmentsInfoOnLogout(Context context) {
        try {
            Realm myRealm = Realm.getInstance(context);
            myRealm.beginTransaction();
            UserProfileDo userProfileDo = myRealm.where(UserProfileDo.class).findFirst();
            userProfileDo.removeFromRealm();
            myRealm.commitTransaction();
            appointmentList.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void removeDoctorProfileAndAppointmentsInfoOnLogout(Context context) {
        try {
            Realm myRealm = Realm.getInstance(context);
            myRealm.beginTransaction();
            DoctorLoginProfileDo doctorLoginProfileDo = myRealm.where(DoctorLoginProfileDo.class).findFirst();
            doctorLoginProfileDo.removeFromRealm();
            myRealm.commitTransaction();
            appointmentList.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public DoctorLoginProfileDo getDoctorLoginProfileDo() {
        return doctorLoginProfileDo;
    }

    public boolean isDoctorLogin() {
        return isDoctorLogin;
    }

    public void setDoctorLogin(boolean doctorLogin) {
        isDoctorLogin = doctorLogin;
    }
}
