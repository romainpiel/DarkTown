package com.romainpiel.darktown;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class ScrollAwareScrollView extends ScrollView {

    private OnScrollListener onScrollListener;

    public ScrollAwareScrollView(Context context) {
        super(context);
    }

    public ScrollAwareScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollAwareScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ScrollAwareScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public OnScrollListener getOnScrollListener() {
        return onScrollListener;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldX, int oldY) {
        super.onScrollChanged(x, y, oldX, oldY);
        if (onScrollListener != null) {
            onScrollListener.onScrollChanged(this, x, y, oldX, oldY);
        }
    }

    public interface OnScrollListener {
        public void onScrollChanged(ScrollView scrollView, int x, int y, int oldX, int oldY);
    }
}
