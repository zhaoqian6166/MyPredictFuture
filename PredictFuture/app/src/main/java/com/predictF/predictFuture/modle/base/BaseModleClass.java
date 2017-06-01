package com.predictF.predictFuture.modle.base;

import android.content.Context;
import android.util.Log;

import com.predictF.predictFuture.bean.UrlBean;
import com.predictF.predictFuture.presenter.base.BasePresenterClass;
import com.predictF.predictFuture.util.IRetrofitService;
import com.predictF.predictFuture.util.Tool;

import java.util.ArrayList;
import java.util.HashMap;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 赵倩 on 2017/5/27.
 * <p>
 * 类的用途：
 */
public class BaseModleClass implements IBaseModle {
    private BasePresenterClass bpc;
    private Context context;

    public BaseModleClass(BasePresenterClass bpc, Context context) {
        this.bpc = bpc;
        this.context = context;
    }

    private String TAG="M_Base";
    @Override
    public void getBanner(String url) {
        //执行网络请求
        IRetrofitService retrofitService = Tool.getNetData(url);
        //java动态代理方式实现
        HashMap<String, String> map = Tool.getMap(context);
        String s=Tool.getP_key(context)+Tool.getAppid(context)+Tool.getDevId(context)+Tool.getVersionCode(context)+Tool.getTick();
        String sign = Tool.getSign(s);
        map.put("sign",sign);
        Observable<UrlBean> observable = retrofitService.getBanner(map);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UrlBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("banner","--onCompleted--");
                        Log.i(TAG,"onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("banner","--onError--");
                        Log.i(TAG,"onError");
                    }

                    @Override
                    public void onNext(UrlBean bean) {
                        Log.d("banner","--onNext--");
                        // Log.i(TAG,bean.ret);
                        //请求成功  将请求成功的数据返回到P层
                        Log.d("banner_bean",bean.data.banner.get(0).image);
                        bpc.getBanner(bean);
                    }
                });
    }

    @Override
    public void getBest(String url, final Context context) {
        //执行网络请求
        IRetrofitService retrofitService = Tool.getNetData(url);
        //java动态代理方式实现
        HashMap<String, String> map = Tool.getMap(context);
       /* map.put("page_size","5");
        map.put("page_index","0");*/
        String s=Tool.getP_key(context)+Tool.getAppid(context)+Tool.getDevId(context)+Tool.getVersionCode(context)+Tool.getTick()+5+0;
        String sign = Tool.getSign(s);
        map.put("page_size",5+"");
        map.put("page_index",0+"");
        map.put("sign",sign);

        Observable<UrlBean> observable = retrofitService.getFree(map);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UrlBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("getBest","--onCompleted--");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("getBest","--onError--");
                    }

                    @Override
                    public void onNext(UrlBean bean) {
                        Log.d("getBest","--onNext--");
                        //请求成功  将请求成功的数据返回到P层
                        Log.d("getBest",bean.data.topic.size()+"");
                        MyAdapter myAdapter = new MyAdapter(bean.data.topic, context);
                       // bpc.getAdapter(myAdapter);
                        bpc.getBest(bean,myAdapter);
                    }
                });
    }

}
