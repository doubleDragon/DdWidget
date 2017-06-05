package com.wsl.library.widget.ninegrid;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.wsl.library.widget.R;

/**
 * Created by wsl on 17/4/14.
 */

public class DdNineGridLayout extends ViewGroup {

    private static final String TAG = DdNineGridLayout.class.getSimpleName();

    private static final int DEFAULT_CHILD_WIDTH = 140;
    private static final int DEFAULT_CHILD_COLUMNS = 3;
    private static final int CHILD_MODE_DIVIDE_EQUALLY = 0;
    private static final int CHILD_MODE_DIVIDE_GAP = 1;

    private boolean mDebug;

    private int mRow;//行
    private int mColumns;//列

    private int mChildWidth;
    private int mChildGap;//间距, 上下左右相等

    private boolean mShowAdd;

    private int mMode;

    public DdNineGridLayout(Context context) {
        this(context, null);
    }

    public DdNineGridLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DdNineGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DdNineGridLayout);
        mChildWidth = a.getDimensionPixelOffset(R.styleable.DdNineGridLayout_dd_child_width, 0);
        mColumns = a.getInt(R.styleable.DdNineGridLayout_dd_child_columns, DEFAULT_CHILD_COLUMNS);
        mMode = a.getInt(R.styleable.DdNineGridLayout_dd_child_mode, CHILD_MODE_DIVIDE_EQUALLY);
        if(mMode == CHILD_MODE_DIVIDE_GAP) {
            //GAP模式才去取值
            mChildGap = a.getDimensionPixelOffset(R.styleable.DdNineGridLayout_dd_child_gap, 0);
        }
        a.recycle();

        if(mChildWidth == 0) {
            throw new RuntimeException("must assign child width");
        }
    }

    public void setDebug(boolean debug) {
        this.mDebug = debug;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = widthSize;
        int height = 0;

        logD("spec width: " + widthSize + ",spec height: " + heightSize);

        int count = getChildCount();
        if (count > 0) {
            for(int i=0; i<count; i++) {
                View child = getChildAt(i);
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
                //每个child大小一样, 不知道为啥实际情况是不一样，需要验证
//                final int measuredChildWidth = child.getMeasuredWidth();
//                logD("i=" + i + "[measuredChildWidth: " + measuredChildWidth + ",mChildWidth: " + mChildWidth + "]");
//                mChildWidth = measuredChildWidth;
            }
            if(mMode == CHILD_MODE_DIVIDE_EQUALLY) {
                //平分模式下计算gap值
                mChildGap = (widthSize - mColumns * mChildWidth) / (mColumns - 1);
            }
            logD("mChildWidth: " + mChildWidth + ",mChildGap: " + mChildGap);

            int rows = mRow = getRows(count);
            height = mChildWidth * rows + mChildGap * (rows - 1);
        }

        logD("width: " + width + ",height: " + height);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int column = getColumn(i);//第几列
            int row = getRow(i);//第几行
            int left = mChildWidth * column + mChildGap * column;
            int top = mChildWidth * row + mChildGap * row;
            int right = left + mChildWidth;
            int bottom = top + mChildWidth;
            logD("i=" + i + "[" + left + "," + top + "," + right + "," + bottom + "]");
            child.layout(left, top, right, bottom);
        }
    }

    private int getRows(int count) {
        if(mColumns == 3){
            if (count == 0) {
                return 0;
            } else if (count > 0 && count <= 3) {
                return 1;
            } else if (count > 3 && count <= 6) {
                return 2;
            } else {
                return 3;
            }
        } else if(mColumns == 4){
            if (count == 0) {
                return 0;
            } else if (count > 0 && count <= 4) {
                return 1;
            } else if (count > 4 && count <= 8) {
                return 2;
            } else {
                return 3;
            }
        } else {
            throw new RuntimeException("only support 3 or 4 columns");
        }
    }


    /**
     * 第几行
     *
     * @param index 位置
     * @return 第几行
     */
    private int getRow(int index) {
        if(mColumns == 3) {
            switch (index) {
                default:
                case 0:
                case 1:
                case 2:
                    return 0;
                case 3:
                case 4:
                case 5:
                    return 1;
                case 6:
                case 7:
                case 8:
                    return 2;
            }
        } else if(mColumns == 4) {
            switch (index) {
                default:
                case 0:
                case 1:
                case 2:
                case 3:
                    return 0;
                case 4:
                case 5:
                case 6:
                case 7:
                    return 1;
                case 8:
                    return 2;
            }
        } else {
            throw new RuntimeException("only support 3 or 4 columns");
        }

    }

    /**
     * 第几列
     *
     * @param index 位置
     * @return 第几列
     */
    private int getColumn(int index) {
        return index % mColumns;
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
