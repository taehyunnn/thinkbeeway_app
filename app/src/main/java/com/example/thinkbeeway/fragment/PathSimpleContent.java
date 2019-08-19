package com.example.thinkbeeway.fragment;

import android.content.Context;
import android.os.Bundle;
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

public class PathSimpleContent extends Fragment implements MainActivity.OnBackKeyPressedListener {

    private TextView mode;    // 최단 경로, 안전 경로
    private TextView time;    // 소요 시간
    private TextView distance; // 거리
    private ImageButton detailPath; // 상세경로 버튼
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_path_simple_content,container,false);
        mode = rootView.findViewById(R.id.mode);
        time = rootView.findViewById(R.id.time);
        distance = rootView.findViewById(R.id.distance);
        detailPath = rootView.findViewById(R.id.detailPath);

        detailPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).getDetailPath();
            }
        });
        return rootView;
    }
    public void changeSimpleContent(Bundle path){

        int time = path.getInt("time");
        int minuteSum = time >= 60 ? time/60 : 1 ;  // 총 시간으로 총 분으로 바꾸고 1분 미만은 무조건 1분으로

        int distance = path.getInt("distance");

        // 1시간 이상이면 형식 바꿔주기
        if(minuteSum >= 60){
            this.time.setText((minuteSum /60)+"시간 "+(minuteSum % 60)+"분");
        }
        else {
            this.time.setText(minuteSum +"분");
        }

        // 1km 이상이면 형식 바꿔주기
        if( distance >= 1000 ){
            this.distance.setText((distance/1000)+"."+((distance%1000)/100)+"km");
        }
        else {
            this.distance.setText(distance+"m");
        }

        mode.setText(path.getString("mode"));


        ((MainActivity)getActivity()).pushOnBackKeyPressedListener(this);
    }

    @Override
    public void onStart() {
        Log.d("PathSimpleContent","onStart");
        super.onStart();
    }

    @Override
    public void onAttach(Context context) {
        Log.d("PlaceFrament","onStart");
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        Log.d("PathSimpleContent","onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d("PathSimpleContent","onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("PathSimpleContent","onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d("PathSimpleContent","onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        Log.d("PathSimpleContent","onDetach");
        super.onDetach();
    }

    @Override
    public void onBack() {
        getFragmentManager().popBackStack();
    }
}
