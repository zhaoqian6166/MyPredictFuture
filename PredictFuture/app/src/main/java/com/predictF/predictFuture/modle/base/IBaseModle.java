package com.predictF.predictFuture.modle.base;

import android.content.Context;

import com.predictF.predictFuture.bean.UrlBean;

import java.util.ArrayList;

/**
 * Created by 赵倩 on 2017/5/27.
 * <p>
 * 类的用途：
 */
public interface IBaseModle {
    void getBanner(String url);
    void getBest(String url,Context context);
    void getMyClasses(String url);

}
