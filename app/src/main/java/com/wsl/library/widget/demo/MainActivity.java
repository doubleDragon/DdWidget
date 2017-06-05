package com.wsl.library.widget.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wsl.library.widget.demo.ninegrid.NinegridActivity;
import com.wsl.library.widget.demo.refresh.RefreshActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainAdapter.Listener{

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViews();
    }

    private void initViews() {
        attachToolbar("Widget Activity", false);

        List<MainItem> items = new ArrayList<>();
        items.add(new MainItem("Nine Grid"));
        items.add(new MainItem("Refresh layout"));

        MainAdapter adapter = new MainAdapter(this, items);
        adapter.setListener(this);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onClickItem(int position) {
        switch (position) {
            case 0:
                intentToActivity(NinegridActivity.class);
                break;
            case 1:
                intentToActivity(RefreshActivity.class);
                break;
            default:
                break;
        }
    }

    private void intentToActivity(Class<? extends Activity> activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }
}
