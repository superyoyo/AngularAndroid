package com.autonavi.jacklee.ngandroid.subject;

import android.view.View;

import com.autonavi.jacklee.ngandroid.angular.observer.ViewObserver;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jacklee on 17/1/12.
 */

public class EventSubject implements ISubject{
    private ArrayList<ViewObserver> observers;
    private HashMap<String, Object> data;//key:tag 代表View的tag value该view的属性值

    public EventSubject() {
        observers = new ArrayList<>();
        data = new HashMap<>();
    }

    @Override
    public void registe(ViewObserver observer) {
        for(ViewObserver iObserver : observers){
            if(observer.equals(iObserver)){
                //已经注册过，不再注册
                return;
            }
        }
        //还没有注册过，现在进行注册
        observers.add(observer);
    }

    @Override
    public void remove(ViewObserver observer) {
        for(ViewObserver iObserver : observers){
            if(observer.equals(iObserver)){
                //找到了已经注册过的观察者，将其移除
                observers.remove(iObserver);
                return;
            }
        }
    }

    @Override
    public void notifyData(String tag, Object value) {
        for(ViewObserver iObserver : observers){
            //获取对应的view
            View view = iObserver.getView();
            //对比view的tag是否和要改变的tag一致
            if(tag.equals(view.getTag())){
                //通知这个viewObserver去做出相应的改变
                iObserver.dataChange(this, value);
            }

        }
    }

}
