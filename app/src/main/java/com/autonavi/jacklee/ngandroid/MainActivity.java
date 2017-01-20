package com.autonavi.jacklee.ngandroid;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.autonavi.jacklee.ngandroid.angular.bean.NgGo;
import com.autonavi.jacklee.ngandroid.angular.bean.NgModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NgGo ngGo;
    private NgModel ngUser;
    private LinearLayout ll_container;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ngUser.addParams("age", ((int)ngUser.getValue("age")) + 2 );
            msg = Message.obtain();
            handler.sendMessageDelayed(msg, 1000);
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
        ngUser.addParams("isMale", true);

        List<NgModel> list = new ArrayList<>();
        for(int i = 0; i<13; i++){
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
    }


}
