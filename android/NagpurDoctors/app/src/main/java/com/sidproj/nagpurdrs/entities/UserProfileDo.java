package com.sidproj.nagpurdrs.entities;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

/**
 * Created by siddharth on 12/6/2016.
 */
public class UserProfileDo implements Parcelable {
    public String uname;
    public String pwd;
    public String full_name;
    public String address;
    public String mobile_no;
    public String gender;
    public String dob;
    public String profile_pic_url = null;

    public UserProfileDo() {

    }

    protected UserProfileDo(Parcel in) {
        uname = in.readString();
        pwd = in.readString();
        full_name = in.readString();
        address = in.readString();
        mobile_no = in.readString();
        gender = in.readString();
        dob = in.readString();
        profile_pic_url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uname);
        dest.writeString(pwd);
        dest.writeString(full_name);
        dest.writeString(address);
        dest.writeString(mobile_no);
        dest.writeString(gender);
        dest.writeString(dob);
        dest.writeString(profile_pic_url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserProfileDo> CREATOR = new Creator<UserProfileDo>() {
        @Override
        public UserProfileDo createFromParcel(Parcel in) {
            return new UserProfileDo(in);
        }

        @Override
        public UserProfileDo[] newArray(int size) {
            return new UserProfileDo[size];
        }
    };

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getProfile_pic_url() {
        return profile_pic_url;
    }

    public void setProfile_pic_url(String profile_pic_url) {
        this.profile_pic_url = profile_pic_url;
    }

    /**
     * Method to form JSON request body
     *
     * @return
     */
    public String toJSONString() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("uname", uname);
            jsonObject.put("pwd", pwd);
            jsonObject.put("full_name", full_name);
            jsonObject.put("address", address);
            jsonObject.put("mobile_no", mobile_no);
            jsonObject.put("gender", gender);
            jsonObject.put("dob", dob);
            jsonObject.put("profile_pic_url", profile_pic_url);
            return jsonObject.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
