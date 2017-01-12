package com.autonavi.jacklee.ngandroid.angular.observer.impl;

import android.view.View;
import android.widget.ImageView;

import com.autonavi.jacklee.ngandroid.angular.observer.ViewObserver;
import com.autonavi.jacklee.ngandroid.subject.ISubject;

/**
 * Created by jacklee on 17/1/12.
 */

public class ImageViewObserver extends ViewObserver {
    public ImageViewObserver(View view) {
        super(view);
    }

    @Override
    public void dataChange(ISubject subject, Object object) {
        ImageView iv = (ImageView)getView();
    }
}
