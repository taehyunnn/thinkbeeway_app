package com.example.thinkbeeway.vo;

import org.json.JSONArray;
import org.json.JSONException;

public class Facility  {
    private int bellFlag;
    private int cctvFlag;
    private int shopFlag;
    private int policeFlag;
    private int protectFlag;
    private int lampFlag;

    private String[] facilitiesName;

    public Facility() {
        facilitiesName = new String[]{"bell","cctv","police","protect","light","convenience"};
    }

    public int getBellFlag() {
        return bellFlag;
    }

    public void setBellFlag(int bellFlag) {
        this.bellFlag = bellFlag;
    }

    public int getCctvFlag() {
        return cctvFlag;
    }

    public void setCctvFlag(int cctvFlag) {
        this.cctvFlag = cctvFlag;
    }

    public int getShopFlag() {
        return shopFlag;
    }

    public void setShopFlag(int shopFlag) {
        this.shopFlag = shopFlag;
    }

    public int getPoliceFlag() {
        return policeFlag;
    }

    public void setPoliceFlag(int policeFlag) {
        this.policeFlag = policeFlag;
    }

    public int getProtectFlag() {
        return protectFlag;
    }

    public void setProtectFlag(int protectFlag) {
        this.protectFlag = protectFlag;
    }

    public int getLampFlag() {
        return lampFlag;
    }

    public void setLampFlag(int lampFlag) {
        this.lampFlag = lampFlag;
    }

    public void onBell() {
        bellFlag = 1;
    }

    public void offBell() {
        bellFlag = 0;
    }

    public void onCCTV() {
        cctvFlag = 1;
    }

    public void offCCTV() {
        cctvFlag = 0;
    }

    public void onPolice() {
        policeFlag = 1;
    }

    public void offPolice() {
        policeFlag = 0;
    }

    public void onProtect() {
        protectFlag = 1;
    }

    public void offProtect() {
        protectFlag = 0;
    }

    public void onLamp() {
        lampFlag = 1;
    }

    public void offLamp() {
        lampFlag = 0;
    }

    public void onShop() {
        shopFlag = 1;
    }

    public void offShop() {
        shopFlag  = 0;
    }

    public boolean getFourBtnOn() {
        if( (bellFlag | cctvFlag | lampFlag | shopFlag) == 1){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isContainsOne() {
        if((bellFlag | cctvFlag | protectFlag | policeFlag | lampFlag | shopFlag) == 1){
            return true;
        }
        else {
            return false;
        }
    }

    public JSONArray getFacilitiesName() throws JSONException {
        return new JSONArray(facilitiesName);
    }

    public String getFacilitiesFlag() {
        return "["+bellFlag+","+cctvFlag+","+policeFlag+","+protectFlag+","+lampFlag+","+shopFlag+"]";
    }
//    public String getFacilitiesFlag() {
//        return new String[]{bellFlag,cctvFlag,policeFlag,protectFlag,lampFlag,shopFlag};
//    }
}
