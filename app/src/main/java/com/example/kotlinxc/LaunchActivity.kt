package com.example.kotlinxc

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.example.kotlinxc.adapter.LaunchRecyclerAdapter
import com.example.kotlinxc.bean.ToolEntity
import kotlinx.android.synthetic.main.activity_launch.*


/**
 * 启动页
 */
class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        var adapter = LaunchRecyclerAdapter()
        adapter.setItems(ToolEntity.getData())

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        recyclerView.adapter = adapter
    }


}
