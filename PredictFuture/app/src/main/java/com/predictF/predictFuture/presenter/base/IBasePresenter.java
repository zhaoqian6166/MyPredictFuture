package com.predictF.predictFuture.presenter.base;

import com.predictF.predictFuture.bean.UrlBean;
import com.predictF.predictFuture.modle.base.MyAdapter;

/**
 * Created by 赵倩 on 2017/5/27.
 * <p>
 * 类的用途：
 */
public interface IBasePresenter {
    void getBanner(UrlBean bean);
    void getBest(UrlBean bean,MyAdapter adapter);
   // void getAdapter(MyAdapter adapter);
    void getMyClasses(MyAdapter adapter);

}
