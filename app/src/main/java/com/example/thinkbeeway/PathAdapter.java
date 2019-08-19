package com.example.thinkbeeway;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thinkbeeway.vo.PathInfo;
import com.example.thinkbeeway.vo.PathInfo;

import java.util.List;

public class PathAdapter extends BaseAdapter {

    public static final int STAIGHT = 11;
    public static final int TURN_LEFT = 12;
    public static final int TURN_RIGHT = 13;
    public static final int TURN_LEFT_8 = 16;
    public static final int TURN_LEFT_10 = 17;
    public static final int TURN_RIGHT_2 = 18;
    public static final int TURN_RIGHT_4 = 19;
    public static final int PASS = 184;
    public static final int PASS1 = 185;
    public static final int PASS2 = 186;
    public static final int PASS3 = 187;
    public static final int PASS4 = 188;
    public static final int PASS5 = 189;
    public static final int PEDE_OVERPASS = 125;
    public static final int UNDERGROUND = 126;
    public static final int STAIR = 127;
    public static final int SLOP_WAY = 128;
    public static final int START = 200;
    public static final int END = 201;
    public static final int CROSS_WORK = 211;
    public static final int CROSS_WORK_LEFT = 212;
    public static final int CROSS_WORK_RIGHT = 213;
    public static final int CROSS_WORK_8 = 214;
    public static final int CROSS_WORK_10 = 215;
    public static final int CROSS_WORK_2 = 216;
    public static final int CROSS_WORK_4 = 217;

    List<PathInfo> pathInfoList;
    Context context;
    LayoutInflater layoutInflater;

    public PathAdapter() { }

    public PathAdapter(List<PathInfo> pathInfoList, Context context) {
        this.pathInfoList = pathInfoList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return pathInfoList.size();
    }

    @Override
    public Object getItem(int i) {
        return pathInfoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemLayout = view;
        if(itemLayout == null){
            itemLayout = layoutInflater.inflate(R.layout.listview_path, viewGroup, false);
        }

        ImageView type = itemLayout.findViewById(R.id.turnType);
        TextView pathIndex =  itemLayout.findViewById(R.id.pathIndex);
//        TextView name = itemLayout.findViewById(R.id.pathName);
        TextView description = itemLayout.findViewById(R.id.pathDescription);

        // 데이터 설정
        description.setText(pathInfoList.get(i).getDescription());
        pathIndex.setText(pathInfoList.get(i).getIndex()+"");

        switch (pathInfoList.get(i).getTurnType()){
            case STAIGHT :
                type.setImageResource(R.drawable.straight);
                break;
            case TURN_LEFT :
                type.setImageResource(R.drawable.turn_left);
                break;
            case TURN_RIGHT :
                type.setImageResource(R.drawable.turn_right);
                break;
            case TURN_LEFT_8 :
                type.setImageResource(R.drawable.turn_left);
                break;
            case TURN_LEFT_10 :
                type.setImageResource(R.drawable.turn_left);
                break;
            case TURN_RIGHT_2 :
                type.setImageResource(R.drawable.turn_right);
                break;
            case TURN_RIGHT_4 :
                type.setImageResource(R.drawable.turn_right);
                break;
            case PASS :
                break;
            case PASS1 :
                break;
            case PASS2 :
                break;
            case PASS3 :
                break;
            case PASS4 :
                break;
            case PASS5 :
                break;
            case PEDE_OVERPASS :
                break;
            case UNDERGROUND :
                break;
            case STAIR :
                break;
            case SLOP_WAY :
                break;
            case START :
                type.setImageResource(R.drawable.start_icon);
                break;
            case END :
                type.setImageResource(R.drawable.end_icon);
                break;
            case CROSS_WORK :
                type.setImageResource(R.drawable.cross_walk_icon);
                break;
            case CROSS_WORK_LEFT :
                type.setImageResource(R.drawable.cross_walk_icon);
                break;
            case CROSS_WORK_RIGHT :
                type.setImageResource(R.drawable.cross_walk_icon);
                break;
            case CROSS_WORK_8 :
                type.setImageResource(R.drawable.cross_walk_icon);
                break;
            case CROSS_WORK_10 :
                type.setImageResource(R.drawable.cross_walk_icon);
                break;
            case CROSS_WORK_2 :
                type.setImageResource(R.drawable.cross_walk_icon);
                break;
            case CROSS_WORK_4 :
                type.setImageResource(R.drawable.cross_walk_icon);
                break;
            default :
                break;
        }

        return itemLayout;
    }

    public void replacePath(List<Parcelable> parcelableList){

    }
}
