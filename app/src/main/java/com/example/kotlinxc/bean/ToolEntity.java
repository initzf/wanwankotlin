package com.example.kotlinxc.bean;

import com.example.kotlinxc.KtxActivity;
import com.example.kotlinxc.MainActivity;
import com.example.kotlinxc.ui.LayoutManagerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zf on 2018/2/7.
 */

public class ToolEntity {
    public Class<?> clazz;
    public String name;

    public ToolEntity(String name, Class<?> clazz) {
        this.clazz = clazz;
        this.name = name;
    }


    public static List<ToolEntity> getData() {
        List<ToolEntity> lists = new ArrayList<>();

        lists.add(new ToolEntity("测试协程", MainActivity.class));
        lists.add(new ToolEntity("Ktx 扩展", KtxActivity.class));
        lists.add(new ToolEntity("自定义layoutmanager", LayoutManagerActivity.class));

        return lists;
    }
}


