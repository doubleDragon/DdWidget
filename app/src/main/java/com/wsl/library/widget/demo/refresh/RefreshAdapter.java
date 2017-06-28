package com.wsl.library.widget.demo.refresh;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wsl.library.widget.demo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wsl on 16-8-23.
 */

public class RefreshAdapter extends RecyclerView.Adapter{

    private List<String> data;

    public RefreshAdapter(int count) {
        super();
        data = new ArrayList<>();
        for(int i=0; i< count; i++) {
            data.add("item " + i);
        }
    }

    public void add(String item) {
        int position = this.data.size();
        this.data.add(item);
        this.notifyItemInserted(position);
    }

    public void addAll(List<String> list) {
        if(list == null || list.isEmpty()) {
            return;
        }
        int start = this.data.size();
        this.data.addAll(list);
        this.notifyItemRangeInserted(start, list.size());
    }

    public void clear() {
        if(data == null || data.isEmpty()) {
            return;
        }
        int itemCount = getItemCount();
        data.clear();
        this.notifyItemRangeRemoved(0, itemCount);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_refresh, parent, false);
        return new VHItem(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String text = getItem(position);
        VHItem vhItem = (VHItem) holder;
        vhItem.textView.setText(text);
    }

    private String getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class VHItem extends RecyclerView.ViewHolder {

        @BindView(R.id.text)
        TextView textView;

        public VHItem(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
