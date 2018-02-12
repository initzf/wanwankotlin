package com.example.testmodule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_module.*

class ModuleActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_module)

        var a = "qweqweqwe"


        tvModuleTest.text = a


        tvModuleTest.setOnClickListener(this)


        when (a) {
            is String -> say("string")

            a -> say(a)

            else -> say("else")
        }

    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.tvModuleTest -> say("onClick")
        }

    }


    fun say(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

}
