package com.autonavi.jacklee.ngandroid.angular.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.autonavi.jacklee.ngandroid.R;

/**
 * Created by jacklee on 17/1/20.
 */

public class NgItemView extends View {
    private int laytoutId;
    public NgItemView(Context context) {
        super(context);
    }

    public NgItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public NgItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs){
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.ng);
        laytoutId = array.getResourceId(R.styleable.ng_ngRecyclerViewItemList, -1);
    }

    public int getLaytoutId() {
        return laytoutId;
    }
}
