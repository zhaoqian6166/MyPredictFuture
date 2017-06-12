package com.predictF.predictFuture.presenter.base;

import android.content.Context;

import com.predictF.predictFuture.bean.UrlBean;
import com.predictF.predictFuture.modle.base.BaseModleClass;
import com.predictF.predictFuture.modle.base.MyAdapter;
import com.predictF.predictFuture.util.Tool;
import com.predictF.predictFuture.view.base.IBaseView;

import java.util.ArrayList;

/**
 * Created by 赵倩 on 2017/5/27.
 * <p>
 * 类的用途：
 */
public class BasePresenterClass implements IBasePresenter {
    private IBaseView iBaseView;
    private Context context;
    private BaseModleClass bmc;

    public BasePresenterClass(IBaseView iBaseView, Context context) {
        this.iBaseView = iBaseView;
        this.context = context;
        this.bmc=new BaseModleClass(this,context);
    }
    public void getBanner(){
        String newUrl = Tool.getNewUrl(context);
         bmc.getBanner(newUrl);
    }
    public void getBest(Context context){
        String newUrl = Tool.getNewUrl(context);
        bmc.getBest(newUrl,context);
    }
    public void getMyClass(Context context){
        String newUrl = Tool.getNewUrl(context);
        bmc.getMyClasses(newUrl);
    }

    @Override
    public void getBanner(UrlBean bean) {
        iBaseView.showBanner(bean);
    }

    @Override
    public void getBest(UrlBean bean,MyAdapter adapter) {
        iBaseView.showBest(bean,adapter);

    }

    @Override
    public void getMyClasses(MyAdapter adapter) {
        //传到view层
        iBaseView.showMyClasses(adapter);
    }


}
