package com.sidproj.nagpurdrs.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DoctorLoginProfileDo extends RealmObject {

    @PrimaryKey
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

    public void setDrSpecializationId(int drSpecializationId) {
        this.drSpecializationId = drSpecializationId;
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
}
