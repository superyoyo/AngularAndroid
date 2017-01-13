package com.autonavi.jacklee.ngandroid.angular.observer.impl;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.autonavi.jacklee.ngandroid.angular.adapter.CommonAdapter;
import com.autonavi.jacklee.ngandroid.angular.observer.ViewObserver;
import com.autonavi.jacklee.ngandroid.subject.ISubject;

/**
 * Created by jacklee on 17/1/12.
 */

public class RecycleViewObserver extends ViewObserver {
    public RecycleViewObserver(View view) {
        super(view);
    }

    @Override
    public void dataChange(ISubject subject, Object object) {
        RecyclerView rv = (RecyclerView)getView();
        rv.getAdapter().notifyDataSetChanged();
    }
}
