package com.autonavi.jacklee.ngandroid.angular.observer.impl;

import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.autonavi.jacklee.ngandroid.angular.bean.NgModel;
import com.autonavi.jacklee.ngandroid.angular.observer.ViewObserver;
import com.autonavi.jacklee.ngandroid.subject.ISubject;

/**
 * Created by jacklee on 17/1/22.
 */

public class SeekBarViewObserver extends ViewObserver {
    private boolean not_change;
    public SeekBarViewObserver(View view, final NgModel ngModel) {
        super(view);
        SeekBar sb = (SeekBar)view;
        String tag = view.getTag().toString();
        //1.判断tag是否为空，如果不为空，看开头是否是"ng:"开头
        if (!TextUtils.isEmpty(tag) && tag.startsWith("ng:")) {
            //<1>.获取对象tag <2>.获取对象属性
            String[] tags = tag.split(":");
            //对象tag
            String model_tag = tags[1];
            //对象属性
            final String model_property = tags[2];
            sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    not_change = true;
                    if(ngModel.getValue(model_property) instanceof Integer){
                        ngModel.addParams(model_property, progress);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }
    }

    @Override
    public void dataChange(ISubject subject, Object object) {
        if(not_change){
            not_change = false;
            return;
        }
        SeekBar sb = (SeekBar)getView();
        if(object instanceof Integer){
            sb.setProgress((Integer)object);
        }
    }
}
