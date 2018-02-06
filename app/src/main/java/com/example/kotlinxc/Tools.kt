package com.example.kotlinxc

import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by zf on 2018/2/5.
 */
class Tools {

    fun getUserInfo(): String? {

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
        return buff.toString()
    }

}