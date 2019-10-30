package com.midai.kotlinapplication.utils

import android.app.Activity
import android.content.Context
import android.widget.Toast
import java.security.AccessControlContext

object ToastUtils {
    var toast:Toast? = null
    fun ShowToast(context: Context, text:String){
        toast = Toast.makeText(context,"",Toast.LENGTH_SHORT)
        toast!!.setText(text)

        try {
            toast!!.show()
        } catch (var3: Exception) {
            var3.printStackTrace()
        }

    }
}