package com.autonavi.jacklee.ngandroid.angular.observer;

import android.view.View;

import com.autonavi.jacklee.ngandroid.subject.IObserver;
import com.autonavi.jacklee.ngandroid.subject.ISubject;

/**
 * Created by jacklee on 17/1/12.
 *
 * 该类主要是对ngModle的改变，做出对应的UI操作，
 * 写为抽象类，是为了不同的UI组件再相应同一个属性值时，表现不同。
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
