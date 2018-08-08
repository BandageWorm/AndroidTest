package com.kurt.widget

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.kurt.R
import com.kurt.util.ContextFun.color
import com.kurt.util.ViewFun.pop

class FocusDrawer(view: View)  {
    private var focus: Boolean = false
    private val view: View = view
    private val rect: Rect = Rect()
    private val paint: Paint = Paint()
    private var focusDrawable: Drawable = view.context.getDrawable(R.drawable.focus)
    private var normalDrawable: Drawable? = view.context.getDrawable(R.drawable.normal)

    constructor(view: View, nDr: Drawable?) : this(view) {
        normalDrawable = nDr
    }

    fun setDrawFocus(b: Boolean) {
        focus = b
        view.invalidate()
    }

    init {
        view.getDrawingRect(rect)
        paint.color = view.context.color(R.color.colorAccent)
    }

    fun onDraw(canvas: Canvas?) {
        if (focus || view.hasFocus()) {
            canvas?.drawRect(rect, paint)
            focusDrawable.setBounds(-37, -21, view.width + 37, view.height + 37)
            focusDrawable.draw(canvas)
        } else {
            normalDrawable?.setBounds(0, 0, view.width, view.height)
            normalDrawable?.draw(canvas)
        }
        view.pop(focus)
    }
}