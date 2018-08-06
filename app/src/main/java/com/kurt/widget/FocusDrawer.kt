package com.kurt.widget

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v4.view.ViewCompat
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.kurt.R

class FocusDrawer(view: View)  {
    private val view: View = view
    private val rect: Rect = Rect()
    private val paint: Paint = Paint()
    private var focusDrawable: Drawable = view.context.getDrawable(R.drawable.focus)
    private var normalDrawable: Drawable? = view.context.getDrawable(R.drawable.normal)

    constructor(view: View, nDr: Drawable?) : this(view) {
        normalDrawable = nDr
    }

    init {
        view.getDrawingRect(rect)
        view.isFocusable = true
        view.isFocusableInTouchMode = true
        if (view is ViewGroup) view.descendantFocusability = ViewGroup.FOCUS_BLOCK_DESCENDANTS
        paint.color = view.context.resources.getColor(R.color.colorAccent)
        view.setOnTouchListener({v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                v.requestFocus()
                return@setOnTouchListener true
            }
            return@setOnTouchListener false
        })
        view.setOnFocusChangeListener ({v, has ->
            val scale = if (has) 1.1F else 1.0F
            ViewCompat.animate(v).scaleX(scale).scaleY(scale).setDuration(50).start()
        })
    }

    fun onDraw(canvas: Canvas?) {
        if (view.hasFocus()) {
            canvas?.drawRect(rect, paint)
            focusDrawable.setBounds(-37, -21, view.width + 37, view.height + 37)
            focusDrawable.draw(canvas)
        } else {
            normalDrawable?.setBounds(0, 0, view.width, view.height)
            normalDrawable?.draw(canvas)
        }
    }
}