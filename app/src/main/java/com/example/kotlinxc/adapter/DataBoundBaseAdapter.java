package com.example.kotlinxc.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zf on 2018/2/7.
 */

public abstract class DataBoundBaseAdapter<T, V extends ViewDataBinding> extends RecyclerView.Adapter<DataBoundViewBaseHolder<V>> {

    private List<T> items;

    @Override
    public final DataBoundViewBaseHolder<V> onCreateViewHolder(ViewGroup parent, int viewType) {

        V binding = createBinding(parent);

        return new DataBoundViewBaseHolder<>(binding);
    }

    protected abstract V createBinding(ViewGroup parent);


    @Override
    public void onBindViewHolder(DataBoundViewBaseHolder<V> holder, int position) {
        bind(holder.binding, items.get(position));

        holder.binding.executePendingBindings();
    }


    protected abstract void bind(V binding, T item);


    public void setItems(List<T> data) {
        this.items = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }
}
