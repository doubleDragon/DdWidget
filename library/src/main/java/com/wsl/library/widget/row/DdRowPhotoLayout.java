package com.wsl.library.widget.row;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wsl on 17/4/14.
 */

public class DdRowPhotoLayout extends DdRowPhotoAbsLayout {

    private DdRowPhotoBaseAdapter mAdapter;
    private AdapterDataSetObserver mDataSetObserver;

    public DdRowPhotoLayout(Context context) {
        super(context);
    }

    public DdRowPhotoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DdRowPhotoLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mAdapter != null && mDataSetObserver == null) {
            mDataSetObserver = new AdapterDataSetObserver();
            mAdapter.registerDataSetObserver(mDataSetObserver);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mAdapter != null && mDataSetObserver != null) {
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
            mDataSetObserver = null;
        }
    }

    public void setAdapter(DdRowPhotoBaseAdapter adapter) {
        if (mAdapter != null && mDataSetObserver != null) {
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
        }
        this.mAdapter = adapter;
        removeAllViews();
        if(mAdapter != null) {
            mDataSetObserver = new AdapterDataSetObserver();
            mAdapter.registerDataSetObserver(mDataSetObserver);
            int count = mAdapter.getCount();
            for(int i=0; i<count; i++) {
                View view = mAdapter.getView(i, this);
                addView(view, i, new LayoutParams(getChildWidth(), getChildWidth()));
            }
        }
    }

    private class AdapterDataSetObserver extends DataSetObserver {

        @Override
        public void onChanged() {
            //数据变化了，
            removeAllViews();
            if(mAdapter != null) {
                if(mDataSetObserver != null) {
                    mAdapter.unregisterDataSetObserver(mDataSetObserver);
                }
                mDataSetObserver = new AdapterDataSetObserver();
                mAdapter.registerDataSetObserver(mDataSetObserver);
                int count = mAdapter.getCount();
                for(int i=0; i<count; i++) {
                    View view = mAdapter.getView(i, DdRowPhotoLayout.this);
                    addView(view, i);
                }
            }
            requestLayout();
        }
    }
}
