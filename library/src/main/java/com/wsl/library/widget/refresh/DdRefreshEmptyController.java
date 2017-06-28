package com.wsl.library.widget.refresh;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by wsl on 17/6/28.
 */

public class DdRefreshEmptyController implements RefreshRecyclerView.Listener{

    private View mEmptyView;

    public void setEmptyView(View emptyView) {
        this.mEmptyView = emptyView;
    }

    void handleContent(View contentView) {
        if(contentView instanceof RefreshRecyclerView && mEmptyView != null) {
            final RefreshRecyclerView recyclerView = (RefreshRecyclerView) contentView;
            recyclerView.setListener(this);
        }
    }

    @Override
    public void onActionSetAdapter(RecyclerView rv) {
        RecyclerView.Adapter adapter = rv.getAdapter();
        setEmptyVisibleIfNeeded(adapter.getItemCount());
        adapter.registerAdapterDataObserver(new RefreshAdapterDataObserver(rv));
    }

    private void setEmptyVisibleIfNeeded(int count) {
        if(mEmptyView == null) {
            return;
        }
        mEmptyView.setVisibility(count == 0 ? View.VISIBLE : View.GONE);
    }

    private class RefreshAdapterDataObserver extends RecyclerView.AdapterDataObserver {

        RecyclerView.Adapter adapter;

        public RefreshAdapterDataObserver(RecyclerView recyclerView) {
            adapter = recyclerView.getAdapter();
        }

        @Override
        public void onChanged() {
            super.onChanged();
            setEmptyVisibleIfNeeded(adapter.getItemCount());
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            super.onItemRangeChanged(positionStart, itemCount, payload);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            setEmptyVisibleIfNeeded(adapter.getItemCount());
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            setEmptyVisibleIfNeeded(adapter.getItemCount());
        }
    }
}
