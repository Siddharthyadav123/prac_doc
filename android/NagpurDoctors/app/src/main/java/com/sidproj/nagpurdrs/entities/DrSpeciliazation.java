package com.sidproj.nagpurdrs.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by siddharth on 11/23/2016.
 */
public class DrSpeciliazation implements Parcelable {
    public int id;
    public String name;

    protected DrSpeciliazation(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DrSpeciliazation> CREATOR = new Creator<DrSpeciliazation>() {
        @Override
        public DrSpeciliazation createFromParcel(Parcel in) {
            return new DrSpeciliazation(in);
        }

        @Override
        public DrSpeciliazation[] newArray(int size) {
            return new DrSpeciliazation[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
