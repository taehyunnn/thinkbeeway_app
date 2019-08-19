package com.example.thinkbeeway.vo;

import android.os.Parcel;
import android.os.Parcelable;

public class Place implements Parcelable {
    private String id;
    private String name;
    private String telNo;
    private double noorLat; // 중심점 위도
    private double noorLon; // 중심점 경도
    private String upperAddrName;   // 주소 대분류
    private String middleAddrName;  // 주소 중분류
    private String lowerAddrName;   // 주소 소분류
    private String detailAddrName;  // 주소 세분류
    private String roadName;        // 도로명 주소
    private String rpFlag;          // 길안내 요청에 필요한 값

    public Place() {}

    public Place(String name){
        this.name = name;
        this.upperAddrName = "";
        this.middleAddrName = "";
        this.lowerAddrName = "";
        this.detailAddrName = "";
    }

    public Place(String id, String name, String telNo, double noorLat, double noorLon, String upperAddrName, String middleAddrName, String lowerAddrName, String detailAddrName, String roadName, String rpFlag) {
        this.id = id;
        this.name = name;
        this.telNo = telNo;
        this.noorLat = noorLat;
        this.noorLon = noorLon;
        this.upperAddrName = upperAddrName;
        this.middleAddrName = middleAddrName;
        this.lowerAddrName = lowerAddrName;
        this.detailAddrName = detailAddrName;
        this.roadName = roadName;
        this.rpFlag = rpFlag;
    }

    protected Place(Parcel in) {
        id = in.readString();
        name = in.readString();
        telNo = in.readString();
        noorLat = in.readDouble();
        noorLon = in.readDouble();
        upperAddrName = in.readString();
        middleAddrName = in.readString();
        lowerAddrName = in.readString();
        detailAddrName = in.readString();
        roadName = in.readString();
        rpFlag = in.readString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTelNo() {
        return telNo;
    }

    public double getNoorLat() {
        return noorLat;
    }

    public double getNoorLon() {
        return noorLon;
    }

    public String getFullAddrName() {
        return upperAddrName +" "+middleAddrName+" "+lowerAddrName+" "+detailAddrName;
    }

    public String getUpperAddrName() {
        return upperAddrName;
    }

    public String getMiddleAddrName() {
        return middleAddrName;
    }

    public String getLowerAddrName() {
        return lowerAddrName;
    }

    public String getDetailAddrName() {
        return detailAddrName;
    }

    public String getRoadName() {
        return roadName;
    }

    public String getRpFlag() {
        return rpFlag;
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(upperAddrName);
        parcel.writeString(middleAddrName);
        parcel.writeString(lowerAddrName);
        parcel.writeString(detailAddrName);
        parcel.writeString(telNo);
    }
}
