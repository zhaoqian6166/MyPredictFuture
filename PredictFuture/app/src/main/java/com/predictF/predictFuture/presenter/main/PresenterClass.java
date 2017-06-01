package com.predictF.predictFuture.presenter.main;

import android.content.Context;

import com.predictF.predictFuture.bean.FirstHandBean;
import com.predictF.predictFuture.bean.FirstHandErrorBean;
import com.predictF.predictFuture.bean.UrlBean;
import com.predictF.predictFuture.modle.main.ModleClass;
import com.predictF.predictFuture.util.Api;
import com.predictF.predictFuture.util.Tool;
import com.predictF.predictFuture.view.main.Iview;

/**
 * Created by 赵倩 on 2017/5/26.
 * <p/>
 * 类的用途：
 */
public class PresenterClass implements IPresenter {
    //得到m层和v层的对象
    private ModleClass iModle;
    private Iview iview;
    private Context context;
    private String TAG="p";

    public PresenterClass(Context context, Iview iview) {
        this.context = context;
        this.iview = iview;
        iModle=new ModleClass(this,context);
    }
//第一次握手
    public void getFirstHandDataFromModle() {
        iModle.firstHandInModle(Api.BASE_URL);//请求网络
    }
    //获得导向地址
    public void getUrlFromModle(){
        iModle.getUrl(Api.BASE_URL);
    }
    //注册
    public  void getRegin(String num,String pwd,String rand){
        String newUrl = Tool.getNewUrl(context);
        iModle.getRegin(newUrl,num,pwd,rand);
    }


    @Override
    public void onFirstHandSuccess(FirstHandBean bean) {
        //第一次握手成功  将值传递给v层
        iview.showData(bean);
    }


    @Override
    public void getUrlData(UrlBean bean) {
        iview.getUri(bean);

    }
//注册
    @Override
    public void regin(UrlBean bean) {
      //  iview.userRegin(bean);//将得到的注册信息传递到view
      //  注册之后进行验证
        String newUrl = Tool.getNewUrl(context);
        String session = bean.data.session;
      //  iModle.getReginCheck(newUrl,pwd,rand,session,context);

    }
//验证
    @Override
    public void reginCheck(UrlBean bean) {
        iview.userRegin(bean);

    }

}
