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
    //post请求
    @POST("/app/v1/first_hand")
    @FormUrlEncoded
    Observable<FirstHandBean> getDataSuccess(@Field("type") String type, @Field("dev_id") String devid, @Field("ver_code") int vercode, @Field("tick") String tick, @Field("sign") String sign);

    @POST("/app/v1/get_host")
    @FormUrlEncoded
    Observable<UrlBean> getUrlData(@Field("app_id") String app_id, @Field("dev_id") String devid, @Field("ver_code") int vercode, @Field("tick") String tick, @Field("sign") String sign);

    @POST("/app/v1/list_banner")
    Observable<UrlBean> getBanner(@QueryMap HashMap<String, String> map);

    //------------
    @POST("/app/v1/list_topic")
    Observable<UrlBean> getFree(@QueryMap HashMap<String, String> map);

    //用户注册
    @POST("/app/v1/user_reg")
    Observable<UrlBean> userRegin(@QueryMap HashMap<String, String> map);
    //用户校验
    @POST("/app/v1/user_check_rand")
    Observable<UrlBean> userCheck(@QueryMap HashMap<String, String> map);
}
