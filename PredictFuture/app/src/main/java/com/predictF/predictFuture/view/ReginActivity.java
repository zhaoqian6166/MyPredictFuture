package com.predictF.predictFuture.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.predictF.predictFuture.R;
import com.predictF.predictFuture.bean.UrlBean;
import com.predictF.predictFuture.presenter.main.PresenterClass;
import com.predictF.predictFuture.view.main.IBaseClass;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 赵倩 on 2017/5/28.
 * <p/>
 * 类的用途：注册页面
 */
public class ReginActivity extends IBaseClass {

    @BindView(R.id.regin_return)
    ImageView reginReturn;
    @BindView(R.id.regin_img)
    ImageView reginImg;
    @BindView(R.id.regin_tel)
    EditText reginTel;
    @BindView(R.id.regin_Editcheck)
    EditText reginEditcheck;
    @BindView(R.id.regin_getCheckNum)
    TextView reginGetCheckNum;
    @BindView(R.id.regin_pwd)
    EditText reginPwd;
    @BindView(R.id.regin_noShowPwd)
    ImageView reginNoShowPwd;
    @BindView(R.id.regin_regin)
    Button reginRegin;
    @BindView(R.id.regin_agree)
    CheckBox reginAgree;
    private PresenterClass presenterClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regin_main);
        ButterKnife.bind(this);
        //得到P层的对象
        presenterClass = new PresenterClass(getApplicationContext(), this);
    }


    //注册

    @Override
    public void userRegin(UrlBean bean) {
        super.userRegin(bean);
        Log.d("tag","注册成功");
        Log.d("tag",bean.data.session);
    }

    //点击注册按钮
    @OnClick(R.id.regin_regin)
    public void onViewClicked() {
        String s = reginTel.getText().toString();//获得手机号
        String pwd = reginPwd.getText().toString();//获得密码
        String rand=reginEditcheck.getText().toString();//获取验证码
        if (!TextUtils.isEmpty(s)){//判断不为空
            presenterClass.getRegin(s,pwd,rand);

        }

    }
}
