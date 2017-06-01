package com.predictF.predictFuture.view.base;

import com.predictF.predictFuture.bean.UrlBean;
import com.predictF.predictFuture.modle.base.MyAdapter;

/**
 * Created by 赵倩 on 2017/5/27.
 * <p>
 * 类的用途：
 */
public interface IBaseView {
    void showBanner(UrlBean bean);
    void showBest(UrlBean bean,MyAdapter adapter);
}
