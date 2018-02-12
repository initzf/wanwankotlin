package com.example.kotlinxc.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.kotlinxc.bean.ItemHeaderBean;
import com.example.kotlinxc.tools.AppUtils;

/**
 * Created by zf on 2018/2/8.
 */

public class MyItemDivider extends RecyclerView.ItemDecoration {
    private static final String TAG = "MyItemDivider";
    private Paint mPaint;

    private int d1, d2, d3;

    private Rect mRect;

    private CallBackDataListener mCallBackDataListener;


    public MyItemDivider(Context context, CallBackDataListener mCallBackDataListener) {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(AppUtils.dp2px(context, 16));

        d1 = AppUtils.dp2px(context, 45);
        d2 = AppUtils.dp2px(context, 1);
        d3 = AppUtils.dp2px(context, 15);

        mRect = new Rect();

        this.mCallBackDataListener = mCallBackDataListener;

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildAdapterPosition(view);

        if (mCallBackDataListener.onCallBack(position).isFirstViewInGroup()) {
            outRect.top = d1;
        } else {
            outRect.top = d2;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);


        int count = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < count; i++) {
            View view = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(view);

            if (mCallBackDataListener.onCallBack(index).isFirstViewInGroup()) {

               /*int top = view.getTop() - d1;

                int bottom = view.getTop();

                c.drawRect(left, top, right, bottom, mPaint);


                Log.i(TAG, "onDraw: i=" + i + "-----index=" + index + "---top=" + top + "----bottom=" + bottom + "-----D1=" + d1 + "----viewheight=" + view.getHeight());


                float titleX = left;

                String str = String.valueOf(index + 1);
                mPaint.getTextBounds(str, 0, str.length(), mRect);
                float titleY = (bottom) - (mPaint.getFontMetrics().descent + mRect.height());


                //绘制Title
                mPaint.setColor(Color.WHITE);

                c.drawText(str, titleX + d3, (titleY), mPaint);

                mPaint.setColor(Color.RED);*/

            } else {
                c.drawRect(left, view.getTop() - d2, right, view.getTop(), mPaint);
            }
        }
    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {

        int count = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int top = parent.getPaddingTop();
        int bottom = 0;

        Log.i(TAG, "onDrawOver: qweqweqwe");


        for (int i = 0; i < count; i++) {
            View view = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(view);

            ItemHeaderBean headerBean = mCallBackDataListener.onCallBack(index);

            if (i == 0) {
                //Log.i(TAG, "onDraw: i=" + i + "-----index=" + index + "------" + view.getTop());

                /*if (headerBean.isLastViewInGroup()) {
                    int tempTop = view.getBottom() - d1;
                    if (tempTop < top) {
                        top = tempTop;
                    }
                }*/
                bottom = top + d1;
            } else {
                if (headerBean.isFirstViewInGroup()) {
                    top = view.getTop() - d1;
                    bottom = view.getTop();
                }
            }
            drawText(c, left, top, right, bottom, headerBean.getmTitle());
        }
    }


    private void drawText(Canvas c, int left, int top, int right, int bottom, String text) {
        c.drawRect(left, top, right, bottom, mPaint);

        float titleX = left;

        if (mRect.isEmpty()) {
            mPaint.getTextBounds(text, 0, text.length(), mRect);
        }

        float titleY = bottom - mPaint.getFontMetrics().bottom - mRect.height();

        //绘制Title
        mPaint.setColor(Color.WHITE);
        c.drawText(text, titleX + d3, titleY, mPaint);
        mPaint.setColor(Color.RED);
    }


    public interface CallBackDataListener {
        ItemHeaderBean onCallBack(int position);
    }


}
