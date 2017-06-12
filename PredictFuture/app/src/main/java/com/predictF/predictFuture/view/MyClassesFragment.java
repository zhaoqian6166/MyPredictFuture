package com.predictF.predictFuture.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.predictF.predictFuture.R;
import com.predictF.predictFuture.bean.UrlBean;
import com.predictF.predictFuture.modle.base.MyAdapter;
import com.predictF.predictFuture.presenter.base.BasePresenterClass;
import com.predictF.predictFuture.view.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 赵倩 on 2017/6/2.
 * <p/>
 * 类的用途：我的课程页面
 */
public class MyClassesFragment extends BaseFragment {
    @BindView(R.id.myclass_return)
    ImageView myclassReturn;
    @BindView(R.id.myclass_recy)
    RecyclerView myclassRecy;
    Unbinder unbinder;
    private String TAG = "sy_Fragment";
    private BasePresenterClass basePresenterClass;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_myclasses, null);
        basePresenterClass = new BasePresenterClass(this, getActivity().getApplicationContext());
        unbinder = ButterKnife.bind(this,view);
        basePresenterClass.getMyClass(getActivity().getApplication());
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.myclass_return)
    public void onViewClicked() {
    }
//展示我的课程列表
    @Override
    public void showMyClasses(MyAdapter adapter) {
        super.showMyClasses(adapter);
        //展示
        LinearLayoutManager list_layout = new LinearLayoutManager(getActivity().getApplication());//listview样式，也是默认的
        myclassRecy.setLayoutManager(list_layout);
        myclassRecy.setAdapter(adapter);


    }
}
