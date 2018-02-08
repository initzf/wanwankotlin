package com.example.kotlinxc.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;

/**
 * Created by zf on 2018/2/8.
 */

public class MyLayoutManager extends RecyclerView.LayoutManager {
    private static final String TAG = "MyLayoutManager";
    private int verticalScrollOffset = 0;

    private int totalHeight = 0;

    private SparseArray<Rect> allItemFrames = new SparseArray<>();
    private SparseBooleanArray hasAttachedItems = new SparseBooleanArray();

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT);
    }


    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {

        if (getItemCount() <= 0) {
            return;
        }
        // 跳过preLayout，preLayout主要用于支持动画
        if (state.isPreLayout()) {
            return;
        }
        //在布局之前，将所有的子View先Detach掉，放入到Scrap缓存中
        detachAndScrapAttachedViews(recycler);
        //定义竖直方向的偏移量
        int offsetY = 0;
        totalHeight = 0;

        int hei = 0;

        for (int i = 0; i < getItemCount(); i++) {

            //这里就是从缓存里面取出
            View view = recycler.getViewForPosition(i);
            //将View加入到RecyclerView中
            addView(view);
            measureChildWithMargins(view, 0, 0);
            int width = getDecoratedMeasuredWidth(view);
            int height = getDecoratedMeasuredHeight(view);

            totalHeight += height;

            hei = height;
            Rect frame = allItemFrames.get(i);
            if (frame == null) {
                frame = new Rect();
            }
            frame.set(0, offsetY, width, offsetY + height);
            // 将当前的Item的Rect边界数据保存
            allItemFrames.put(i, frame);
            // 由于已经调用了detachAndScrapAttachedViews，因此需要将当前的Item设置为未出现过
            hasAttachedItems.put(i, false);
            //将竖直方向偏移量增大height
            offsetY += height;
        }
        //如果所有子View的高度和没有填满RecyclerView的高度，
        // 则将高度设置为RecyclerView的高度
        totalHeight = Math.max(totalHeight, getVerticalSpace());
        recycleAndFillItems(recycler, state);

        Log.i(TAG, "onLayoutChildren: " + offsetY + "--------" + totalHeight + " -------" + hei);

    }


    public int getVerticalSpace() {
        return getHeight() - getPaddingBottom() - getPaddingTop();
    }


    public int getHorizontalSpace() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }


    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {

        detachAndScrapAttachedViews(recycler);

        int travel = dy;

        if (verticalScrollOffset + dy < 0) {
            travel = -verticalScrollOffset;
        } else if (verticalScrollOffset + dy > totalHeight - getVerticalSpace()) {
            travel = totalHeight - getVerticalSpace() - verticalScrollOffset;
        }

        verticalScrollOffset += travel;

        offsetChildrenVertical(-travel);

        recycleAndFillItems(recycler, state);

        Log.d("--->", " childView count:" + getChildCount());

        return travel;
    }


    private void recycleAndFillItems(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.isPreLayout()) { // 跳过preLayout，preLayout主要用于支持动画
            return;
        }

        Rect displayFrame = new Rect(0, verticalScrollOffset, getHorizontalSpace(), verticalScrollOffset + getVerticalSpace());

        Rect childFrame = new Rect();

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            childFrame.left = getDecoratedLeft(child);
            childFrame.top = getDecoratedTop(child);
            childFrame.right = getDecoratedRight(child);
            childFrame.bottom = getDecoratedBottom(child);
            //如果Item没有在显示区域，就说明需要回收
            if (!Rect.intersects(displayFrame, childFrame)) {
                //回收掉滑出屏幕的View
                removeAndRecycleView(child, recycler);
            }
        }

        for (int i = 0; i < getItemCount(); i++) {
            if (Rect.intersects(displayFrame, allItemFrames.get(i))) {
                View scrap = recycler.getViewForPosition(i);
                measureChildWithMargins(scrap, 0, 0);
                addView(scrap);

                Rect frame = allItemFrames.get(i);

                layoutDecorated(scrap,
                    frame.left,
                    frame.top - verticalScrollOffset,
                    frame.right,
                    frame.bottom - verticalScrollOffset);
            }
        }
    }


}
