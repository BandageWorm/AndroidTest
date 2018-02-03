package com.qiming.kurtapp.ui;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * Created by kurtg on 18/2/3.
 */

public class MarqueeTextView extends AppCompatTextView {

    private boolean focus;

    public MarqueeTextView(Context context) {
        super(context);
        init();
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setSingleLine();
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setMarqueeRepeatLimit(-1);
//        setMaxEms(10);
    }

    @Override
    public boolean isFocused() {
        return focus;
    }

    public void setFocus(final boolean b) {
        focus = b;
        post(new Runnable() {
            @Override
            public void run() {
                MarqueeTextView.super.setSelected(b);
            }
        });
    }
}
