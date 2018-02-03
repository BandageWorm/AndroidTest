package com.qiming.kurtapp;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kurtg on 18/2/3.
 */

public class MyRecyclerView extends RecyclerView {

    private Context context = getContext();
    private ArrayList<String> list = new ArrayList<>();

    public MyRecyclerView(Context context) {
        super(context);
        init();
    }

    public MyRecyclerView(Context context, AttributeSet set) {
        super(context, set);
        init();
    }

    public MyRecyclerView(Context context, AttributeSet set, int def) {
        super(context, set, def);
        init();
    }

    private void init() {
        for(int i = 10; i < 13; i++){
            list.add("AA" + i);
        }
        setLayoutManager(new MyLayoutManager(context));
        setAdapter(new MyAdapter());
        addItemDecoration(decoration);

    }

    private ItemDecoration decoration = new ItemDecoration() {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.top = 20;
            outRect.bottom = 20;
            outRect.left = 150;
        }
    };

    class MyAdapter extends Adapter<MyHolder> {
        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LinearLayout view = (LinearLayout)LayoutInflater.from(context).inflate(R.layout.recycler_item, null);
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
        LinearLayout parent;
        EduNewButton textView;

        public MyHolder(LinearLayout parent) {
            super(parent);
            this.parent = (LinearLayout) parent;
            textView = (EduNewButton) parent.findViewById(R.id.text);
        }

        public void update(int pos) {
            textView.setText(list.get(pos));
        }
    }

    class MyLayoutManager extends GridLayoutManager {
        public MyLayoutManager(Context context) {
            super(context, 1);
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
}
