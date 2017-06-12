package com.predictF.predictFuture.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.predictF.predictFuture.R;
import com.predictF.predictFuture.bean.UrlBean;
import com.predictF.predictFuture.modle.base.MyAdapter;
import com.predictF.predictFuture.presenter.base.BasePresenterClass;
import com.predictF.predictFuture.util.Image;
import com.predictF.predictFuture.view.base.BaseFragment;
import com.youth.banner.Banner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by 赵倩 on 2017/5/26.
 * <p/>
 * 类的用途：
 */
public class SY_Fragment extends BaseFragment {
    @BindView(R.id.sy_banner)
    Banner syBanner;
    @BindView(R.id.sy_freeRecycle)
    RecyclerView syFreeRecycle;
    @BindView(R.id.sy_chooseRecycle)
    RecyclerView syChooseRecycle;
    @BindView(R.id.sy_zhuanjiRecycle)
    RecyclerView syZhuanjiRecycle;
    Unbinder unbinder;
    private String TAG="sy_Fragment";
    private BasePresenterClass basePresenterClass;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_shouye, null);
        unbinder = ButterKnife.bind(this, view);
        basePresenterClass = new BasePresenterClass(this, getActivity().getApplicationContext());
        basePresenterClass.getBanner();
        basePresenterClass.getBest(getActivity().getApplicationContext());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showBanner(UrlBean bean) {
        ArrayList<UrlBean.BannerBean> banner = bean.data.banner;
        ArrayList<String> listImage = new ArrayList<String>();
        for (int i = 0; i < banner.size(); i++) {
            listImage.add(banner.get(i).image);
            Log.i(TAG, banner.get(i).click);
        }
        Log.d(TAG, listImage.size() + "image");
        syBanner.setImageLoader(new Image());
        syBanner.setImages(listImage);
        syBanner.start();
    }

    @Override
    public void showBest(UrlBean bean,MyAdapter adapter) {
        ArrayList<UrlBean.Try> trys = bean.data.list_try;
        Log.i("sy_Fragment",trys.size()+"");
        LinearLayoutManager list_layout = new LinearLayoutManager(getActivity().getApplication());//listview样式，也是默认的
        syChooseRecycle.setLayoutManager(list_layout);
        syChooseRecycle.setAdapter(adapter);
        adapter.setOnItemClickListner(new MyAdapter.ItemClick() {
            @Override
            public void onItemClick(String object_id) {
                //得到了id  开始请求详情界面数据
                Log.i("sy_Fragment",object_id+"-object_id");
                Intent intent=new Intent(getActivity(),DetailClassActivity.class);
              intent.putExtra("object_id",object_id);
                getContext().startActivity(intent);
            }
        });
    }


    public void viewClick(){

    }

}
