package com.example.thinkbeeway.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.thinkbeeway.MainActivity;
import com.example.thinkbeeway.R;
import com.example.thinkbeeway.vo.Place;

public class PlaceFragment extends Fragment implements MainActivity.OnBackKeyPressedListener {

    TextView name;
    TextView address;
    TextView tel;
    Place place;

    public static final int START_PLACE = 100;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("PlaceFragment","onCreateView");
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_place,container,false);

        name = rootView.findViewById(R.id.name);
        address = rootView.findViewById(R.id.address);
        tel = rootView.findViewById(R.id.telNo);

        ImageButton imageButton = rootView.findViewById(R.id.btnFindWayFragment);
        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).onFindWay(null);
            }
        });
        return rootView;
    }

    public void changePlace(Parcelable place){
        if(place instanceof Place){
            this.place = (Place)place;
            name.setText(this.place.getName());
            Log.d("changePlace",this.place.getFullAddrName());
            address.setText(this.place.getFullAddrName());
            tel.setText(this.place.getTelNo());

             ((MainActivity)getActivity()).pushOnBackKeyPressedListener(this);
        }
    }

   @Override
    public void onBack() {
        getFragmentManager().popBackStack();
    }

    @Override
    public void onStart() {
        Log.d("PlaceFragment","onStart");
        super.onStart();
    }

    @Override
    public void onAttach(Context context) {
        Log.d("PlaceFrament","onStart");
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        Log.d("PlaceFragment","onResue");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d("PlaceFragment","onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("PlaceFragment","onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d("PlaceFragment","onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        Log.d("PlaceFragment","onDetach");
        super.onDetach();
    }
}
