package com.predictF.predictFuture.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.predictF.predictFuture.R;
import com.predictF.predictFuture.util.XCRoundImageView;
import com.predictF.predictFuture.view.main.BaseActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 赵倩 on 2017/5/26.
 * <p/>
 * 类的用途：
 */
public class Try extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_main);
        String url="https://pretty.f8cam.com/static/image/product/tu/tuauimijnc.jpg";
        TextView title2= (TextView) findViewById(R.id.detail_title2);
        title2.setMovementMethod(ScrollingMovementMethod.getInstance());
      /*  unbinder = ButterKnife.bind(this);
        Picasso.with(this).load(url).into(img);*/
    }


   /* @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }*/
}
