package com.wsl.library.widget.ninegrid;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collection;
import java.util.List;

/**
 * Created by wsl on 17/4/15.
 */

public abstract class DdNineGridAppendAdapter<T> extends DdNineGridArrayAdapter<T> {

    private static final int TYPE_NORMAL_ITEM = 0;
    private static final int TYPE_ADD_ITEM = 1;

    private int mMax;

    public DdNineGridAppendAdapter(List<T> objects, int max) {
        super(objects);
        this.mMax = max;
    }

    public void remove(int position) {
        if(isEmpty()) {
            return;
        }
        int type = getItemViewType(position);
        if(type == TYPE_ADD_ITEM) {
            position--;
        }
        T t = getItem(position);
        remove(t);
    }

    @Override
    public void add(@Nullable T object) {
        if(mMax == super.getCount()) {
            return;
        }
        super.add(object);
    }

    @Override
    public void addAll(@NonNull Collection<? extends T> collection) {
        if(mMax == super.getCount()) {
            return;
        }
        super.addAll(collection);
    }

    @Override
    public void addAll(T... items) {
        if(mMax == super.getCount()) {
            return;
        }
        super.addAll(items);
    }

    @Override
    public boolean isEmpty() {
        return super.getCount() == 0;
    }

    @Override
    public final int getCount() {
        int count = super.getCount();
        if (mMax > count) {
            return count + 1;
        }
        return count;
    }

    @Override
    public final View getView(int position, ViewGroup parent) {
        int type = getItemViewType(position);
        if(type == TYPE_ADD_ITEM) {
            return getAddView();
        } else {
            return getNormalView(position);
        }
    }

    private int getItemViewType(int position) {
        int objectCount = super.getCount();
        if (objectCount == mMax) {
            return TYPE_NORMAL_ITEM;
        }
        return position == objectCount ? TYPE_ADD_ITEM : TYPE_NORMAL_ITEM;
    }

    protected abstract View getNormalView(int position);
    protected abstract View getAddView();
}