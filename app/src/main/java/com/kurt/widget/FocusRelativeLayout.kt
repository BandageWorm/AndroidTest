package com.kurt.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.RelativeLayout

class FocusRelativeLayout: RelativeLayout {
    private val focusDrawer: FocusDrawer = FocusDrawer(this)

    constructor(context: Context) : super(context)
    constructor(context: Context, aSet: AttributeSet?) : super(context, aSet)

    init {
        setOnFocusChangeListener{_, b ->
            focusDrawer.setDrawFocus(b)
        }
    }

    override fun dispatchDraw(canvas: Canvas?) {
        focusDrawer.onDraw(canvas)
        super.dispatchDraw(canvas)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        requestFocus()
        return super.dispatchTouchEvent(ev)
    }
}