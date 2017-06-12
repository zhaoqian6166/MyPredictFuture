package com.predictF.predictFuture.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by 赵倩 on 2017/5/26.
 * <p/>
 * 类的用途：
 */
public class UrlBean {
    public int ret;
    public Data data;
    public class Data{
        public String login;
        public String uname;
        public boolean alert;
        public String message;
        public String session;
        public String url_host;
        public ArrayList<BannerBean> banner;
        public ArrayList<Try> topic;
        public ArrayList<Try> course;
        @SerializedName("try")
        public ArrayList<Try> list_try;
        public String image;
        public String object_id;
        public String title2;
        public String title;
        @SerializedName("abstract")
        public String abs;
        public String speaker_head;
        public String speaker_audio;
        public String course_video;
        public int try_length;
        public int volume;
        public int comment_num;
        public String exam_score;
        public String service_tel;
        public int type;
        public int status;
        public String msg;


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
        public String progress;
        public String exam_score;

    }
}
