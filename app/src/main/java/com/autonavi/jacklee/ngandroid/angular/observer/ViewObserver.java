package com.autonavi.jacklee.ngandroid.angular.observer;

import android.view.View;

import com.autonavi.jacklee.ngandroid.subject.IObserver;
import com.autonavi.jacklee.ngandroid.subject.ISubject;

/**
 * Created by jacklee on 17/1/12.
 */

public abstract class ViewObserver implements IObserver{
    private View view;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public ViewObserver(View view) {
        this.view = view;
    }

    @Override
    public abstract void dataChange(ISubject subject, Object object) ;
}
