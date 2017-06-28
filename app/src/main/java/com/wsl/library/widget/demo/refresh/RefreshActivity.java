package com.wsl.library.widget.demo.refresh;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wsl.library.widget.demo.BaseActivity;
import com.wsl.library.widget.demo.R;
import com.wsl.library.widget.refresh.DdLoadListener;
import com.wsl.library.widget.refresh.DdRefreshLayout;
import com.wsl.library.widget.refresh.DdRefreshListener;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 下拉刷新和下拉加载更多页面
 * Created by wsl on 17/6/5.
 */
public class RefreshActivity extends BaseActivity{

    @BindView(R.id.dd_refresh_content)
    RecyclerView rvContent;

    @BindView(R.id.refresh_layout)
    DdRefreshLayout refreshLayout;

    private RefreshAdapter mAdapter;

    private RefreshHandler mHandler = new RefreshHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);

        ButterKnife.bind(this);

        initViews();
    }

    private void initViews() {
        attachToolbar("Refresh");


        rvContent.setHasFixedSize(true);
        rvContent.setLayoutManager(new LinearLayoutManager(this));
        rvContent.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new RefreshAdapter(0);
        rvContent.setAdapter(mAdapter);


        refreshLayout.setRefreshEnabled(true);
        refreshLayout.setLoadEnabled(true);

        refreshLayout.setRefreshListener(new DdRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.sendEmptyMessageDelayed(0, 2000);
            }
        });

        refreshLayout.setLoadListener(new DdLoadListener() {
            @Override
            public void onLoadMore() {
                mHandler.sendEmptyMessageDelayed(1, 2000);
            }
        });


        View empty = getLayoutInflater().inflate(R.layout.layout_refresh_empty_new, null);
        refreshLayout.setEmptyView(empty);
//        refreshLayout.setEmptyView(R.layout.layout_refresh_empty_new);
    }

    public void fakeRefreshResult() {
        mAdapter.add("pull item ");
        refreshLayout.setRefresh(false);
    }

    public void fakeLoadResult() {
        mAdapter.add("load item ");
        refreshLayout.setLoad(false);
    }

    private static class RefreshHandler extends Handler {

        private WeakReference<RefreshActivity> reference;


        RefreshHandler(RefreshActivity activity) {
            this.reference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            RefreshActivity refreshActivity = reference.get();
            if(refreshActivity == null) {
                return;
            }

            switch (msg.what) {
                case 0:
                    refreshActivity.fakeRefreshResult();
                    break;
                case 1:
                    refreshActivity.fakeLoadResult();
                    break;
            }
        }
    }

}