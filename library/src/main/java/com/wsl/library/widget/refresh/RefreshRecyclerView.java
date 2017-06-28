package com.wsl.library.widget.refresh;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by wsl on 17/6/28.
 */

public class RefreshRecyclerView extends RecyclerView{

    public interface Listener {
        void onActionSetAdapter(RecyclerView rv);
    }

    private Listener mListener;

    public void setListener(Listener mListener) {
        this.mListener = mListener;
    }

    public RefreshRecyclerView(Context context) {
        super(context);
    }

    public RefreshRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if(mListener != null) {
            mListener.onActionSetAdapter(this);
        }
    }
}
