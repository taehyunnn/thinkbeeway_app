package com.example.thinkbeeway.animator;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;

import com.example.thinkbeeway.R;

public class FacilityAnimator {
    private Context context;
    private ObjectAnimator bellAnimeX ;
    private ObjectAnimator cctvAnimeX ;
    private ObjectAnimator shopAnimeX ;
    private ObjectAnimator policeAnimeX ;
    private ObjectAnimator protectAnimeX ;
    private ObjectAnimator lampAnimeX ;
    private ObjectAnimator bellAnimeY ;
    private ObjectAnimator cctvAnimeY ;
    private ObjectAnimator shopAnimeY ;
    private ObjectAnimator policeAnimeY ;
    private ObjectAnimator protectAnimeY ;
    private ObjectAnimator lampAnimeY ;

    private ObjectAnimator facilityUp;
    private ObjectAnimator facilityDown;

    private AnimatorSet bellSet;
    private AnimatorSet cctvSet;
    private AnimatorSet shopSet;
    private AnimatorSet policeSet;
    private AnimatorSet protectSet;
    private AnimatorSet lampSet;

    public FacilityAnimator(){}
    public FacilityAnimator(Context context){
        this.context = context;

        bellAnimeX = (ObjectAnimator) AnimatorInflater.loadAnimator(context, R.animator.bell_animator_x);
        cctvAnimeX = (ObjectAnimator) AnimatorInflater.loadAnimator(context, R.animator.cctv_animator_x);
        shopAnimeX = (ObjectAnimator) AnimatorInflater.loadAnimator(context, R.animator.shop_animator_x);
        policeAnimeX = (ObjectAnimator) AnimatorInflater.loadAnimator(context, R.animator.police_animator_x);
        protectAnimeX = (ObjectAnimator) AnimatorInflater.loadAnimator(context, R.animator.protect_animator_x);
        lampAnimeX = (ObjectAnimator) AnimatorInflater.loadAnimator(context, R.animator.lamp_animator_x);
        bellAnimeY = (ObjectAnimator) AnimatorInflater.loadAnimator(context, R.animator.bell_animator_y);
        cctvAnimeY = (ObjectAnimator) AnimatorInflater.loadAnimator(context, R.animator.cctv_animator_y);
        shopAnimeY = (ObjectAnimator) AnimatorInflater.loadAnimator(context, R.animator.shop_animator_y);
        policeAnimeY = (ObjectAnimator) AnimatorInflater.loadAnimator(context, R.animator.police_animator_y);
        protectAnimeY = (ObjectAnimator) AnimatorInflater.loadAnimator(context, R.animator.protect_animator_y);
        lampAnimeY = (ObjectAnimator) AnimatorInflater.loadAnimator(context, R.animator.lamp_animator_y);

        facilityUp = (ObjectAnimator) AnimatorInflater.loadAnimator(context, R.animator.facility_animator_up);
        facilityDown = (ObjectAnimator) AnimatorInflater.loadAnimator(context,R.animator.facility_animator_down);

        bellSet = new AnimatorSet();
        cctvSet = new AnimatorSet();
        policeSet = new AnimatorSet();
        shopSet = new AnimatorSet();
        protectSet = new AnimatorSet();
        lampSet = new AnimatorSet();

        bellSet.playTogether(bellAnimeX,bellAnimeY);
        cctvSet.playTogether(cctvAnimeX,cctvAnimeY);
        protectSet.playTogether(protectAnimeX,protectAnimeY);
        policeSet.playTogether(policeAnimeX,policeAnimeY);
        lampSet.playTogether(lampAnimeX,lampAnimeY);
        shopSet.playTogether(shopAnimeX,shopAnimeY);
    }

    public AnimatorSet getBellSet() {
        return bellSet;
    }

    public AnimatorSet getCctvSet() {
        return cctvSet;
    }

    public AnimatorSet getShopSet() {
        return shopSet;
    }

    public AnimatorSet getPoliceSet() {
        return policeSet;
    }

    public AnimatorSet getProtectSet() {return protectSet;}

    public AnimatorSet getLampSet() {
        return lampSet;
    }

    public ObjectAnimator getFacilityUp() {return facilityUp;}

    public ObjectAnimator getFacilityDown() {return facilityDown;}
}
