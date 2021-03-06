package com.autonavi.jacklee.ngandroid.angular.observer.impl;

import android.view.View;
import android.widget.TextView;

import com.autonavi.jacklee.ngandroid.angular.observer.ViewObserver;
import com.autonavi.jacklee.ngandroid.subject.ISubject;

/**
 * Created by jacklee on 17/1/12.
 */

public class TextViewObserver extends ViewObserver {


    public TextViewObserver(View view) {
        super(view);
    }

    @Override
    public void dataChange(ISubject subject, Object object) {
        TextView tv = (TextView)getView();
        tv.setText(object.toString());
    }
}
