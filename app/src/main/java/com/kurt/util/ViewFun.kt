package com.kurt.util

import android.support.v4.view.ViewCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.kurt.control.GlideApp

object ViewFun {

    public fun ViewGroup.inflate(layoutRes: Int): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, false)
    }

    fun ImageView.load(res: Int) {
        Glide.with(context).load(res).into(this)
    }

    fun ImageView.load(url: String, holder: Int = 0, error: Int = 0) {
        GlideApp.with(context).load(url).placeholder(holder).error(error).into(this)
    }

    fun View.pop() {
        val scale = if (hasFocus()) 1.1F else 1.0F
        ViewCompat.animate(this).scaleX(scale).scaleY(scale).setDuration(50).start()
    }


    fun View.visible(){
        visibility = View.VISIBLE
    }

    fun View.invisivle(){
        visibility = View.INVISIBLE
    }

    fun View.gone(){
        visibility = View.GONE
    }

}