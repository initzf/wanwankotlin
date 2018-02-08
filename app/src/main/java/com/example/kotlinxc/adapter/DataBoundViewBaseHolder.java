package com.example.kotlinxc.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by zf on 2018/2/7.
 */

public class DataBoundViewBaseHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    public final T binding;

    public DataBoundViewBaseHolder(T bind) {
        super(bind.getRoot());
        this.binding = bind;
    }
}
