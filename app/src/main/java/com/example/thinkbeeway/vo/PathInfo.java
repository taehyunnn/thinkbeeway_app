package com.example.thinkbeeway.vo;

import android.os.Parcel;
import android.os.Parcelable;

public class PathInfo implements Parcelable {

    private String name;
    private String description;
    private int turnType;
    private int index;
//    private int distance;
//    private int time;
//    private String pointType;


    public PathInfo(String description) {
        this.description = description;
    }

    public PathInfo(String name, String description, int turnType, int distance, int time, String pointType, int index) {
        this.name = name;
        this.description = description;
        this.turnType = turnType;
        this.index = index;
//        this.distance = distance;
//        this.time = time;
//        this.pointType = pointType;
    }

    public PathInfo(String name, String description, int turnType, int index) {
        this.name = name;
        this.description = description;
        this.turnType = turnType;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getTurnType() {
        return turnType;
    }

//    public int getDistance() {
//        return distance;
//    }
//
//    public int getTime() {
//        return time;
//    }
//
//    public String getPointType() {
//        return pointType;
//    }

    protected PathInfo(Parcel in) {
        name = in.readString();
        description = in.readString();
        turnType = in.readInt();
//        distance = in.readInt();
//        time = in.readInt();
//        pointType = in.readString();
    }

    public static final Creator<PathInfo> CREATOR = new Creator<PathInfo>() {
        @Override
        public PathInfo createFromParcel(Parcel in) {
            return new PathInfo(in);
        }

        @Override
        public PathInfo[] newArray(int size) {
            return new PathInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeInt(turnType);
        parcel.writeInt(index);
//        parcel.writeString(pointType);
//        parcel.writeInt(distance);
//        parcel.writeInt(time);
    }

    @Override
    public String toString() {
        return description;
    }
}
