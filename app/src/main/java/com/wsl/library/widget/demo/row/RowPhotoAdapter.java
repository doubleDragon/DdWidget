package com.wsl.library.widget.demo.row;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wsl.library.widget.demo.R;
import com.wsl.library.widget.row.DdRowPhotoArrayAdapter;

import java.util.List;

/**
 * 只有三张图的adapter
 * Created by wsl on 17/7/21.
 */

public class RowPhotoAdapter extends DdRowPhotoArrayAdapter<String> {

    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public RowPhotoAdapter(Context context, List<String> objects) {
        super(objects);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void update(List<String> objects) {
        clear();
        addAll(objects);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.item_row_photo, null);
        ImageView imageView = (ImageView) view;
        String url = getItem(position);
        Glide.with(mContext)
                .load(url)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(imageView);

        return view;
    }
}
