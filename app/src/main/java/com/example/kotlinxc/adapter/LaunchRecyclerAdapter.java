package com.example.kotlinxc.adapter;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kotlinxc.R;
import com.example.kotlinxc.bean.ToolEntity;
import com.example.kotlinxc.databinding.ItemLaunchLayoutBinding;

/**
 * Created by zf on 2018/2/7.
 */

public class LaunchRecyclerAdapter extends DataBoundBaseAdapter<ToolEntity, ItemLaunchLayoutBinding> {

    public LaunchRecyclerAdapter() {
    }


    @Override
    protected ItemLaunchLayoutBinding createBinding(ViewGroup parent) {

        ItemLaunchLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_launch_layout, parent, false);

        return binding;
    }

    @Override
    protected void bind(final ItemLaunchLayoutBinding binding, final ToolEntity item) {
        binding.setTooBean(item);

        final View itemView = binding.getRoot();

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), item.clazz);
                itemView.getContext().startActivity(intent);
            }
        });
    }
}
