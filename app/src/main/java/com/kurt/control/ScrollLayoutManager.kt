package com.kurt.control

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

class ScrollLayoutManager : GridLayoutManager {

    constructor(context: Context, span: Int) : super(context, span)

    override fun requestChildRectangleOnScreen(
            parent: RecyclerView, child: View, rect: Rect, immediate: Boolean,
            focusedChildVisible: Boolean): Boolean {
        val isHorizontalMode = orientation == 0

        if (isHorizontalMode) {
            val parentCenter = width / 2
            val childLeft = child.left + rect.left
            val childCenter = childLeft + rect.width() / 2

            val dx = childCenter - parentCenter

            if (dx != 0) {
                parent.smoothScrollBy(dx, 0)
                return true
            }
        } else {
            val parentCenter = height / 2
            val childTop = child.top + rect.top
            val childCenter = childTop + rect.height() / 2

            val dy = childCenter - parentCenter

            if (dy != 0) {
                parent.smoothScrollBy(0, dy)
                return true
            }
        }
        return false
    }
}