package com.predictF.predictFuture.view.main;

import com.predictF.predictFuture.bean.FirstHandBean;
import com.predictF.predictFuture.bean.FirstHandErrorBean;
import com.predictF.predictFuture.bean.UrlBean;

/**
 * Created by 赵倩 on 2017/5/26.
 * <p/>
 * 类的用途：
 */
public interface Iview {
    void showData(FirstHandBean bean);
   // void erroData(FirstHandErrorBean bean);
    void getUri(UrlBean bean);
   // void showBanner(UrlBean bean);
    //用户注册
    void userRegin(UrlBean bean);
    //登录
    void userPwdLogin(UrlBean bean);
    //详情页面数据
    void showDetail(UrlBean bean);
    //收藏
    void getHeartV(UrlBean bean);

}
