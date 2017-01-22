package com.autonavi.jacklee.ngandroid.angular.bean;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.autonavi.jacklee.ngandroid.angular.adapter.CommonAdapter;
import com.autonavi.jacklee.ngandroid.angular.observer.ViewObserver;
import com.autonavi.jacklee.ngandroid.angular.observer.ViewObserverFactory;
import com.autonavi.jacklee.ngandroid.angular.view.NgItemView;
import com.autonavi.jacklee.ngandroid.subject.EventSubject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jacklee on 17/1/12.
 */

public class NgGo {
    //每个主题都有其订阅者，即ngModel
    private HashMap<String, EventSubject> subjects;
    //当前nggo作用的域
    private View parent;
    private LayoutInflater inflater;

    public NgGo(View parent) {
        this.parent = parent;
        subjects = new HashMap<>();
        inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //添加ngModel
    public void addNgModel(NgModel ngModel) {
        subjects.put(ngModel.getTag(), ngModel);
    }

    //遍历这个域下的viewgroup的所有子节点，找出托管给nggo的view集合，并和对应的ngmodel关联起来
    private void selectViewTag(View view) {
        if(view instanceof ViewGroup){
            ViewGroup viewGroup = (ViewGroup)view;
            for (int i = 0, n = viewGroup.getChildCount(); i < n; i++) {
                View item = viewGroup.getChildAt(i);
                //判断是否是RecycleView
                if(item instanceof LinearLayout){
                    Log.d("liuji", "LinearLayout-->");
                    //2.判断tag是否为空，如果不为空，看开头是否是"nglist:"开头
                    if (item.getTag() != null && !TextUtils.isEmpty(item.getTag().toString()) && item.getTag().toString().startsWith("nglist:")) {

                        //1.获取view 的tag值，用来找出对应那个model的那个属性
                        String tag = item.getTag().toString();

                        RecyclerView recyclerView = new RecyclerView(view.getContext());
                        recyclerView.setLayoutParams(item.getLayoutParams());
                        //为recyclerView设置tag，否则ngModel在值发生改变时，通过tag遍历ViewObserver的时候，会发生view没有tag的空指针异常
                        recyclerView.setTag(tag);

                        //将之前的LinearLayout移除
                        viewGroup.removeView(item);
                        viewGroup.addView(recyclerView, i);


                        List<NgItemView> views = new ArrayList<>();
                        //3.实例化该viewgroup的所有item
                        for(int j = 0, m = ((ViewGroup) item).getChildCount(); j < m; j++){
                            View item_view = ((ViewGroup) item).getChildAt(j);
                            if(item_view instanceof NgItemView){
                                views.add((NgItemView)item_view);
                            }

                            //将该子view从父view中移除，不然，在CommonAdapter中把该子view当item时会报该子view有多个父view
                            ((ViewGroup) item).removeView(item_view);
                            //移除
                            j --;
                            m--;
                        }
                        //4.获取该RecyclerView对应的List的NgModel属性值

                        String[] tags = tag.split(":");
                        //对象tag
                        String model_tag = tags[1];
                        //对象属性
                        String model_property = tags[2];

                        //获取该view对应的NgModel
                        EventSubject subject = subjects.get(model_tag);

                        //将subject转为NgModel
                        NgModel ngModel = (NgModel)subject;
                        //获取属性对应的值
                        List<NgModel> list = (List<NgModel>) ngModel.getValue(model_property);
                        if(list == null){
                            list = new ArrayList<>();
                            //不能调用ngModel.addParams(),因为此方法会掉起UI操作，此时UI还未渲染完成
                            ngModel.getParams().put(model_property, list);
                        }
                        CommonAdapter adapter = new CommonAdapter(views, list, inflater);

                        //根据LinearLayout的Orientation属性，设置recyclerview的方向
                        if(((LinearLayout)item).getOrientation() == LinearLayout.HORIZONTAL){
                            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
                        }else{
                            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
                        }
                        //为recyclerview设置适配器
                        recyclerView.setAdapter(adapter);

                        //recyclerView (根据view类型，创建不同的ViewObserver)
                        ViewObserver viewObserver = ViewObserverFactory.createViewObserver(recyclerView, (NgModel) subject);
                        if(viewObserver != null){
                            subject.registe(viewObserver);
                        }
                    }else{
                        selectViewTag(item);
                    }
                }else {
                    Log.d("liuji", "Not_LinearLayout-->");
                    selectViewTag(item);
                }
            }
        }else{
            Log.d("liuji", "View-->");
            //1.获取view 的tag值，用来找出对应那个model的那个属性
            if(view.getTag() == null){
                return;
            }
            String tag = view.getTag().toString();
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
                ViewObserver viewObserver = ViewObserverFactory.createViewObserver(view, (NgModel)subject);
                if(viewObserver != null){
                    subject.registe(viewObserver);
                }
            }
        }

    }

    public void start(){
        selectViewTag(parent);
    }
}
