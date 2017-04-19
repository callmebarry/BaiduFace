package com.qqdemo.administrator.baiduface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2017/4/18.
 */

public class MyView extends ImageView {

    int left;
    int top;
    int width;
    int height;
    private final Paint mPaint;
    private List<Rect> rects;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

       if(this.rects!=null){
           for(Rect mRect:rects){
               canvas.drawRect(mRect, mPaint);
           }
       }

    }
    public void clear(){
        this.rects=null;
        invalidate();
    }
    public void drawRect( List<Rect> rects ){
        this.rects=rects;
        invalidate();
    }


}
