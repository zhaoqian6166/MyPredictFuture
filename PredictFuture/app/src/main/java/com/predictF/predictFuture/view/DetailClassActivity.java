package com.predictF.predictFuture.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.predictF.predictFuture.R;
import com.predictF.predictFuture.bean.EventBean;
import com.predictF.predictFuture.bean.UrlBean;
import com.predictF.predictFuture.presenter.main.PresenterClass;
import com.predictF.predictFuture.util.MyJCVideo;
import com.predictF.predictFuture.util.XCRoundImageView;
import com.predictF.predictFuture.view.main.BaseActivity;
import com.predictF.predictFuture.view.main.PayActivity;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by 赵倩 on 2017/6/2.
 * <p>
 * 类的用途：
 */
public class DetailClassActivity extends BaseActivity {
    @BindView(R.id.detail_video)
    MyJCVideo detail_video;
    @BindView(R.id.detail_return)
    ImageView detailReturn;
    @BindView(R.id.detail_share)
    ImageView detailShare;
    @BindView(R.id.detail_Img_Text2)
    TextView detailImgText2;
    @BindView(R.id.detail_buy)
    Button detailBuy;
    @BindView(R.id.detail_buy_ll)
    LinearLayout detailBuyLl;
    @BindView(R.id.detail_tryend_tobuy)
    LinearLayout detail_tryend_tobuy;
    @BindView(R.id.detail_teacherImg)
    XCRoundImageView detailTeacherImg;
    @BindView(R.id.detail_teacherName)
    TextView detailTeacherName;
    @BindView(R.id.detail_showNum)
    TextView detailShowNum;
    @BindView(R.id.detail_title1)
    TextView title1;
    @BindView(R.id.detail_title2)
    TextView title2;
    @BindView(R.id.detail_content)
    TextView text_content;
    @BindView(R.id.detail_listner)
    ImageView detail_listner;
    @BindView(R.id.detail_tel)
    RadioButton detailTel;
    @BindView(R.id.detail_heart)
    RadioButton detailHeart;
    @BindView(R.id.detail_download)
    RadioButton detailDownload;
    @BindView(R.id.detail_img)
    Button detailImg;
    private PresenterClass presenterClass;
    private UrlBean mbean;
    private String object_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);//注册
        title2.setMovementMethod(ScrollingMovementMethod.getInstance());
        //获得传过来的数据
        Intent intent = getIntent();
        object_id = intent.getStringExtra("object_id");
        //请求数据
        //得到P层的对象
        presenterClass = new PresenterClass(getApplicationContext(), this);
        Log.i("---Detail---", "object_id" + object_id);
        presenterClass.getDetail(object_id);


    }

    //点击事件
    @OnClick({R.id.detail_return, R.id.detail_buy, R.id.detail_listner, R.id.detail_tel, R.id.detail_heart, R.id.detail_download,R.id.detail_tryend_tobuy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.detail_return:
                break;
            case R.id.detail_buy:
                //点击立即购买  跳转到购买页面  支付购买
                Intent intent=new Intent(DetailClassActivity.this, PayActivity.class);
                intent.putExtra("price",0.01+"");
                startActivity(intent);

                break;
            case R.id.detail_listner:
                //点击后成为音频播放
                ImageView listnerview = detail_video.thumbImageView;
                listnerview.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Glide.with(DetailClassActivity.this).load(R.mipmap.course_course_frequency).into(listnerview);
                detail_video.setUp(mbean.data.speaker_audio, "");

                break;
            case R.id.detail_tel:
                break;
            case R.id.detail_heart:
                //收藏按钮
                presenterClass.getHeard(object_id);
                break;
            case R.id.detail_download:
                break;
            case R.id.detail_tryend_tobuy:
                //点击立即购买  跳转到购买页面  支付购买
                Intent intent2=new Intent(DetailClassActivity.this, PayActivity.class);
                intent2.putExtra("price",0.01+"");
                startActivity(intent2);
                break;
        }
    }

    //展示详情页面数据
    @Override
    public void showDetail(UrlBean bean) {
        super.showDetail(bean);
        mbean = bean;
        ImageView view = detail_video.thumbImageView;
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(DetailClassActivity.this).load(R.mipmap.course_video).into(view);
        //设置播放地址与标题  标题为空
        detail_video.setUp(bean.data.course_video, "");
        //头像
        Log.i("xxx", bean.data.speaker_head);
        //自定义的圆形图片，不能使用glide，要使用picasso
        Picasso.with(DetailClassActivity.this).load(bean.data.speaker_head).into(detailTeacherImg);
        detailShowNum.setText("已售：" + bean.data.volume + "");
        title1.setText(bean.data.title);
        title2.setText(bean.data.title2);
        text_content.setText(bean.data.abs);

    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(EventBean event) {
        Log.e("xxx", "event---->" + event.state);
        if (event.state.equals("end")) {
            // detailBuyLl.setVisibility(View.VISIBLE);//播放完隐藏  显示中间立即购买
            detail_tryend_tobuy.setVisibility(View.VISIBLE);
        } else {
            detailBuyLl.setVisibility(View.GONE);//正在播放时隐藏
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        JCVideoPlayer.releaseAllVideos();
    }


    @Override
    public void getHeartV(UrlBean bean) {
        super.getHeartV(bean);
        Log.i("getHeartV", bean.data.status + "");
        Log.i("getHeartV", bean.data.msg);
    }

    @OnClick(R.id.detail_tryend_tobuy)
    public void onViewClicked() {
    }
}
