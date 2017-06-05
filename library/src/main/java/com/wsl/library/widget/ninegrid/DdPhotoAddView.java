package com.wsl.library.widget.ninegrid;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.wsl.library.widget.R;

/**
 * Created by wsl on 17/4/17.
 */

public class DdPhotoAddView extends View {

    private Paint mOutPrint;
    private Paint mInPrint;

    private int mInWidth;
    private int mInHeight;

    public DdPhotoAddView(Context context) {
        this(context, null);
    }

    public DdPhotoAddView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DdPhotoAddView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DdPhotoAddView);
        mInWidth = a.getDimensionPixelOffset(R.styleable.DdPhotoAddView_dd_photo_in_width, dpToPx(3));
        mInHeight = a.getDimensionPixelOffset(R.styleable.DdPhotoAddView_dd_photo_in_height, dpToPx(40));
        int inColor = a.getColor(R.styleable.DdPhotoAddView_dd_photo_in_color, 0xffd8d8d8);

        int outStrokeWidth = a.getDimensionPixelOffset(R.styleable.DdPhotoAddView_dd_photo_out_stroke, dpToPx(1));
        int outColor = a.getColor(R.styleable.DdPhotoAddView_dd_photo_out_color, 0xffcccccc);

        a.recycle();

        mOutPrint = new Paint();
        mOutPrint.setDither(true);
        mOutPrint.setAntiAlias(true);
        mOutPrint.setStyle(Paint.Style.STROKE);
        mOutPrint.setStrokeWidth(outStrokeWidth);
        mOutPrint.setColor(outColor);

        mInPrint = new Paint();
        mInPrint.setDither(true);
        mInPrint.setAntiAlias(true);
        mInPrint.setStyle(Paint.Style.FILL);
        mInPrint.setColor(inColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();

        //画内部的2个矩形
        drawInside(canvas);

        //画外部的边框
        canvas.drawRect(0, 0, width, height, mOutPrint);
    }

    private void drawInside(Canvas canvas) {
        int l = (getWidth() - mInWidth) / 2;
        int t = (getHeight() - mInHeight) / 2;
        canvas.drawRect(l, t, l + mInWidth, t + mInHeight, mInPrint);

        l = (getWidth() - mInHeight) / 2;
        t = (getHeight() - mInWidth) / 2;
        canvas.drawRect(l, t, l + mInHeight, t + mInWidth, mInPrint);
    }

    public int dpToPx(int dp) {
        Resources r = getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }
}
