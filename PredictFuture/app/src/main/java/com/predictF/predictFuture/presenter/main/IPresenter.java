package com.predictF.predictFuture.presenter.main;

import com.predictF.predictFuture.bean.FirstHandBean;
import com.predictF.predictFuture.bean.FirstHandErrorBean;
import com.predictF.predictFuture.bean.UrlBean;

import org.w3c.dom.UserDataHandler;

/**
 * Created by 赵倩 on 2017/5/26.
 * <p/>
 * 类的用途：
 */
public interface IPresenter {
    void onFirstHandSuccess(FirstHandBean bean);//请求成功
    void getUrlData(UrlBean bean);
    void regin(UrlBean bean);
    void userPwdlogin(UrlBean bean);
    void getDetailClasses(UrlBean bean);
    void getHeartP(UrlBean bean);


}
