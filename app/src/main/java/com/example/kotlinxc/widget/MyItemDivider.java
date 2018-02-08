package com.example.kotlinxc.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.kotlinxc.tools.AppUtils;

/**
 * Created by zf on 2018/2/8.
 */

public class MyItemDivider extends RecyclerView.ItemDecoration {

    private Paint mPaint;

    private int d1, d2;

    public MyItemDivider(Context context) {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(AppUtils.dp2px(context, 16));

        d1 = AppUtils.dp2px(context, 45);
        d2 = AppUtils.dp2px(context, 1);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildAdapterPosition(view);

        if (position % 3 == 0) {
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

            if (index % 3 == 0) {

                int top = view.getTop() - d1;
                int bottom = view.getTop();

                c.drawRect(left, top, right, bottom, mPaint);

                float titleX = left;
                float titleY = bottom - mPaint.getFontMetrics().descent;
                //绘制Title

                mPaint.setColor(Color.WHITE);
                c.drawText("22222", titleX, titleY, mPaint);
                mPaint.setColor(Color.RED);
            } else {
                c.drawRect(left, view.getTop() - d2, right, view.getTop(), mPaint);
            }
        }
    }

}
