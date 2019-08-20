package com.example.thinkbeeway;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thinkbeeway.vo.Place;

import java.util.List;

public class PlaceAdapter extends BaseAdapter {

    List<Place> placeList;
    List<Integer> distanceList;
    Context context;
    LayoutInflater layoutInflater;

    public PlaceAdapter() { }

    public PlaceAdapter(List<Place> placeList, List<Integer> distanceList, Context context) {
        this.placeList = placeList;
        this.distanceList = distanceList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return placeList.size();
    }

    @Override
    public Object getItem(int i) {
        return placeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemLayout = view;
        if(itemLayout == null){
            itemLayout = layoutInflater.inflate(R.layout.listview_place, viewGroup, false);
        }

        TextView name = itemLayout.findViewById(R.id.name);
        TextView distance = itemLayout.findViewById(R.id.distance);
        TextView address = itemLayout.findViewById(R.id.address);
        TextView tel = itemLayout.findViewById(R.id.tel);

        // 데이터 설정
        Place place = placeList.get(i);
        name.setText(place.getName());

        // 거리 정보 있으면
        if(distanceList.size() > 0)
        {
            int distanceValue = (int)distanceList.get(i);
            // 1km 이상이면 형식 바꿔주기
            if( distanceValue >= 1000 ){
                distance.setText((distanceValue/1000)+"."+((distanceValue%1000)/100)+"km");
            }
            else {
                distance.setText(distanceValue+"m");
            }
        }

        address.setText(place.getFullAddrName());
        tel.setText(place.getTelNo());

        return itemLayout;
    }
}
