
<!DOCTYPE html>
<html class="theme theme-white">
<head>
<meta charset="utf-8">
<title>AngualarAndroid</title>
<link href="https://www.zybuluo.com/static/assets/template-theme-white.css" rel="stylesheet" media="screen">
<style type="text/css">
</head>
<body class="theme theme-white">
<div id="wmd-preview" class="wmd-preview wmd-preview-full-reader"><div class="md-section-divider"></div><div class="md-section-divider"></div><h1 data-anchor-id="svwq" id="angualarandroid">AngualarAndroid</h1><p data-anchor-id="bnl7"><code>angularAndroid</code></p><hr><p data-anchor-id="0dea">先上一张完成的效果图：</p><div class="md-section-divider"></div><h2 data-anchor-id="zua8" id="title"><img src="http://static.zybuluo.com/JackLee/6gcggwcdtg3zz1077vzdr946/2017-02-20-05mza.gif" alt="2017-02-20-05mza.gif-3285.8kB"></h2><hr><p data-anchor-id="47st">完成这么多的UI操作，其实指需要很少的代码，现在附上全部关键代码。 <br>
    首先是布局文件：activity_main.xml</p><pre data-anchor-id="sq1l"><code>&lt;?xml version="1.0" encoding="utf-8"?&gt;
</code></pre><p data-anchor-id="8a16">
    xmlns:ng="http://schemas.android.com/apk/res-auto" <br>
    xmlns:tools="http://schemas.android.com/tools" <br>
    android:id="@+id/activity_main" <br>
    android:layout_width="match_parent" <br>
    android:layout_height="match_parent" <br>
    android:orientation="vertical"&gt;</p><pre data-anchor-id="gndg"><code>&lt;LinearLayout
    android:id="@+id/ll_container"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"&gt;

    &lt;TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="10dp"
        android:tag="ng:user:name"
        android:text="Hello World!" /&gt;

    &lt;EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="userName"
        android:padding="10dp"
        android:tag="ng:user:name" /&gt;

    &lt;TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="10dp"
        android:tag="ng:user:age" /&gt;

    &lt;ProgressBar
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        style="@style/Base.Widget.AppCompat.ProgressBar.Horizontal"
        android:progress="10"
        android:tag="ng:user:age"/&gt;

    &lt;SeekBar
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:progress="0"
        android:tag="ng:user:age"/&gt;

    &lt;LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal"&gt;

        &lt;Switch
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:text="男"
            android:tag="ng:user:isMale"
            android:layout_margin="10dp"/&gt;

        &lt;CheckBox
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:tag="ng:user:isMale"
            android:text="男"
            android:layout_margin="10dp" /&gt;

        &lt;TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_margin="10dp"
            android:tag="ng:user:isMale"
            android:text="Hello World!" /&gt;

    &lt;/LinearLayout&gt;

    &lt;LinearLayout
        android:id="@+id/rv_list"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:tag="nglist:user:list"&gt;

        &lt;com.autonavi.jacklee.ngandroid.angular.view.NgItemView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:tag="ng:student"
            ng:ngRecyclerViewItemList="@layout/item_student" /&gt;

        &lt;com.autonavi.jacklee.ngandroid.angular.view.NgItemView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:tag="ng:title"
            ng:ngRecyclerViewItemList="@layout/item_tag" /&gt;

        &lt;com.autonavi.jacklee.ngandroid.angular.view.NgItemView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:tag="ng:content"
            ng:ngRecyclerViewItemList="@layout/item_content" /&gt;

    &lt;/LinearLayout&gt;


&lt;/LinearLayout&gt;
</code></pre><p></p><p data-anchor-id="i9wi">其中java代码部分如下：</p><p data-anchor-id="c2un">public class MainActivity extends AppCompatActivity implements CommonAdapter.CommonAdapterInterface{ <br>
    private NgGo ngGo; <br>
    private NgModel ngUser; <br>
    private List list; <br>
    private LinearLayout ll_container; <br>
    private Handler handler = new Handler(){ <br>
        @Override <br>
        public void handleMessage(Message msg) { <br>
            super.handleMessage(msg);</p><pre data-anchor-id="10bn"><code>        if((int)ngUser.getValue("age") &gt;= 100){
            ngUser.addParams("age", 0 );
        }else{
            ngUser.addParams("age", ((int)ngUser.getValue("age")) + 2 );
        }

        NgModel user = ((List&lt;NgModel&gt;)ngUser.getValue("list")).get(0);
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

    list = new ArrayList&lt;&gt;();
    for(int i = 0; i&lt;10; i++){
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
</code></pre><p data-anchor-id="kzo4">}</p><p data-anchor-id="wb6f">以上就是所有代码，是不是感觉代码很少？哈哈哈</p><p data-anchor-id="8b7z">现在开始介绍AngularAndroid</p><p data-anchor-id="jpsc">一 需要记住的几个对象</p><ol data-anchor-id="0r2u">
<li>NgGo：这个是AngularAndroid渲染的具体执行者</li>
<li>NgModel：这个是数据绑定中的绑定对象，只要它的属性值产生了变化，那么只要绑定在它身上的View就会做出相应的改变</li>
<li>CommonAdapter：这个是RecyclerView的通用适配器</li>
<li>NgItemView：这个是RecyclerView的Item，NgGo在监测到LinearLayout的tag中以nglist开头，会自动将LinearLayout转为RecyclerView，LinearLayout有多少个NgItemView，RecyclerView就有多少种item类型。</li>
</ol><p data-anchor-id="tf7r">二 使用步骤 <br>
    1.初始化NgGo对象 NgGo ngg = new NgGo(View parent);</p><blockquote data-anchor-id="qgl1" class="white-blockquote">
  <p>parent这个view对象，类似与angularjs中的控制域，可能一个页面中有不同的逻辑部分，这个时候，需要多个逻辑对象，这样的话，每个逻辑操作对象都对应一个控制域。一般这样的情况比较少。</p>
</blockquote><p data-anchor-id="kbp9">2.初始化NgModel对象 NgModel user = new NgModel("user");</p><blockquote data-anchor-id="9zgo" class="white-blockquote">
  <p>其中“user”这个参数，对应xml中"ng:user:name"的user，也就是NgModel需要指定对象名字。</p>
</blockquote><p data-anchor-id="fjpo">3.将NgModel添加到NgGo中，交给NgGo去控制 ngg.addNgModel(user);</p><p data-anchor-id="gc5p">4.NgGo开始渲染 ngg.start();</p><p data-anchor-id="p5em">至此，数据绑定完成，现在尝试改变user的属性值：user.addParams("name", "Jhon");</p><p data-anchor-id="sjnd">然后运行程序，是不是发现只要xml中tag为"ng:user:name"的view都显示"Jhon"？</p><p data-anchor-id="2dhc">因为是双向绑定，所以，当view的文本发生改变时，对应的NgModel的相应属性也会发生变化。倘若Editext和TextView的tag都设置为"ng:user:name"时，会发现，TextView的值是跟着EditText的值动态改变的。</p><p data-anchor-id="nhpv">有人会问这有什么用？现在举例一个最简单的场景：</p><p data-anchor-id="hv6t">现在要做一个登录页面： <br>
原始做法： <br>
1.实例化帐号和密码两个EditText <br>
3.点击登录时，判断EditText的输入值是否符合规定。</p><p data-anchor-id="paht">现在的做法： <br>
1.点击登录时，判断user对象的帐号（account）和密码（password）是否符合规定</p><p data-anchor-id="bq6w">有木有发现，全程不用关注View对象，只需要关注具体的逻辑对象User，思维不用来回切</p></div>
</body>
</html>
