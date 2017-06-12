package com.predictF.predictFuture.util;

import com.predictF.predictFuture.bean.FirstHandBean;
import com.predictF.predictFuture.bean.UrlBean;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by 赵倩 on 2017/5/26.
 * <p/>
 * 类的用途：Retrofit接口
 */
public interface IRetrofitService {
    //首次握手
    @POST("/app/v1/first_hand")
    @FormUrlEncoded
    Observable<FirstHandBean> getDataSuccess(@Field("type") String type, @Field("dev_id") String devid, @Field("ver_code") int vercode, @Field("tick") String tick, @Field("sign") String sign);

    //连接服务导向
    @POST("/app/v1/get_host")
    @FormUrlEncoded
    Observable<UrlBean> getUrlData(@Field("app_id") String app_id, @Field("dev_id") String devid, @Field("ver_code") int vercode, @Field("tick") String tick, @Field("sign") String sign);

    //首页轮播
    @POST("/app/v1/list_banner")
    Observable<UrlBean> getBanner(@QueryMap HashMap<String, String> map);

    //------免费------
    @POST("/app/v1/list_try")
    Observable<UrlBean> getFree(@QueryMap HashMap<String, String> map);

    //用户注册
    @POST("/app/v1/user_reg")
    Observable<UrlBean> userRegin(@QueryMap HashMap<String, String> map);

    //用户校验
    @POST("/app/v1/user_check_rand")
    Observable<UrlBean> userCheck(@QueryMap HashMap<String, String> map);

    //登录
    @POST("/app/v1/user_pwd_login")
    Observable<UrlBean> userPwdLogin(@QueryMap HashMap<String, String> map);

    //获取我的课程列表
    @POST("/app/v1/list_my_course")
    Observable<UrlBean> getMyClasses(@QueryMap HashMap<String, String> map);

    //获取免费课程条目点击后的条目的详情
    @POST("/app/v1/detail_course")
    Observable<UrlBean> getDetailClasses(@QueryMap HashMap<String, String> map);

    //收藏课程
    @POST("/app/v1/heart_object")
    Observable<UrlBean> getHeartClasses(@QueryMap HashMap<String, String> map);



}
