package com.autonavi.jacklee.ngandroid.subject;

import com.autonavi.jacklee.ngandroid.angular.observer.ViewObserver;

/**
 * Created by jacklee on 17/1/12.
 */

public interface ISubject {
    //注册观察者
    public void registe(ViewObserver observer);

    //移除观察者
    public void remove(ViewObserver observer);

    //移除所有的观察者
    public void removeAll();

    //进行事件更新
    public void notifyData(String tag, Object value);

}
