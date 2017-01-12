package com.autonavi.jacklee.ngandroid.angular.observer.impl;

import android.view.View;
import android.widget.EditText;

import com.autonavi.jacklee.ngandroid.angular.observer.ViewObserver;
import com.autonavi.jacklee.ngandroid.subject.ISubject;

/**
 * Created by jacklee on 17/1/12.
 */

public class EditViewObserver extends ViewObserver {
    public EditViewObserver(View view) {
        super(view);
    }

    @Override
    public void dataChange(ISubject subject, Object object) {
        EditText et = (EditText)getView();
        et.setText(object.toString());
    }
}
