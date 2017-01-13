package com.autonavi.jacklee.ngandroid.angular.bean;

import com.autonavi.jacklee.ngandroid.subject.EventSubject;

import java.util.HashMap;
import java.util.List;

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
        if(value instanceof List){
            if(params.get(property) != null && params.get(property) instanceof List){
                List list = (List) params.get(property);
                list.addAll((List) value);
                params.put(property, list);
            }else{
                params.put(property, value);
            }
        }else{
            params.put(property, value);
        }

        //调用该方法，通知所有与该属性关联的view更新UI
        notifyData(property, value);
        return this;
    }

    //获取对应属性值
    public Object getValue(String property){
        if(params.containsKey(property)){
            return params.get(property);
        }
        return null;
    }

    public HashMap<String, Object> getParams(){
        return params;
    }
}
