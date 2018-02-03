package com.qiming.kurtapp.ui;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.qiming.kurtapp.R;
import com.qiming.kurtapp.Utils;
import com.qiming.kurtapp.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by kurtg on 18/2/3.
 */

public class PosterView extends RecyclerView {

    private Context context = getContext();
    private List<Example.ResultBean> list = new ArrayList<>();

    public PosterView(Context context) {
        super(context);
        init();
    }

    public PosterView(Context context, AttributeSet set) {
        super(context, set);
        init();
    }

    public PosterView(Context context, AttributeSet set, int def) {
        super(context, set, def);
        init();
    }

    private void init() {
        Example json = Utils.getJsonExp(getContext());
        Log.i("vvv", json.toString());
        list = json.getSearchResultList();
        setLayoutManager(new MyLayoutManager(context));
        setAdapter(new MyAdapter());
        addItemDecoration(decoration);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Log.i("ttt", "focus:" + getFocusedChild());
            }
        }, 1000, 1000);

    }

    private ItemDecoration decoration = new ItemDecoration() {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.top = 50;
            outRect.bottom = 50;
            outRect.left = 20;
            outRect.right = 20;
        }
    };

    class MyAdapter extends Adapter<MyHolder> {
        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RelativeLayout view = (RelativeLayout)LayoutInflater.from(context).inflate(R.layout.poster_layout, null);
            view.setClipToPadding(false);
            view.setClipChildren(false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.update(position);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    class MyHolder extends ViewHolder {
        RelativeLayout parent;
        PosterImageView imageView;
        MarqueeTextView textView;

        public MyHolder(RelativeLayout parent) {
            super(parent);
            this.parent = parent;
            imageView = (PosterImageView) parent.findViewById(R.id.poster_image);
            textView = (MarqueeTextView) parent.findViewById(R.id.poster_text);
            parent.setOnTouchListener(touchListener);
            parent.setOnFocusChangeListener(focusListener);
            parent.setFocusable(true);
            parent.setFocusableInTouchMode(true);
        }

        public void update(int pos) {
            Example.ResultBean bean = list.get(pos);
            textView.setText(Utils.highlightTitle(bean.getMediaTitle()));
            Glide.with(getContext()).load(bean.getPoster()).into(imageView);
        }
    }

    class MyLayoutManager extends GridLayoutManager {
        public MyLayoutManager(Context context) {
            super(context, 3);
        }

        @Override
        public boolean requestChildRectangleOnScreen(RecyclerView parent, View child, Rect rect, boolean immediate,
                boolean focusedChildVisible) {
            boolean isHorizontalMode = getOrientation() == 0;

            if (isHorizontalMode) {
                final int parentCenter = getWidth() / 2;
                final int childLeft = child.getLeft() + rect.left;
                final int childCenter = childLeft + rect.width() / 2;

                final int dx = childCenter - parentCenter;

                if (dx != 0) {
                    parent.smoothScrollBy(dx, 0);
                    return true;
                }
            } else {
                final int parentCenter = getHeight() / 2;
                final int childTop = child.getTop() + rect.top;
                final int childCenter = childTop + rect.height() / 2;

                final int dy = childCenter - parentCenter;

                if (dy != 0) {
                    parent.smoothScrollBy(0, dy);
                    return true;
                }
            }
            return false;
        }
    }


    private OnTouchListener touchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(final View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                view.requestFocus();
                return true;
            }
            return false;
        }
    };

    private OnFocusChangeListener focusListener = new OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            view.setScaleX(b ? 1.1F : 1.0F);
            view.setScaleY(b ? 1.1F : 1.0F);
        }
    };
}
