package com.predictF.predictFuture.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by 赵倩 on 2017/5/26.
 * <p/>
 * 类的用途：
 */
public class UrlBean {
    public Data data;
    public class Data{
        public String login;
        public String uname;
        public String alert;
        public String message;
        public String session;
        public String url_host;
        public ArrayList<BannerBean> banner;
        public ArrayList<Try> topic;

    }
    public class BannerBean{
        public String image;
        public String click;
    }
    public class Try{
        public String title2;
        public String speaker;
        public String title;
        public int try_time;
        public String image;
        public int type;
        public int length;
        public String object_id;

    }
}
