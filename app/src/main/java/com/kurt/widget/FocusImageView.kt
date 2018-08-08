package com.kurt.widget

import android.content.Context
import android.graphics.Canvas
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet

class FocusImageView : AppCompatImageView {
    private val focusDrawer: FocusDrawer = FocusDrawer(this, null)

    constructor(context: Context) : super(context)
    constructor(context: Context, aSet: AttributeSet?) : super(context, aSet)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        focusDrawer.onDraw(canvas)
        super.dispatchDraw(canvas)
    }

    fun drawFocus(b: Boolean) {focusDrawer.setDrawFocus(b)}
}