package com.autonavi.jacklee.ngandroid.angular.bean;

import com.autonavi.jacklee.ngandroid.subject.EventSubject;

import java.util.HashMap;

/**
 * Created by jacklee on 17/1/12.
 */

public class NgModel extends EventSubject{
    private String tag;
    private HashMap<String, Object> params;

    public NgModel(String tag) {
        this.tag = tag;
        params = new HashMap<>();
    }

    public String getTag() {
        return tag;
    }

    //为该对象，添加属性
    public NgModel addParams(String property, Object value){
        params.put(property, value);
        //调用该方法，通知所有与该属性关联的view更新UI
        notifyData(property, value);
        return this;
    }
}
