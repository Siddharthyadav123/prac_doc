package com.sidproj.nagpurdrs.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by siddharth on 1/31/2017.
 */
public class DrTimeSlotsDo implements Parcelable {
    private String[] morningTimeSlots;
    private String[] afternoonTimeSlots;
    private String[] eveningTimeSlots;
    private String[] nightTimeSlots;
    private String[] busySlopts;

    protected DrTimeSlotsDo(Parcel in) {
        morningTimeSlots = in.createStringArray();
        afternoonTimeSlots = in.createStringArray();
        eveningTimeSlots = in.createStringArray();
        nightTimeSlots = in.createStringArray();
        busySlopts = in.createStringArray();
    }

    public static final Creator<DrTimeSlotsDo> CREATOR = new Creator<DrTimeSlotsDo>() {
        @Override
        public DrTimeSlotsDo createFromParcel(Parcel in) {
            return new DrTimeSlotsDo(in);
        }

        @Override
        public DrTimeSlotsDo[] newArray(int size) {
            return new DrTimeSlotsDo[size];
        }
    };

    public String[] getMorningTimeSlots() {
        return morningTimeSlots;
    }

    public void setMorningTimeSlots(String[] morningTimeSlots) {
        this.morningTimeSlots = morningTimeSlots;
    }

    public String[] getAfternoonTimeSlots() {
        return afternoonTimeSlots;
    }

    public void setAfternoonTimeSlots(String[] afternoonTimeSlots) {
        this.afternoonTimeSlots = afternoonTimeSlots;
    }

    public String[] getEveningTimeSlots() {
        return eveningTimeSlots;
    }

    public void setEveningTimeSlots(String[] eveningTimeSlots) {
        this.eveningTimeSlots = eveningTimeSlots;
    }

    public String[] getNightTimeSlots() {
        return nightTimeSlots;
    }

    public void setNightTimeSlots(String[] nightTimeSlots) {
        this.nightTimeSlots = nightTimeSlots;
    }

    public String[] getBusySlopts() {
        return busySlopts;
    }

    public void setBusySlopts(String[] busySlopts) {
        this.busySlopts = busySlopts;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(morningTimeSlots);
        dest.writeStringArray(afternoonTimeSlots);
        dest.writeStringArray(eveningTimeSlots);
        dest.writeStringArray(nightTimeSlots);
        dest.writeStringArray(busySlopts);
    }
}
