package com.hh.calendartest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

/**
 * Created by haohe on 2017/8/9 0009.
 */

public class CoustomerGridView extends GridView {

    public CoustomerGridView(Context context) {
        super(context);
    }

    public CoustomerGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CoustomerGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >>2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

//        View v ;
//        Paint mPaint = new Paint();
//        mPaint.setColor(Color.parseColor("#777777"));
//        mPaint.setStyle(Paint.Style.STROKE);
//
//        for( int i = 0; i< getChildCount(); i++){
//            v = getChildAt(i);
//            canvas.drawRect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom(), mPaint);
//        }
        super.dispatchDraw(canvas);
    }
}
