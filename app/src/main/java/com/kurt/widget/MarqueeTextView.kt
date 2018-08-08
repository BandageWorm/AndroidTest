package com.kurt.widget

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.text.TextUtils
import android.util.AttributeSet

class MarqueeTextView: AppCompatTextView {
    var focus: Boolean = false

    constructor(context: Context) : super(context)
    constructor(context: Context, aSet: AttributeSet?) : super(context, aSet)

    init {
        setSingleLine()
        ellipsize = TextUtils.TruncateAt.MARQUEE
        marqueeRepeatLimit = -1
    }

    override fun isFocused(): Boolean {
        return focus
    }

    fun marquee(b: Boolean) {
        focus = b
        post { super@MarqueeTextView.setSelected(b) }
    }
}