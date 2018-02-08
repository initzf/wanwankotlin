package com.example.kotlinxc.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.kotlinxc.R;
import com.example.kotlinxc.adapter.LayoutManagerAdapter;
import com.example.kotlinxc.widget.MyItemDivider;
import com.example.kotlinxc.widget.MyLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zf on 2018/2/8.
 */

public class LayoutManagerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_layoutmanager);

        mRecyclerView = findViewById(R.id.recyclerView);

        //mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mRecyclerView.addItemDecoration(new MyItemDivider(this));
        mRecyclerView.setLayoutManager(new MyLayoutManager());

        LayoutManagerAdapter adapter = new LayoutManagerAdapter();

        adapter.setItems(getData());

        mRecyclerView.setAdapter(adapter);
    }


    private List<String> getData() {
        List<String> lists = new ArrayList<>();
        for (char a = 'A'; a <= 'Z'; a++) {
            lists.add(String.valueOf(a));
        }
        return lists;
    }


}
