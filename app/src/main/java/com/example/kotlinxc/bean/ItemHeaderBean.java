package com.example.kotlinxc.bean;

/**
 * Created by zf on 2018/2/9.
 */

public class ItemHeaderBean {

    private int mGroupID;
    private String mTitle;
    private int position;
    private int mGroupLength;

    public ItemHeaderBean(int mGroupID, String mTitle) {
        this.mGroupID = mGroupID;
        this.mTitle = mTitle;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getmGroupLength() {
        return mGroupLength;
    }

    public void setmGroupLength(int mGroupLength) {
        this.mGroupLength = mGroupLength;
    }


    public boolean isFirstViewInGroup() {
        return position == 0;
    }

    public boolean isLastViewInGroup() {
        return position == mGroupLength - 1 && position >= 0;
    }
}
