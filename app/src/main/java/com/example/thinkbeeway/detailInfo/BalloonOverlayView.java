package com.example.thinkbeeway.detailInfo;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.thinkbeeway.R;

public class BalloonOverlayView extends FrameLayout {

    private LinearLayout layout;
    private TextView title;
    private TextView subTitle;
    private TextView content1;
    private TextView content2;

    public BalloonOverlayView(Context context, String labelName, String id, String land, String road) {

        super(context);

        setPadding(10, 0, 10, 0);
        layout = new LinearLayout(context);
        layout.setVisibility(VISIBLE);

        setupView(context, layout, labelName, id, land, road);

        LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.NO_GRAVITY;
        addView(layout, params);
    }


    protected void setupView(Context context, final ViewGroup parent, String labelName, String id, String land, String road) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View view = inflater.inflate(R.layout.bubble_popup, parent, true);

        title = (TextView) view.findViewById(R.id.bubble_title);
        subTitle = (TextView) view.findViewById(R.id.bubble_subtitle);
        content1  = view.findViewById(R.id.content1);
        content2 = view.findViewById(R.id.content2);

        setTitle("관리: " +labelName);
        setSubTitle(id);
        setContent1(land);
        setContent2(road);

    }

    public void setTitle(String str) {
        title.setText(str);
    }

    public void setSubTitle(String str) {
        subTitle.setText(str);
    }

    public void setContent1(String str) { content1.setText(str);}

    public void setContent2(String str) { content2.setText(str);}
}