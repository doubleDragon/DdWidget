package com.wsl.library.widget.row;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by wsl on 17/4/15.
 */

public abstract class DdRowPhotoArrayAdapter<T> extends DdRowPhotoBaseAdapter {

    private final Object mLock = new Object();

    private List<T> mObjects;

    private boolean mNotifyOnChange = true;

    public DdRowPhotoArrayAdapter(List<T> objects) {
        this.mObjects = new ArrayList<>();
        if(objects != null && !objects.isEmpty()) {
            this.mObjects.addAll(objects);
        }
    }

    public void add(@Nullable T object) {
        synchronized (mLock) {
            mObjects.add(object);
        }
        if (mNotifyOnChange) notifyDataSetChanged();
    }

    public void addAll(@NonNull Collection<? extends T> collection) {
        synchronized (mLock) {
            mObjects.addAll(collection);
        }
        if (mNotifyOnChange) notifyDataSetChanged();
    }

    public void addAll(T... items) {
        synchronized (mLock) {
            Collections.addAll(mObjects, items);
        }
        if (mNotifyOnChange) notifyDataSetChanged();
    }

    public void insert(@Nullable T object, int index) {
        synchronized (mLock) {
            mObjects.add(index, object);
        }
        if (mNotifyOnChange) notifyDataSetChanged();
    }

    public void remove(@Nullable T object) {
        if(mObjects.isEmpty()) {
            return;
        }
        synchronized (mLock) {
            try {
                mObjects.remove(object);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (mNotifyOnChange) notifyDataSetChanged();
    }

    /**
     * Remove all elements from the list.
     */
    public void clear() {
        if(mObjects.isEmpty()) {
            return;
        }
        synchronized (mLock) {
            mObjects.clear();
        }
        if (mNotifyOnChange) notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mObjects != null ? mObjects.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return mObjects != null ? mObjects.get(position) : null;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        mNotifyOnChange = true;
    }

    public void setNotifyOnChange(boolean notifyOnChange) {
        mNotifyOnChange = notifyOnChange;
    }

    /**
     * @return 返回所有的数据
     */
    protected List<T> getAll() {
        return mObjects;
    }
}
