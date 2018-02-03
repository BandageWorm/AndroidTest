package com.qiming.kurtapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;


public class FocusRelativeLayout extends RelativeLayout {

    private FocusShape focusShape;

    public FocusRelativeLayout(Context context) {
        super(context);
        init();
    }

    public FocusRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FocusRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        focusShape = new FocusShape(this);
        setOnTouchListener(touchListener);
        setOnFocusChangeListener(focusListener);
        setOnClickListener(clickListener);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (focusShape != null){
            focusShape.onDraw(canvas);
        }
        super.dispatchDraw(canvas);

    }

    public class FocusShape {
        private View view;
        private Rect rect;
        private Paint paint;
        private Drawable focusDrawable;
        private Drawable normalDrawable;


        public FocusShape(View view) {
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

    private View.OnFocusChangeListener focusListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            view.setScaleX(b ? 1.1F : 1.0F);
            view.setScaleY(b ? 1.1F : 1.0F);
        }
    };

    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            view.requestFocus();
            return false;
        }
    };


    private OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getContext(), RecyclerActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            getContext().startActivity(intent);
        }
    };
}

