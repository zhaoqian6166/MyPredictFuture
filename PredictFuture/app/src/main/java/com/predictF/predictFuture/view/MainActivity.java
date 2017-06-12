package com.predictF.predictFuture.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.predictF.predictFuture.R;
import com.predictF.predictFuture.bean.FirstHandBean;
import com.predictF.predictFuture.bean.UrlBean;
import com.predictF.predictFuture.presenter.main.PresenterClass;
import com.predictF.predictFuture.util.Tool;
import com.predictF.predictFuture.view.main.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {

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
    private FragmentManager manager;
    private SY_Fragment sy_fragment;
    private MyClassesFragment myClassesFragment;
    private MineFragment mineFragment;

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

    void initFragment() {
        sy_fragment = new SY_Fragment();
        Try aTry = new Try();
        myClassesFragment = new MyClassesFragment();
        mineFragment = new MineFragment();
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.sy_frame, sy_fragment, "sy");
        transaction.add(R.id.sy_frame, myClassesFragment, "myclass");
        transaction.add(R.id.sy_frame, mineFragment, "mine");
        transaction.show(sy_fragment);
        transaction.hide(myClassesFragment);
        transaction.hide(mineFragment);
        transaction.commit();

    }

    @OnClick({R.id.sy_study, R.id.sy_classes, R.id.sy_myselef})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sy_study:
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.show(sy_fragment);
                transaction.hide(myClassesFragment);
                transaction.hide(mineFragment);
                transaction.commit();
                break;
            case R.id.sy_classes:
                FragmentTransaction transaction2 = manager.beginTransaction();
                transaction2.show(myClassesFragment);
                transaction2.hide(sy_fragment);
                transaction2.hide(mineFragment);
                transaction2.commit();
                break;
            case R.id.sy_myselef:
                FragmentTransaction transaction3 = manager.beginTransaction();
                transaction3.show(mineFragment);
                transaction3.hide(sy_fragment);
                transaction3.hide(myClassesFragment);
                transaction3.commit();

                break;
        }
    }

    //回调
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        Log.d("userInfo","requestCode:"+requestCode+"--resultCode:"+resultCode);
        if (requestCode == 100 & resultCode == 101) {
            String userName = data.getStringExtra("userName");//得到用户名
            Log.d("userName",userName);
            mineFragment.setUserInfo(userName);
        }

    }
}
