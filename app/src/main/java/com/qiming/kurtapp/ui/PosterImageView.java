package com.qiming.kurtapp.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.qiming.kurtapp.R;


public class PosterImageView extends AppCompatImageView {

    private FocusDrawer focusDrawer = new FocusDrawer(this);

    public PosterImageView(Context context) {
        super(context);
        init();
    }

    public PosterImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PosterImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        focusDrawer = new FocusDrawer(this);
        setOnTouchListener(touchListener);
        setOnFocusChangeListener(focusListener);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (focusDrawer != null){
            focusDrawer.onDraw(canvas);
        }
        super.onDraw(canvas);

    }

    class FocusDrawer {
        private View view;
        private Rect rect;
        private Paint paint;
        private Drawable focusDrawable;
        private Drawable normalDrawable;


        public FocusDrawer(View view) {
            this.view = view;
            focusDrawable = view.getContext().getDrawable(R.drawable.focus);
            normalDrawable = view.getContext().getDrawable(R.drawable.normal);

        }

        public void onDraw(Canvas canvas) {
            if (rect == null) {
                rect = new Rect();
                view.getDrawingRect(rect);
                paint = new Paint();
                paint.setColor(Color.parseColor("#FF4081"));
            }

            if (view.hasFocus()) {
                canvas.drawRect(rect, paint);
                focusDrawable.setBounds(-37, -21, view.getWidth() + 37, view.getHeight() + 34);
                focusDrawable.draw(canvas);
            } else {
                normalDrawable.setBounds(0, 0, view.getWidth(), view.getHeight());
                normalDrawable.draw(canvas);
            }
        }
    }

    private OnTouchListener touchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(final View view, MotionEvent motionEvent) {
            view.requestFocus();
            view.performClick();
            return true;
        }
    };

    private OnFocusChangeListener focusListener = new OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            view.setScaleX(b ? 1.1F : 1.0F);
            view.setScaleY(b ? 1.1F : 1.0F);
            MarqueeTextView textView = ((MarqueeTextView) ((RelativeLayout) view.getParent()).getChildAt(1));
            textView.setEllipsize(b? TextUtils.TruncateAt.MARQUEE: TextUtils.TruncateAt.END);
            textView.setFocus(b);
        }
    };
}

