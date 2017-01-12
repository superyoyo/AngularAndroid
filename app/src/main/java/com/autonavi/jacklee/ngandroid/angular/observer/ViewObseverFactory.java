package com.autonavi.jacklee.ngandroid.angular.observer;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.autonavi.jacklee.ngandroid.angular.observer.impl.EditViewObserver;
import com.autonavi.jacklee.ngandroid.angular.observer.impl.ImageViewObserver;
import com.autonavi.jacklee.ngandroid.angular.observer.impl.TextViewObserver;

/**
 * Created by jacklee on 17/1/12.
 */

public class ViewObseverFactory {
    public static ViewObserver createViewObserver(View view){
        ViewObserver viewObserver = null;

        if(view instanceof TextView){
            //如果是TexView
            viewObserver = new TextViewObserver(view);
        }else if(view instanceof EditText){
            //如果是输入框
            viewObserver = new EditViewObserver(view);
        }else if(view instanceof ImageView){
            //如果是图片
            viewObserver = new ImageViewObserver(view);
        }else if(view instanceof CheckBox){

        }
        return viewObserver;
    }
}
