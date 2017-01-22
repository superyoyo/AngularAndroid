package com.autonavi.jacklee.ngandroid.angular.observer.impl;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.autonavi.jacklee.ngandroid.angular.bean.NgModel;
import com.autonavi.jacklee.ngandroid.angular.observer.ViewObserver;
import com.autonavi.jacklee.ngandroid.subject.ISubject;

/**
 * Created by jacklee on 17/1/12.
 */

public class EditViewObserver extends ViewObserver {
    private boolean not_change;

    public EditViewObserver(View view, final NgModel ngModel) {
        super(view);
        EditText editText = (EditText)view;
        String tag = view.getTag().toString();
        //1.判断tag是否为空，如果不为空，看开头是否是"ng:"开头
        if (!TextUtils.isEmpty(tag) && tag.startsWith("ng:")) {
            //<1>.获取对象tag <2>.获取对象属性
            String[] tags = tag.split(":");
            //对象tag
            String model_tag = tags[1];
            //对象属性
            final String model_property = tags[2];
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    not_change = true;
                    ngModel.addParams(model_property, s.toString());
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
        EditText et = (EditText)getView();
        et.setText(object.toString());
    }
}
