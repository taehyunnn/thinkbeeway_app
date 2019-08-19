package com.example.thinkbeeway.detailInfo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.skt.Tmap.TMapMarkerItem2;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

public class MarkerOverlay extends TMapMarkerItem2 {

    private DisplayMetrics dm = null;

    private Context mContext = null;
    private BalloonOverlayView balloonView = null;

    @Override
    public Bitmap getIcon() {
        return super.getIcon();
    }

    @Override
    public void setIcon(Bitmap bitmap) {
        super.setIcon(bitmap);
    }

    @Override
    public void setTMapPoint(TMapPoint point) {
        super.setTMapPoint(point);
    }

    @Override
    public TMapPoint getTMapPoint() {
        return super.getTMapPoint();
    }

    @Override
    public void setPosition(float dx, float dy) {
        super.setPosition(dx, dy);
    }

    /**
     * 풍선뷰 영역을 설정한다.
     */
    @Override
    public void setCalloutRect(Rect rect) {
        super.setCalloutRect(rect);
    }

    public MarkerOverlay(Context context, String labelName, String id, String land, String road) {
        this.mContext = context;

        dm = new DisplayMetrics();
        WindowManager wmgr = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        wmgr.getDefaultDisplay().getMetrics(dm);

        balloonView = new BalloonOverlayView(mContext, labelName, id, land, road);

        balloonView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        balloonView.layout(0, 0, balloonView.getMeasuredWidth(), balloonView.getMeasuredHeight());
    }

    @Override
    public void draw(Canvas canvas, TMapView mapView, boolean showCallout) {
        int x = mapView.getRotatedMapXForPoint(getTMapPoint().getLatitude(), getTMapPoint().getLongitude());
        int y = mapView.getRotatedMapYForPoint(getTMapPoint().getLatitude(), getTMapPoint().getLongitude());

        canvas.save();
        canvas.rotate(-mapView.getRotate(), mapView.getCenterPointX(), mapView.getCenterPointY());

        balloonView.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        int nTempX =  x - balloonView.getMeasuredWidth() / 2;
        int nTempY =  y - balloonView.getMeasuredHeight();

        canvas.translate(nTempX, nTempY);
        balloonView.draw(canvas);
        canvas.restore();
    }

    public boolean onSingleTapUp(PointF point, TMapView mapView) {
        mapView.showCallOutViewWithMarkerItemID(getID());
        return false;
    }
}