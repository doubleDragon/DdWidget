package com.wsl.library.widget.row;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wsl on 17/7/21.
 */

interface DdRowPhotoAdapter {

    int getCount();
    boolean isEmpty();

    View getView(int position, ViewGroup parent);

    Object getItem(int position);
}
