package com.example.administrator.customview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;

import com.example.administrator.customview.R;

/**
 * 自定义View
 * @author dengjiali
 */
public class CustomView extends View
{
    /**
     * 默认大小
     */
    private int defaultSize;

    private Paint mPaint;

    public CustomView(Context context)
    {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView);
        defaultSize = typedArray.getDimensionPixelSize(R.styleable.CustomView_default_size, 100);
        int circleColor = typedArray.getColor(R.styleable.CustomView_circle_color, ContextCompat.getColor(context, R.color.colorAccent));
        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setColor(circleColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(widthMeasureSpec);
        int height = getMySize(heightMeasureSpec);
        if (width > height)
        {
            width = height;
        }
        else
        {
            height = width;
        }
        setMeasuredDimension(width, height);
    }

    private int getMySize(int widthMeasureSpec)
    {
        int size = defaultSize;
        switch (MeasureSpec.getMode(widthMeasureSpec))
        {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
            {
                size = MeasureSpec.getSize(widthMeasureSpec);
                break;
            }
            default:
                break;
        }
        return size;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        int width = getMeasuredWidth();
        canvas.drawCircle(width / 2, width / 2, width / 2, mPaint);
    }
}
