package com.autonavi.jacklee.ngandroid.angular.bean;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.autonavi.jacklee.ngandroid.angular.observer.ViewObserver;
import com.autonavi.jacklee.ngandroid.angular.observer.ViewObseverFactory;
import com.autonavi.jacklee.ngandroid.subject.EventSubject;

import java.util.HashMap;

/**
 * Created by jacklee on 17/1/12.
 */

public class NgGo {
    //每个主题都有其订阅者，即ngModel
    private HashMap<String, EventSubject> subjects;
    //当前nggo作用的域
    private ViewGroup parent;

    public NgGo(ViewGroup parent) {
        this.parent = parent;
        subjects = new HashMap<>();
    }

    //添加ngModel
    public void addNgModel(NgModel ngModel) {
        subjects.put(ngModel.getTag(), ngModel);
    }

    //遍历这个域下的viewgroup的所有子节点，找出托管给nggo的view集合，并和对应的ngmodel关联起来
    private void selectViewTag(ViewGroup viewGroup) {
        for (int i = 0, n = viewGroup.getChildCount(); i < n; i++) {
            View item = viewGroup.getChildAt(i);
            if (item instanceof ViewGroup) {
                selectViewTag((ViewGroup) item);
            } else {
                //1.获取view 的tag值，用来找出对应那个model的那个属性
                String tag = item.getTag().toString();
                //2.判断tag是否为空，如果不为空，看开头是否是"ng:"开头
                if (!TextUtils.isEmpty(tag) && tag.startsWith("ng:")) {
                    //3.可以判断出该view是交由NgGo去控制的－>1.获取对象tag 2.获取对象属性
                    String[] tags = tag.split(":");
                    //对象tag
                    String model_tag = tags[1];
                    //对象属性
                    String model_property = tags[2];

                    //获取该view对应的NgModel
                    EventSubject subject = subjects.get(model_tag);

                    //将该view和该对象关联起来 (根据view类型，创建不同的ViewObserver)
                    ViewObserver viewObserver = ViewObseverFactory.createViewObserver(item);
                    subject.registe(viewObserver);
                }
            }
        }
    }

    public void start(){
        selectViewTag(parent);
    }
}
