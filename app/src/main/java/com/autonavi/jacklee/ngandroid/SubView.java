package com.autonavi.jacklee.ngandroid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jacklee on 17/1/20.
 */

public class SubView extends View {
    private AttributeSet attrs;
    private int defStyleAttr;

    public SubView(Context context) {
        super(context);
    }

    public SubView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attrs = attrs;
    }

    public SubView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.attrs = attrs;
        this.defStyleAttr = defStyleAttr;
    }
}
