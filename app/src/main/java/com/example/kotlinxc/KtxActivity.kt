package com.example.kotlinxc

import android.content.Context
import android.content.SharedPreferences
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import androidx.content.edit
import androidx.net.toUri
import androidx.util.arrayMapOf
import com.example.kotlinxc.databinding.ActivityKtxBinding

/**
 * Created by zf on 2018/2/7.
 */
class KtxActivity : AppCompatActivity() {
    private val TAG = "KtxActivity"


    var spUtils: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        spUtils = getSharedPreferences("TEST", Context.MODE_PRIVATE)

        var bind = DataBindingUtil.setContentView<ActivityKtxBinding>(this, R.layout.activity_ktx);
        bind.ktxActivity = this

    }


    fun insertSp() {

        //sp.edit().putString("ws", "@").apply()

        spUtils?.edit {
            putString("key", null)
            putInt("int", 1)
        }
    }


    fun readSp() {

        var str: String? = spUtils?.getString("key", null)

        var number = spUtils?.getInt("int", 0)

        if (str.isNullOrEmpty()) {
            Toast.makeText(this, "null", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, " 读取到 key=  ${str}  和 int= ${number}", Toast.LENGTH_LONG).show()
        }
    }

    fun testOther() {

        var name = "xyz"
        var uri = name.toUri()

        Log.i(TAG, uri.toString())


        var map = arrayMapOf<String, String>()

        map.isNotEmpty()

    }
}