package com.predictF.predictFuture.bean;

import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by 赵倩 on 2017/6/11.
 * <p>
 * 类的用途：
 */
public class PayStyle {

    public boolean isCheck;
    public CheckBox cb;
    public LinearLayout ll;

    public PayStyle(boolean isCheck,CheckBox cb, LinearLayout ll) {
        this.isCheck = isCheck;
        this.cb=cb;
        this.ll=ll;
    }
}
