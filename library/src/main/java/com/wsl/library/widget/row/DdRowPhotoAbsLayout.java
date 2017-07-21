package com.wsl.library.widget.row;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.wsl.library.widget.R;

/**
 * 单行photo展示layout, 可能展示3可能展示4
 * Created by wsl on 17/4/14.
 */

class DdRowPhotoAbsLayout extends ViewGroup {

    private static final String TAG = DdRowPhotoAbsLayout.class.getSimpleName();

    private static final int DEFAULT_CHILD_COLUMNS = 3;

    private boolean mDebug = true;

    private int mColumns;//列

    private int mChildWidth;
    private int mChildGap;//item之间的间距

    public DdRowPhotoAbsLayout(Context context) {
        this(context, null);
    }

    public DdRowPhotoAbsLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DdRowPhotoAbsLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DdNineGridLayout);
        mChildWidth = a.getDimensionPixelOffset(R.styleable.DdNineGridLayout_dd_child_width, 0);
        mColumns = a.getInt(R.styleable.DdNineGridLayout_dd_child_columns, DEFAULT_CHILD_COLUMNS);
        a.recycle();

        if(mChildWidth <= 0) {
            throw new RuntimeException("must assign child width");
        }
    }


    public void setDebug(boolean debug) {
        this.mDebug = debug;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int height = 0;

        int count = getChildCount();
        if(count > mColumns) {
            throw new RuntimeException("child 个数超过限制");
        }
        if (count > 0) {
            for(int i=0; i<count; i++) {
                View child = getChildAt(i);
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
            }
            height = mChildWidth;

            mChildGap = (widthSize - mColumns * mChildWidth) / (mColumns - 1);
            if(mChildGap < 0) {
                throw new RuntimeException("child宽度太大");
            }
        }

        setMeasuredDimension(widthSize, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int top = 0;
        int bottom = getHeight();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int left = mChildWidth * i + mChildGap * i;
            int right = left + mChildWidth;
            child.layout(left, top, right, bottom);
        }
    }

    int getChildWidth() {
        return mChildWidth;
    }

    private void logD(String text) {
        if(mDebug) {
            Log.d(TAG, text);
        }
    }
}
