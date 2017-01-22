package com.autonavi.jacklee.ngandroid.angular.observer;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.DigitalClock;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextClock;
import android.widget.TextView;

import com.autonavi.jacklee.ngandroid.angular.bean.NgModel;
import com.autonavi.jacklee.ngandroid.angular.observer.impl.CheckBoxViewObserver;
import com.autonavi.jacklee.ngandroid.angular.observer.impl.EditViewObserver;
import com.autonavi.jacklee.ngandroid.angular.observer.impl.ImageViewObserver;
import com.autonavi.jacklee.ngandroid.angular.observer.impl.ProgressBarViewObserver;
import com.autonavi.jacklee.ngandroid.angular.observer.impl.RecycleViewObserver;
import com.autonavi.jacklee.ngandroid.angular.observer.impl.SeekBarViewObserver;
import com.autonavi.jacklee.ngandroid.angular.observer.impl.SwitchViewObserver;
import com.autonavi.jacklee.ngandroid.angular.observer.impl.TextViewObserver;

/**
 * Created by jacklee on 17/1/12.
 */

public class ViewObserverFactory {
    public static ViewObserver createViewObserver(View view, NgModel ngModel){
        ViewObserver viewObserver = null;
        if(view instanceof TextView){
            if(view instanceof EditText){
                //如果是输入框
                viewObserver = new EditViewObserver(view, ngModel);
            }else if(view instanceof Button){
                if(view instanceof CompoundButton) {
                    if (view instanceof CheckBox) {
                        //如果是CheckBox
                        viewObserver = new CheckBoxViewObserver(view, ngModel);
                    } else if(view instanceof Switch){
                        //如果是Switch
                        viewObserver = new SwitchViewObserver(view, ngModel);
                    }else {
                        //如果是RadioButton
                    }
                }else{
                    //如果是Button
                }
            }else if(view instanceof DigitalClock){

            }else if(view instanceof TextClock){

            }else if(view instanceof CheckedTextView){

            }else{
                //如果是TexView
                viewObserver = new TextViewObserver(view);
            }
        }else if(view instanceof ImageView){
            //如果是图片
            viewObserver = new ImageViewObserver(view);
        }else if(view instanceof ProgressBar){
            if(view instanceof SeekBar){
                viewObserver = new SeekBarViewObserver(view, ngModel);
            }else{
                //如果是ProgressBar
                viewObserver = new ProgressBarViewObserver(view);
            }
        }else if(view instanceof RecyclerView){
            //如果是RecyclerView
            viewObserver = new RecycleViewObserver(view);
        }
        return viewObserver;
    }
}
