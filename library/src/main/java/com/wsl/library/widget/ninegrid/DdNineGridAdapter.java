package com.wsl.library.widget.ninegrid;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wsl on 17/4/14.
 */

interface DdNineGridAdapter{

    int getCount();
    boolean isEmpty();

    View getView(int position, ViewGroup parent);

    Object getItem(int position);
}