package com.autonavi.jacklee.ngandroid.angular.observer.impl;

import android.view.View;
import android.widget.ProgressBar;

import com.autonavi.jacklee.ngandroid.angular.observer.ViewObserver;
import com.autonavi.jacklee.ngandroid.subject.ISubject;

/**
 * Created by jacklee on 17/1/22.
 */

public class ProgressBarViewObserver extends ViewObserver {
    public ProgressBarViewObserver(View view) {
        super(view);
    }

    @Override
    public void dataChange(ISubject subject, Object object) {
        ProgressBar pb = (ProgressBar)getView();
        if(object instanceof Integer){
            pb.setProgress((Integer)object);
        }
    }
}
