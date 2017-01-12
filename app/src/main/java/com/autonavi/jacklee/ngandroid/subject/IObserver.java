package com.autonavi.jacklee.ngandroid.subject;

/**
 * Created by jacklee on 17/1/12.
 */

public interface IObserver {

    //订阅的主题有更新，则调用该方法
    public void dataChange(ISubject subject, Object object);
}
