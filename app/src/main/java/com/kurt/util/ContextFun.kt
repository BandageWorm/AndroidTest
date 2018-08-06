package com.kurt.util

import android.content.Context
import android.os.Build
import android.support.annotation.ColorRes
import android.util.Log
import android.widget.Toast
import java.nio.charset.Charset

object ContextFun {

    fun Context.toast(message: CharSequence, type: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, type).show()
    }

    fun logV(vararg strs: Any?) {
        Log.v(this.javaClass.simpleName, strs.reduce{ acc, s:Any? -> "$acc ${s?:"null"}"}.toString())
    }

    fun logI(vararg strs: Any?) {
        Log.i(this.javaClass.simpleName, strs.reduce{ acc, s:Any? -> "$acc ${s?:"null"}"}.toString())
    }

    fun logD(vararg strs: Any?) {
        Log.v(this.javaClass.simpleName, strs.reduce{ acc, s:Any? -> "$acc ${s?:"null"}"}.toString())
    }

    fun logE(vararg strs: Any?) {
        Log.e(this.javaClass.simpleName, strs.reduce{ acc, s:Any? -> "$acc ${s?:"null"}"}.toString())
    }

    fun logE(strs: Any?, e: Throwable = Exception()) {
        Log.e(this.javaClass.simpleName, strs.toString(), e)
    }

    fun Context.readAsset(fileName: String) : String {
         this.resources.assets.open(fileName).use {
             return it.readBytes().toString(Charset.defaultCharset())
         }
    }

    fun Context.color(@ColorRes id:Int) = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> resources.getColor(id,null)
        else -> resources.getColor(id)
    }
}