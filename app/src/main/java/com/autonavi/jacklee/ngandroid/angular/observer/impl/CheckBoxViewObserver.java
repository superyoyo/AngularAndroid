package com.autonavi.jacklee.ngandroid.angular.observer.impl;

import android.view.View;
import android.widget.CheckBox;

import com.autonavi.jacklee.ngandroid.angular.observer.ViewObserver;
import com.autonavi.jacklee.ngandroid.subject.ISubject;

/**
 * Created by jacklee on 17/1/12.
 */

public class CheckBoxViewObserver extends ViewObserver {
    public CheckBoxViewObserver(View view) {
        super(view);
    }

    @Override
    public void dataChange(ISubject subject, Object object) {
        CheckBox cb = (CheckBox)getView();
        if(object instanceof Boolean){
            boolean flag = (Boolean)object;
            cb.setChecked(flag);
        }else if(object instanceof Integer){
            int flag = (int)object;
            cb.setChecked(flag == 0);
        }
    }
}
