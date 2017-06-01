package com.predictF.predictFuture.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.predictF.predictFuture.R;
import com.predictF.predictFuture.bean.FirstHandBean;
import com.predictF.predictFuture.bean.UrlBean;
import com.predictF.predictFuture.presenter.main.PresenterClass;
import com.predictF.predictFuture.util.Tool;
import com.predictF.predictFuture.view.main.IBaseClass;
import com.predictF.predictFuture.view.main.Iview;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends IBaseClass {

    @BindView(R.id.sy_frame)
    FrameLayout syFrame;
    @BindView(R.id.sy_study)
    RadioButton syStudy;
    @BindView(R.id.sy_classes)
    RadioButton syClasses;
    @BindView(R.id.sy_myselef)
    RadioButton syMyselef;
    private PresenterClass presenterClass;
    private String TAG = "MainActivity";
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SharedPreferences spf = Tool.getSpf(getApplicationContext());
        editor = spf.edit();
        //得到P层的对象
        presenterClass = new PresenterClass(getApplicationContext(), this);
        boolean isFirst = spf.getBoolean("isFirst", true);
        if (isFirst) {//表示是第一次
            firstHand();
        } else {
            //如果不是第一次  不会握手  也不会重新获得url
            presenterClass.getUrlFromModle();

        }

    }

    //首次握手  请求网络 获取数据
    private void firstHand() {
        presenterClass.getFirstHandDataFromModle();
        editor.putBoolean("isFirst", false);
        editor.commit();
    }

    //展示数据
    @Override
    public void showData(FirstHandBean bean) {
        String ret = bean.ret;
        Log.i(TAG, ret);
        String app_id = bean.data.app_id;
        String private_key = bean.data.private_key;
        editor.putString("app_id", app_id);
        editor.putString("private_key", private_key);
        editor.commit();
        presenterClass.getUrlFromModle();

    }

    @Override
    public void getUri(UrlBean bean) {
        //服务器导向  得到新的url地址
        String url_host = bean.data.url_host;
        Log.i(TAG, url_host);
        editor.putString("url_host", url_host);
        editor.commit();
        //初始化Fragment
        initFragment();

    }

    void initFragment(){
        SY_Fragment sy_fragment = new SY_Fragment();
        Try aTry = new Try();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.sy_frame,sy_fragment,"sy");
     //   transaction.add(R.id.sy_frame,aTry,"try");
        transaction.show(sy_fragment);
     //   transaction.hide(aTry);
        transaction.commit();

    }

}
