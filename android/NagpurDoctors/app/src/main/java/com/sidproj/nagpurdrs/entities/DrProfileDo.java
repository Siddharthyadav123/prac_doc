package com.sidproj.nagpurdrs.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by siddharth on 11/23/2016.
 */
public class DrProfileDo implements Parcelable {

    private int id;
    private String drFullName;
    private int drSpecializationId;
    private String drQualification;
    private int drExperience;
    private int drConsulationFee;
    private String drContactNum;
    private String drClinicName;
    private String drClinicAddress;
    private float drClinicRating;
    private String drVerifiedVia;
    private String drServices;
    private String dr_profile_pic_url;
    private String dr_clinic_pics_url;
    private String dr_clinic_lat;
    private String dr_clinic_long;
    private int dr_morning_time_slot_id;
    private int dr_afternoon_time_slot_id;
    private int dr_evening_time_slot_id;
    private int dr_night_time_slot_id;
    private String dr_working_day;
    private String specializationText;

    protected DrProfileDo(Parcel in) {
        id = in.readInt();
        drFullName = in.readString();
        drSpecializationId = in.readInt();
        drQualification = in.readString();
        drExperience = in.readInt();
        drConsulationFee = in.readInt();
        drContactNum = in.readString();
        drClinicName = in.readString();
        drClinicAddress = in.readString();
        drClinicRating = in.readFloat();
        drVerifiedVia = in.readString();
        drServices = in.readString();
        dr_profile_pic_url = in.readString();
        dr_clinic_pics_url = in.readString();
        dr_clinic_lat = in.readString();
        dr_clinic_long = in.readString();
        dr_morning_time_slot_id = in.readInt();
        dr_afternoon_time_slot_id = in.readInt();
        dr_evening_time_slot_id = in.readInt();
        dr_night_time_slot_id = in.readInt();
        dr_working_day = in.readString();
        specializationText = in.readString();
    }

    public static final Creator<DrProfileDo> CREATOR = new Creator<DrProfileDo>() {
        @Override
        public DrProfileDo createFromParcel(Parcel in) {
            return new DrProfileDo(in);
        }

        @Override
        public DrProfileDo[] newArray(int size) {
            return new DrProfileDo[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrFullName() {
        return drFullName;
    }

    public void setDrFullName(String drFullName) {
        this.drFullName = drFullName;
    }

    public int getDrSpecializationId() {
        return drSpecializationId;
    }

    public void setDrSpecializationId(int drSpecialization) {
        this.drSpecializationId = drSpecialization;
    }

    public String getDrQualification() {
        return drQualification;
    }

    public void setDrQualification(String drQualification) {
        this.drQualification = drQualification;
    }

    public int getDrExperience() {
        return drExperience;
    }

    public void setDrExperience(int drExperience) {
        this.drExperience = drExperience;
    }

    public int getDrConsulationFee() {
        return drConsulationFee;
    }

    public void setDrConsulationFee(int drConsulationFee) {
        this.drConsulationFee = drConsulationFee;
    }

    public String getDrContactNum() {
        return drContactNum;
    }

    public void setDrContactNum(String drContactNum) {
        this.drContactNum = drContactNum;
    }

    public String getDrClinicName() {
        return drClinicName;
    }

    public void setDrClinicName(String drClinicName) {
        this.drClinicName = drClinicName;
    }

    public String getDrClinicAddress() {
        return drClinicAddress;
    }

    public void setDrClinicAddress(String drClinicAddress) {
        this.drClinicAddress = drClinicAddress;
    }

    public float getDrClinicRating() {
        return drClinicRating;
    }

    public void setDrClinicRating(float drClinicRating) {
        this.drClinicRating = drClinicRating;
    }

    public String getDrVerifiedVia() {
        return drVerifiedVia;
    }

    public void setDrVerifiedVia(String drVerifiedVia) {
        this.drVerifiedVia = drVerifiedVia;
    }

    public String getDrServices() {
        return drServices;
    }

    public void setDrServices(String drServices) {
        this.drServices = drServices;
    }

    public String getDr_profile_pic_url() {
        return dr_profile_pic_url;
    }

    public void setDr_profile_pic_url(String dr_profile_pic_url) {
        this.dr_profile_pic_url = dr_profile_pic_url;
    }

    public String getDr_clinic_pics_url() {
        return dr_clinic_pics_url;
    }

    public void setDr_clinic_pics_url(String dr_clinic_pics_url) {
        this.dr_clinic_pics_url = dr_clinic_pics_url;
    }

    public String getDr_clinic_lat() {
        return dr_clinic_lat;
    }

    public void setDr_clinic_lat(String dr_clinic_lat) {
        this.dr_clinic_lat = dr_clinic_lat;
    }

    public String getDr_clinic_long() {
        return dr_clinic_long;
    }

    public void setDr_clinic_long(String dr_clinic_long) {
        this.dr_clinic_long = dr_clinic_long;
    }

    public int getDr_morning_time_slot_id() {
        return dr_morning_time_slot_id;
    }

    public void setDr_morning_time_slot_id(int dr_morning_time_slot_id) {
        this.dr_morning_time_slot_id = dr_morning_time_slot_id;
    }

    public int getDr_afternoon_time_slot_id() {
        return dr_afternoon_time_slot_id;
    }

    public void setDr_afternoon_time_slot_id(int dr_afternoon_time_slot_id) {
        this.dr_afternoon_time_slot_id = dr_afternoon_time_slot_id;
    }

    public int getDr_evening_time_slot_id() {
        return dr_evening_time_slot_id;
    }

    public void setDr_evening_time_slot_id(int dr_evening_time_slot_id) {
        this.dr_evening_time_slot_id = dr_evening_time_slot_id;
    }

    public int getDr_night_time_slot_id() {
        return dr_night_time_slot_id;
    }

    public void setDr_night_time_slot_id(int dr_night_time_slot_id) {
        this.dr_night_time_slot_id = dr_night_time_slot_id;
    }

    public String getDr_working_day() {
        return dr_working_day;
    }

    public void setDr_working_day(String dr_working_day) {
        this.dr_working_day = dr_working_day;
    }

    public String getSpecializationText() {
        return specializationText;
    }

    public void setSpecializationText(String specializationText) {
        this.specializationText = specializationText;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(drFullName);
        dest.writeInt(drSpecializationId);
        dest.writeString(drQualification);
        dest.writeInt(drExperience);
        dest.writeInt(drConsulationFee);
        dest.writeString(drContactNum);
        dest.writeString(drClinicName);
        dest.writeString(drClinicAddress);
        dest.writeFloat(drClinicRating);
        dest.writeString(drVerifiedVia);
        dest.writeString(drServices);
        dest.writeString(dr_profile_pic_url);
        dest.writeString(dr_clinic_pics_url);
        dest.writeString(dr_clinic_lat);
        dest.writeString(dr_clinic_long);
        dest.writeInt(dr_morning_time_slot_id);
        dest.writeInt(dr_afternoon_time_slot_id);
        dest.writeInt(dr_evening_time_slot_id);
        dest.writeInt(dr_night_time_slot_id);
        dest.writeString(dr_working_day);
        dest.writeString(specializationText);

    }
}
