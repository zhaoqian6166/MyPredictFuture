package com.predictF.predictFuture.modle.main;

import android.content.Context;
import android.util.Log;

import com.predictF.predictFuture.bean.FirstHandBean;
import com.predictF.predictFuture.bean.UrlBean;
import com.predictF.predictFuture.modle.base.MyAdapter;
import com.predictF.predictFuture.presenter.main.IPresenter;
import com.predictF.predictFuture.util.Api;
import com.predictF.predictFuture.util.IRetrofitService;
import com.predictF.predictFuture.util.Tool;

import java.util.HashMap;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 赵倩 on 2017/5/26.
 * <p/>
 * 类的用途：
 */
public class ModleClass implements IModle {
    //有一个Presenter层的对象
    private IPresenter iPresenter;
    private Context context;
    private String TAG="ModleClass";

    public ModleClass(IPresenter iPresenter, Context context) {
        this.iPresenter = iPresenter;
        this.context = context;
    }

    //第一次握手的具体操作
    @Override
    public void firstHandInModle(String url) {
        //执行网络请求
        IRetrofitService retrofitService = Tool.getNetData(url);
        //创建Observer
        String s=Api.PUBLIC_KEY+Api.TYPE+Tool.getDevId(context)+Tool.getVersionCode(context)+Tool.getTick();
        String sign = Tool.getSign(s);
        Log.d("parm-",Api.TYPE+"--"+Tool.getDevId(context)+"--"+Tool.getVersionCode(context)+"--"+Tool.getTick()+"--"+sign);
        Observable<FirstHandBean> observable = retrofitService.getDataSuccess(Api.TYPE, Tool.getDevId(context), Tool.getVersionCode(context), Tool.getTick(),sign);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FirstHandBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("测试","--onCompleted--");
                        Log.i(TAG,"onCompleted");

                }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("测试","--onError--");
                        Log.i(TAG,"onError");
                    }

                    @Override
                    public void onNext(FirstHandBean bean) {
                        Log.d("测试","--onNext--");
                        Log.i(TAG,bean.ret);
                        //请求成功  将请求成功的数据返回到P层
                        iPresenter.onFirstHandSuccess(bean);
                    }
                });

    }
//连接导向   获得新的url地址
    @Override
    public void getUrl(String url) {
        //执行网络请求
        IRetrofitService retrofitService = Tool.getNetData(url);
        String s=Tool.getP_key(context)+Tool.getAppid(context)+Tool.getDevId(context)+Tool.getVersionCode(context)+Tool.getTick();
        String sign = Tool.getSign(s);
        Observable<UrlBean> observable = retrofitService.getUrlData(Tool.getAppid(context), Tool.getDevId(context), Tool.getVersionCode(context), Tool.getTick(),sign);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UrlBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("测试","--onCompleted--");
                        Log.i(TAG,"onCompleted");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("测试","--onError--");
                        Log.i(TAG,"onError");
                    }

                    @Override
                    public void onNext(UrlBean bean) {
                        Log.d("测试","--onNext--");
                       // Log.i(TAG,bean.ret);
                        //请求成功  将请求成功的数据返回到P层
                        iPresenter.getUrlData(bean);
                    }
                });

    }
//注册
    @Override
    public void getRegin(final String url, String num, final String pwd, final String rand) {
        //执行网络请求
        IRetrofitService retrofitService = Tool.getNetData(url);
        //java动态代理方式实现
        HashMap<String, String> map = Tool.getMap(context);
        String s=Tool.getP_key(context)+Tool.getAppid(context)+Tool.getDevId(context)+Tool.getVersionCode(context)+Tool.getTick()+num;
     //   map.put("mobile",num);
        String sign = Tool.getSign(s);//获得sign
        //Log.d("sign",sign);
        map.put("mobile",num);
        map.put("sign",sign);
        Observable<UrlBean> observable = retrofitService.userRegin(map);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UrlBean>() {
                    @Override
                    public void onCompleted() {

                        Log.d("getRegin","--onCompleted--");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("getRegin","--onError--");
                    }

                    @Override
                    public void onNext(UrlBean bean) {
                        Log.d("getRegin","--onNext--");
                        Log.d("getRegin","--session--"+bean.data.session);
                        //将得到的值返回到P层  bean中session有效
                       // iPresenter.getBest(bean,myAdapter);

                        getReginCheck(url,pwd,rand,bean.data.session,context);
                    }
                });




    }
//注册校验
    @Override
    public void getReginCheck(String url,String pwd,String rand,String session,Context context) {
        //执行网络请求
        IRetrofitService retrofitService = Tool.getNetData(url);
        //java动态代理方式实现
        HashMap<String, String> map = Tool.getMap(context);
        String s=Tool.getP_key(context)+Tool.getAppid(context)+Tool.getDevId(context)+Tool.getVersionCode(context)+Tool.getTick()+session+rand+pwd;
        //   map.put("mobile",num);
        map.put("session",session);
        map.put("rand",rand);
        map.put("passwd",pwd);
        String sign = Tool.getSign(s);//获得sign
        map.put("sign",sign);
        Observable<UrlBean> observable = retrofitService.userCheck(map);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UrlBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("getReginCheck","--onCompleted--");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("getReginCheck","--onError--");
                    }

                    @Override
                    public void onNext(UrlBean bean) {
                        Log.d("getReginCheck","--onNext--");
                        Log.d("getReginCheck","--session--"+bean.data.session);
                        //将得到的值返回到P层  bean中session有效
                        // iPresenter.getBest(bean,myAdapter);
                        iPresenter.regin(bean);
                    }
                });
    }


}
