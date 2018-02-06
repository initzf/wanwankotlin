package com.example.kotlinxc.tools

import com.example.kotlinxc.bean.UserInfoEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by zf on 2018/2/5.
 */
interface ApiService {
    @GET("/v2/user/{newsId}")
    fun getUserInfo(@Path("newsId") newsId: String): Call<UserInfoEntity>
}