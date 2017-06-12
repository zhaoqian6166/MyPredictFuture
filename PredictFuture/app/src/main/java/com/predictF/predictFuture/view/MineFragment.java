package com.predictF.predictFuture.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.predictF.predictFuture.R;
import com.predictF.predictFuture.util.Tool;
import com.predictF.predictFuture.util.XCRoundImageView;
import com.predictF.predictFuture.view.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by 赵倩 on 2017/6/7.
 * <p>
 * 类的用途：
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.my_return)
    ImageView myReturn;
    @BindView(R.id.my_img)
    XCRoundImageView myImg;
    Unbinder unbinder;
    @BindView(R.id.my_login)
    LinearLayout myLogin;
    @BindView(R.id.mine_username)
    TextView mineUsername;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_my, null);
        if (Tool.getLoginState(getContext())) {
            //如果是true  表示是登录状态
        }

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.my_return, R.id.my_img, R.id.my_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_return:

                break;
            case R.id.my_img:

                break;
            case R.id.my_login:
                //点击以后跳转到登录界面
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivityForResult(intent, 100);


                break;
        }
    }
    //从登录界面传过来的登录信息
    public void setUserInfo(String userName){
        mineUsername.setText(userName+"");
        myImg.setImageResource(R.mipmap.a14);
    }


}
