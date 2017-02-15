package com.autonavi.jacklee.ngandroid.angular.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.autonavi.jacklee.ngandroid.angular.bean.NgGo;
import com.autonavi.jacklee.ngandroid.angular.bean.NgModel;
import com.autonavi.jacklee.ngandroid.angular.view.NgItemView;
import com.autonavi.jacklee.ngandroid.subject.EventSubject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jacklee on 17/1/12.
 */

public class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.CommonHolder> {
    private List<NgModel> list;
    private List<NgItemView> views;
    private LayoutInflater inflater;
    private CommonAdapterInterface commonAdapterInterface;
    private int rv_id;//当前RecyclerView的ID

    public CommonAdapter(List<NgItemView> views, List<NgModel> list, LayoutInflater inflater) {
        this.views = views;
        this.list = list;
        this.inflater = inflater;
    }

    public void setCommonAdapterInterface(CommonAdapterInterface commonAdapterInterface) {
        this.commonAdapterInterface = commonAdapterInterface;
    }

    public void setRv_id(int rv_id) {
        this.rv_id = rv_id;
    }

    @Override
    public CommonAdapter.CommonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //1.获取该位置的view
        int laytoutId = views.get(viewType).getLaytoutId();
        View view = inflater.inflate(laytoutId, parent, false);
        return new CommonHolder(view);
    }

    @Override
    public void onBindViewHolder(CommonAdapter.CommonHolder holder, int position) {
        //1.获取该位置的NgModel
        NgModel ngModel = list.get(position);
        //2.为该holder设置数据，进行渲染
        holder.setData(ngModel);
        if(commonAdapterInterface != null){
            commonAdapterInterface.handleItem(rv_id, holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        //1.获取该位置的NgModel
        NgModel ngModel = list.get(position);
        //2.获取这个ngModel的tag
        String tag = ngModel.getTag();
        //3.遍历所有的View类型，找到对应的view
        for(int i = 0, n = views.size();i < n; i ++){
            //4.获取view的tag
            String view_tag = views.get(i).getTag().toString();
            String[] tags = view_tag.split(":");
            if(tag.equals(tags[1])){
                //找到了对应的view，返回该view的索引
                return i;
            }
        }
        return 0;
    }

    public class CommonHolder extends RecyclerView.ViewHolder{
        private SparseArray<View> views;

        public CommonHolder(View itemView) {
            super(itemView);
            views = new SparseArray<View>();
        }

        public void setData(NgModel ngModel){


            if(itemView.getTag() != null && itemView.getTag() instanceof EventSubject){

                EventSubject subject = (EventSubject)itemView.getTag();
                subject.removeAll();
                itemView.setTag(null);
            }
            NgGo ngg = new NgGo(itemView);
            ngg.addNgModel(ngModel);
            ngg.start();

            HashMap<String, Object> params = ngModel.getParams();
            Iterator<String> it = params.keySet().iterator();
            //1.循环遍历
            while (it.hasNext()){
                //获取ngModel的属性名字
                String property = it.next();
                //获取该属性的属性值
                Object value = ngModel.getValue(property);
                ngModel.addParams(property, value);
            }
            itemView.setTag(ngModel);
        }

        public View getView(int viewId){
            View v = views.get(viewId);
            if(v == null){
                v = itemView.findViewById(viewId);
                views.put(viewId, v);
            }
            return v;
        }

        public View getItemView(){
            return itemView;
        }
    }

    public interface CommonAdapterInterface{
        public void handleItem(int id, CommonHolder holder, int position);
    }
}
