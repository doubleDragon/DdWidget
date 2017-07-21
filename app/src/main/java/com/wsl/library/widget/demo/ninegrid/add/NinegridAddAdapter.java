package com.wsl.library.widget.demo.ninegrid.add;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wsl.library.widget.demo.R;
import com.wsl.library.widget.ninegrid.DdNineGridAppendAdapter;

import java.util.List;
import java.util.Random;

/**
 * NinegridAddActivity adapter
 * Created by wsl on 17/4/15.
 */

class NinegridAddAdapter extends DdNineGridAppendAdapter<NinegridAddBean> {

    private LayoutInflater mLayoutInflater;
    private Context mContext;

    NinegridAddAdapter(Context context, List<NinegridAddBean> objects) {
        this(context, objects, 9);
    }

    NinegridAddAdapter(Context context, List<NinegridAddBean> objects, int max) {
        super(objects, max);
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    protected View getNormalView(int position) {
        View view = mLayoutInflater.inflate(R.layout.item_ninegrid_normal, null);
        ImageView imageView = (ImageView) view;
        NinegridAddBean bean = getItem(position);
        Glide
                .with(mContext)
                .load(bean.url)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(imageView);

        return view;
    }

    @Override
    protected View getAddView() {
        return mLayoutInflater.inflate(R.layout.item_ninegrid_add, null);
    }

    private int getNormalColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
