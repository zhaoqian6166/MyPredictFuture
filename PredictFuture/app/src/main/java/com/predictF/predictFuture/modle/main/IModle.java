package com.predictF.predictFuture.modle.main;

import android.content.Context;

/**
 * Created by 赵倩 on 2017/5/26.
 * <p/>
 * 类的用途：
 */
public interface IModle {
    void firstHandInModle(String url);
    void getUrl(String url);
    void getRegin(String url,String num,String pwd,String rand);
    void getReginCheck(String url, String pwd, String rand, String session, Context context);

}
