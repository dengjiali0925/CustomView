package com.example.administrator.customview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * 自定义ViewGroup，类似LinearLayout
 * @author dengjiali
 */
public class CustomViewGroup extends ViewGroup
{
    public CustomViewGroup(Context context)
    {
        super(context);
    }

    public CustomViewGroup(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 测量子View
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int width = getMySize(true, widthMeasureSpec);
        int height = getMySize(false, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int getMySize(boolean isWidth, int measureSpec)
    {
        int size = 0;
        switch (MeasureSpec.getMode(measureSpec))
        {
            case MeasureSpec.AT_MOST:
            {
                size = isWidth ? getMaxChildWidth() : getTotalChildHeight();
                break;
            }
            case MeasureSpec.EXACTLY:
            {
                size = MeasureSpec.getSize(measureSpec);
                break;
            }
            default:
                break;
        }
        return size;
    }

    private int getTotalChildHeight()
    {
        int totalHeight = 0;
        int childCount = getChildCount();
        if (childCount == 0)
        {
            return totalHeight;
        }
        for (int i = 0; i < childCount; i++)
        {
            View childView = getChildAt(i);
            int childHeight = childView.getMeasuredHeight();
            totalHeight += childHeight;
        }
        return totalHeight;
    }

    private int getMaxChildWidth()
    {
        int width = 0;
        int childCount = getChildCount();
        if (childCount == 0)
        {
            return width;
        }
        for (int i = 0; i < childCount; i++)
        {
            View childView = getChildAt(i);
            int measuredWidth = childView.getMeasuredWidth();
            if (i == 0)
            {
                width = measuredWidth;
            }
            if (measuredWidth > width)
            {
                width = measuredWidth;
            }
        }
        return width;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        int top = t;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            View view = getChildAt(i);
            view.layout(l, top, r, top + view.getMeasuredHeight());
            top += view.getMeasuredHeight();
        }
    }
}
