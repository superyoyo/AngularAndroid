package com.autonavi.jacklee.ngandroid.angular.observer.impl;

import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.autonavi.jacklee.ngandroid.angular.bean.NgModel;
import com.autonavi.jacklee.ngandroid.angular.observer.ViewObserver;
import com.autonavi.jacklee.ngandroid.subject.ISubject;

/**
 * Created by jacklee on 17/1/12.
 */

public class CheckBoxViewObserver extends ViewObserver {
    private boolean not_change;
    public CheckBoxViewObserver(View view, final NgModel ngModel) {
        super(view);
        CheckBox checkBox = (CheckBox)view;
        String tag = view.getTag().toString();
        //1.判断tag是否为空，如果不为空，看开头是否是"ng:"开头
        if (!TextUtils.isEmpty(tag) && tag.startsWith("ng:")) {
            //<1>.获取对象tag <2>.获取对象属性
            String[] tags = tag.split(":");
            //对象tag
            String model_tag = tags[1];
            //对象属性
            final String model_property = tags[2];
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    not_change = true;
                    if(ngModel.getValue(model_property) instanceof Boolean){
                        ngModel.addParams(model_property, isChecked);
                    }else{
                        ngModel.addParams(model_property, isChecked ? 0 : 1);
                    }
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
