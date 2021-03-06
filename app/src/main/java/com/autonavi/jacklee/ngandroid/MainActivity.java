package com.autonavi.jacklee.ngandroid;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.autonavi.jacklee.ngandroid.angular.adapter.CommonAdapter;
import com.autonavi.jacklee.ngandroid.angular.bean.NgGo;
import com.autonavi.jacklee.ngandroid.angular.bean.NgModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CommonAdapter.CommonAdapterInterface{
    private NgGo ngGo;
    private NgModel ngUser;
    private List<NgModel> list;
    private LinearLayout ll_container;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if((int)ngUser.getValue("age") >= 100){
                ngUser.addParams("age", 0 );
            }else{
                ngUser.addParams("age", ((int)ngUser.getValue("age")) + 2 );
            }

            NgModel user = ((List<NgModel>)ngUser.getValue("list")).get(0);
            user.addParams("name", "Jack" + ((int)ngUser.getValue("age")));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll_container = (LinearLayout) findViewById(R.id.ll_container);
        ngGo = new NgGo(ll_container);

        ngUser = new NgModel("user");

        ngGo.addNgModel(ngUser);

        ngGo.start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initNgGo();
    }

    private void initNgGo(){

        ngUser.addParams("name", "Jhon");
        ngUser.addParams("sex", "nan");
        ngUser.addParams("age", 14);
        ngUser.addParams("isMale", false);

        list = new ArrayList<>();
        for(int i = 0; i<10; i++){
            if(i%3 == 0){
                NgModel ngUser = new NgModel("student");
                ngUser.addParams("name", "Jack" + i);
                list.add(ngUser);
            }else if(i%3 == 1){
                NgModel ngUser = new NgModel("title");
                ngUser.addParams("name", "title" + i);
                list.add(ngUser);
            }else{
                NgModel ngUser = new NgModel("content");
                ngUser.addParams("name", "Content" + i);
                list.add(ngUser);
            }
        }

        ngUser.addParams("list", list);

        Message msg = Message.obtain();
        handler.sendMessageDelayed(msg, 1000);

        CommonAdapter adapter = ngGo.getRecyclerAdapter(R.id.rv_list);
        adapter.setCommonAdapterInterface(this);
    }


    @Override
    public void handleItem(int id, CommonAdapter.CommonHolder holder, final int position) {
        switch (id){
            case R.id.rv_list:
                if(list.get(position).getTag().equals("student")){
                    holder.getView(R.id.iv_head).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getApplicationContext(), position + "_head", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                holder.getItemView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), position + "_item", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }
}
