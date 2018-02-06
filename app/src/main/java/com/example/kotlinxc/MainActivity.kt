package com.example.kotlinxc

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.kotlinxc.bean.UserInfoEntity
import com.example.kotlinxc.databinding.ActivityMainBinding
import com.example.kotlinxc.tools.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    private var mainBind: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBind = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainBind?.mainActivity = this

        runBlocking {

            var obj1 = async { getUserInfo2() }.await()

            say("runBlocking 中")

            mainBind?.name = obj1.name
        }

        Log.i(TAG, "代码会依次从上往下执行， 这个代码会在 runBlocking 协程 结束后调用 ")
    }


    /**
     * 打印当前所在线程
     * msg @param tag
     */
    fun say(msg: String) {
        Log.i(TAG, "${msg}当前所在线程:${Thread.currentThread().name} ")
    }


    /**
     * 使用原始方式获取  数据
     */
    fun getUserInfo1(): UserInfoEntity? {

        say("async中的 getUserInfo1")

        var url = URL("https://api.douban.com/v2/user/admin")

        var mOpenHTTP: HttpURLConnection = url.openConnection() as HttpURLConnection

        mOpenHTTP.requestMethod = "GET"

        mOpenHTTP.connect()

        var buff = StringBuffer()

        if (mOpenHTTP.responseCode == 200) {

            var bytes = ByteArray(1024)

            var input: InputStream = mOpenHTTP.inputStream

            while (true) {
                var len = input.read(bytes)
                if (len == -1) {
                    break
                }
                buff.append(String(bytes, 0, len))
            }
        }

        if (buff.length == 0) {
            return null
        }

        var entity = Gson().fromJson(buff.toString(), UserInfoEntity::class.java)

        return entity
    }


    /**
     * 使用retrogit 获取 数据
     */
    fun getUserInfo2(): UserInfoEntity {

        say("async中的 getUserInfo2")

        val retrofit = Retrofit.Builder()
        retrofit.baseUrl("https://api.douban.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()


        var service = retrofit.build()

        var mApiService = service.create(ApiService::class.java)

        var response = mApiService.getUserInfo("admin").execute()

        var body = response.body()

        return body as UserInfoEntity
    }


    /**
     * 页面跳转
     * 顺便测试一下传值
     */
    fun jump(msg: String) {
        //Toast.makeText(this, msg, Toast.LENGTH_LONG).show()

        val intent = Intent()
        intent.setClass(this, JavaActivity::class.java)
        intent.putExtra("msg", msg)
        startActivity(intent)
    }
}
