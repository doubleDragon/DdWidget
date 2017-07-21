package com.wsl.library.widget.demo.row;

import android.os.Bundle;
import android.view.View;

import com.wsl.library.widget.demo.BaseActivity;
import com.wsl.library.widget.demo.R;
import com.wsl.library.widget.row.DdRowPhotoLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 只有三张图的九宫格
 * Created by wsl on 17/7/21.
 */

public class RowPhotoActivity extends BaseActivity{

    private List<String> URLS = new ArrayList<>();

    private RowPhotoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_row_photo);


        attachToolbar("Nine Grid three photo");

        initImage();
        initViews();
    }

    private void initViews() {
        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.update(getData());
            }
        });

        adapter = new RowPhotoAdapter(this, getData());

        DdRowPhotoLayout rowPhotoLayout = (DdRowPhotoLayout) findViewById(R.id.nineGridLayout);
        rowPhotoLayout.setAdapter(adapter);
    }

    private List<String> getData() {
        List<String> data = new ArrayList<>();
        data.add(generateImage());
        data.add(generateImage());
        data.add(generateImage());

        return data;
    }

    private String generateImage() {
        int size = URLS.size();
        Random random = new Random();
        int index = random.nextInt(size);
        return URLS.get(index);
    }


    private void initImage() {
        URLS.add("http://img1.imgtn.bdimg.com/it/u=3796927281,2197235180&fm=26&gp=0.jpg");
        URLS.add("http://img3.imgtn.bdimg.com/it/u=125141099,1324702918&fm=26&gp=0.jpg");
        URLS.add("http://img0.imgtn.bdimg.com/it/u=2965529102,1738576226&fm=26&gp=0.jpg");
        URLS.add("http://img5.imgtn.bdimg.com/it/u=3488044699,4020928868&fm=26&gp=0.jpg");
        URLS.add("http://img5.imgtn.bdimg.com/it/u=1663564596,3632836677&fm=26&gp=0.jpg");
        URLS.add("http://img1.imgtn.bdimg.com/it/u=2044029189,4223253902&fm=26&gp=0.jpg");
        URLS.add("http://img1.imgtn.bdimg.com/it/u=1348878974,671305133&fm=26&gp=0.jpg");
    }


}
