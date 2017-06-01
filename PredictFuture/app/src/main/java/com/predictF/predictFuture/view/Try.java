package com.predictF.predictFuture.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.predictF.predictFuture.R;
import com.predictF.predictFuture.bean.FirstHandBean;
import com.predictF.predictFuture.bean.FirstHandErrorBean;
import com.predictF.predictFuture.bean.UrlBean;
import com.predictF.predictFuture.presenter.main.PresenterClass;
import com.predictF.predictFuture.util.Image;
import com.predictF.predictFuture.view.main.Iview;
import com.youth.banner.Banner;

import java.util.ArrayList;

/**
 * Created by 赵倩 on 2017/5/26.
 * <p/>
 * 类的用途：
 */
public class Try extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_main, null);

        return view;
    }



}
