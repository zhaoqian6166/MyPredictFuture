package com.predictF.predictFuture.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.predictF.predictFuture.R;
import com.predictF.predictFuture.bean.UrlBean;
import com.predictF.predictFuture.presenter.main.PresenterClass;
import com.predictF.predictFuture.util.Tool;
import com.predictF.predictFuture.view.main.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 赵倩 on 2017/5/28.
 * <p/>
 * 类的用途：登录页面
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.login_return)
    ImageView loginReturn;
    @BindView(R.id.login_img)
    ImageView loginImg;
    @BindView(R.id.login_tel)
    EditText loginTel;
    @BindView(R.id.login_pwd)
    EditText loginPwd;
    @BindView(R.id.regin_noShowPwd)
    ImageView reginNoShowPwd;
    @BindView(R.id.login_forgetPwd)
    TextView loginForgetPwd;
    @BindView(R.id.login_login)
    Button loginLogin;
    @BindView(R.id.login_regin)
    Button loginRegin;
    @BindView(R.id.login_weChat)
    TextView loginWeChat;
    @BindView(R.id.login_QQ)
    TextView loginQQ;
    private PresenterClass presenterClass;
    private Intent intent;
    private String tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        ButterKnife.bind(this);
        presenterClass = new PresenterClass(getApplicationContext(), this);
        intent = new Intent();


    }
//点击事件
    @OnClick({R.id.login_return, R.id.login_login, R.id.login_regin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_return:
                break;
            case R.id.login_login:
                tel = loginTel.getText().toString();
                String pwd = loginPwd.getText().toString();
                presenterClass.getPwdLogin(tel,pwd);
                //点击登录按钮后登录交互
                break;
            case R.id.login_regin:
                Intent intent=new Intent(LoginActivity.this,ReginActivity.class);
                startActivityForResult(intent,200);

                break;
        }
    }
//登录成功
    @Override
    public void userPwdLogin(UrlBean bean) {
        super.userPwdLogin(bean);

        Log.d("login",bean.data.session);

        switch (bean.ret){
            case 0:
                //存储session
                SharedPreferences spf = Tool.getSpf(getApplicationContext());
                SharedPreferences.Editor editor = spf.edit();
                editor.putString("session", bean.data.session);
                editor.putBoolean("loginState", true);
                editor.commit();
                intent.putExtra("userName",tel+"");
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                LoginActivity.this.setResult(101,intent);
                finish();
                break;
            case -5:
                Toast.makeText(LoginActivity.this, "手机号未注册", Toast.LENGTH_SHORT).show();
                break;
            case -7:
                Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                break;
            case -9:
                Toast.makeText(LoginActivity.this, "连续密码错误5次，账号锁定15分钟", Toast.LENGTH_SHORT).show();
                break;
            case -10:
                Toast.makeText(LoginActivity.this, "手机号码格式错误", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==200&&resultCode==201){
            String userName = data.getStringExtra("userName");
            String userPwd = data.getStringExtra("userPwd");
            Log.d("aaa",userName+"--"+userPwd);
            loginTel.setText(userName);
            loginPwd.setText(userPwd);

        }

    }
}
