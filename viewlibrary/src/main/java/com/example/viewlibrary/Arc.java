package com.example.viewlibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Danni on 2017/7/6.
 */

public class Arc extends View {
    private Paint mPaint;
    RectF mrectF;
    float length;

    private int startRawX;
    private int startRawY;
    private onViewClick mViewClick;
    private OnClickListener mListener;
    /**
     * 圆的宽度
     */
    private int mCircleWidth = 3;

    public Arc(Context context) {
        this(context, null);
    }

    public Arc(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Arc(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int x = (int)event.getX();
        int y = (int)event.getY();
        int rawX = (int)event.getRawX();
        int rawY = (int)event.getRawY();
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                startRawX = (int)event.getRawX();
                startRawY = (int)event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                //判断点击在View内部
                if (x + getX() < getRight() && y + getTop() < getBottom()){
                    mViewClick.onClick(rawX - startRawX,rawY - startRawY);
                    mListener.onClick(this);
                }
                break;
        }
        return true;
    }

    @Override
    public void setOnClickListener(OnClickListener listener){
        this.mListener = listener;
    }



    public void setOnViewClickListener(onViewClick viewClick){
        this.mViewClick = viewClick;
    }

    public interface onViewClick{
        /**
         *
         * @param scrollX 从按下到抬起,X轴方向移动的距离
         * @param scrollY 从按下到抬起,Y轴方向移动的距离
         */
        void onClick(float scrollX,float scrollY);
    }






    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setAntiAlias(true);//取消锯齿
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mCircleWidth);
        mPaint.setColor(Color.RED);

        mrectF=new RectF((float)(getWidth()*0.1),
                (float)(getWidth()*0.1),
                (float)(getWidth()*0.9),
                (float)(getWidth()*0.9));
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingButtom = getPaddingBottom();

        canvas.drawRect(paddingLeft,
                paddingTop,getWidth() - paddingLeft,getHeight() - paddingTop,mPaint);


        Log.d("tag","realwidthsize is " + getWidth() );
        Log.d("tag","realheightsize is " + getHeight() );
//        canvas.drawArc(mrectF,0,180,false,mPaint);//true为扇形
    }
    @Override
    protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
        setMeasuredDimension(
                measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec)
        );
    }

    private int measureWidth(int measureSpec){
        int result=0;
        int Mode= MeasureSpec.getMode(measureSpec);
        int Size= MeasureSpec.getSize(measureSpec);   //自己考虑后的尺寸，若是wrap_content 那就是全屏
        //Size=1080  是像素

        if (Mode== MeasureSpec.EXACTLY){
            result=Size;
        }else {
            result=600;
            if (Mode== MeasureSpec.AT_MOST){
                result=Math.min(result,Size);

            }
        }
        Log.d("tag","widthsize is " + result);
        return result;
    }
    private int measureHeight(int measureSpec){
        int result=0;
        int Mode= MeasureSpec.getMode(measureSpec);
        int Size= MeasureSpec.getSize(measureSpec);


        if (Mode== MeasureSpec.EXACTLY){
            result=Size;
        }else {
            result=200;
            if (Mode== MeasureSpec.AT_MOST){
                result=Math.min(result,Size);

            }
        }
        Log.d("tag","heightsize is " + result);
        return result;
    }
    /*Paint mArcPaint;
    RectF mrectF;
    float length,mSweepValue;
    public Arc(Context context) {
        this(context, null);
    }

    public Arc(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Arc(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public Arc(Context context, AttributeSet attrs,
                        int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
    @Override
    protected void onSizeChanged(int w,int h,int oldW,int oldH){
        super.onSizeChanged(w,h,oldW,oldH);
        length = w;
    }
    public void init(){
        mArcPaint=new Paint();
        mArcPaint.setColor(Color.BLACK);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(20);
        mrectF=new RectF((float)(length*0.1),
                (float)(length*0.1),
                (float)(length*0.9),
                (float)(length*0.9));
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawArc(mrectF,270,360,false,mArcPaint);


    }*/
}
