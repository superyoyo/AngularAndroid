package com.autonavi.jacklee.ngandroid.angular.observer;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.autonavi.jacklee.ngandroid.angular.observer.impl.CheckBoxViewObserver;
import com.autonavi.jacklee.ngandroid.angular.observer.impl.EditViewObserver;
import com.autonavi.jacklee.ngandroid.angular.observer.impl.ImageViewObserver;
import com.autonavi.jacklee.ngandroid.angular.observer.impl.RecycleViewObserver;
import com.autonavi.jacklee.ngandroid.angular.observer.impl.TextViewObserver;

/**
 * Created by jacklee on 17/1/12.
 */

public class ViewObseverFactory {
    public static ViewObserver createViewObserver(View view){
        ViewObserver viewObserver = null;
        String className = view.getClass().getName();
        Log.d("liuji", "className:" + className + " CheckBox:" + CheckBox.class.getName());
        if(className.equals(TextView.class.getName())){
            //如果是TexView
            viewObserver = new TextViewObserver(view);
        }else if(className.equals(EditText.class.getName())){
            //如果是输入框
            viewObserver = new EditViewObserver(view);
        }else if(className.equals(ImageView.class.getName())){
            //如果是图片
            viewObserver = new ImageViewObserver(view);
        }else if(className.equals(CheckBox.class.getName())){
            //如果是CheckBox
            viewObserver = new CheckBoxViewObserver(view);
        }else if(className.equals(RecyclerView.class.getName())){
            //如果是RecyclerView
            viewObserver = new RecycleViewObserver(view);
        }
        return viewObserver;
    }
}
