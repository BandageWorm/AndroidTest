package com.kurt.widget

import android.content.Context
import android.graphics.Canvas
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet

class FocusTextView : AppCompatTextView {
    private val focusDrawer: FocusDrawer = FocusDrawer(this)

    constructor(context: Context) : super(context)
    constructor(context: Context, aSet: AttributeSet?) : super(context, aSet)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        focusDrawer.onDraw(canvas)
        super.dispatchDraw(canvas)
    }
}