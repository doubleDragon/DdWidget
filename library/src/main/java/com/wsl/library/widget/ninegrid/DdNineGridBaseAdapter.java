package com.wsl.library.widget.ninegrid;

import android.database.DataSetObservable;
import android.database.DataSetObserver;

/**
 * Created by wsl on 17/4/15.
 */

abstract class DdNineGridBaseAdapter implements DdNineGridAdapter{

    private DataSetObservable mDataSetObservable = new DataSetObservable();

    public void registerDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.registerObserver(observer);
    }

    public void unregisterDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.unregisterObserver(observer);
    }

    public void notifyDataSetChanged() {
        mDataSetObservable.notifyChanged();
    }

    @Override
    public boolean isEmpty() {
        return getCount() == 0;
    }
}
