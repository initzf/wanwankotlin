package com.example.kotlinxc.adapter;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.kotlinxc.R;
import com.example.kotlinxc.databinding.ItemLayoutmanagerBinding;

/**
 * Created by zf on 2018/2/8.
 */

public class LayoutManagerAdapter extends DataBoundBaseAdapter<String, ItemLayoutmanagerBinding> {

    @Override
    protected ItemLayoutmanagerBinding createBinding(ViewGroup parent) {
        return DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_layoutmanager, parent, false);
    }

    @Override
    protected void bind(ItemLayoutmanagerBinding binding, String item) {
        binding.setStr(item);
    }
}
