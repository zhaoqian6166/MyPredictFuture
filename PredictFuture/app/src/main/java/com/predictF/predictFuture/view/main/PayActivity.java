package com.predictF.predictFuture.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.predictF.predictFuture.R;
import com.predictF.predictFuture.bean.OrderBean;
import com.predictF.predictFuture.bean.PayStyle;
import com.predictF.predictFuture.util.Tool;
import com.predictF.predictFuture.util.pay.PayResult;
import com.predictF.predictFuture.util.pay.PayUtils;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 赵倩 on 2017/6/11.
 * <p/>
 * 类的用途：
 */


public class PayActivity extends BaseActivity {
    @BindView(R.id.pay_return)
    ImageView payReturn;
    @BindView(R.id.pay_price)
    TextView payPrice;
    @BindView(R.id.pay_wechat)
    LinearLayout payWechat;
    @BindView(R.id.pay_aliay)
    LinearLayout payAliay;
    @BindView(R.id.pay_ok)
    Button payOk;
    @BindView(R.id.pay_appleCb)
    CheckBox appleCb;
    @BindView(R.id.pay_yuECb)
    CheckBox yuECb;

    @BindView(R.id.pay_aliayCb)
    CheckBox aliayCb;
    @BindView(R.id.pay_wechatCb)
    CheckBox wechatCb;
    @BindView(R.id.pay_applell)
    LinearLayout payApplell;
    @BindView(R.id.pay_yuEll)
    LinearLayout payYuEll;

    private Map<String, Integer> map;
    private ArrayList<Boolean> list;
    private ArrayList<CheckBox> listCheck;
    private ArrayList<PayStyle> payStyles;
    //---------------------------------------------------
    public static final String PARTNER = "2088901305856832";
    // 商户收款账号
    public static final String SELLER = "8@qdbaiu.com";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAM" +
            "/KCxg/OIj6er2GEig0DOkHqBSzEPHGigMbTXP1k2nrxEHeE59xOOuy" +
            "ovQH/A1LgbuVKyOac3uAN4GXIBEoozRVzDhu5dobeNm48BPcpYSAfvN3K" +
            "/5GLacvJeENqsiBx8KufM/9inlHaDRQV7WhX1Oe2ckat1EkdHwxxQgc" +
            "36NhAgMBAAECgYEAwn3sWpq6cUR65LD8h9MIjopTImTlpFjgz72bhsHD" +
            "ZK6A+eJDXcddrwh7DI34t/0IBqu+QEoOc/f0fIEXS9hMwTvFY59XG7M8" +
            "M6SdeaAbemrGmZ1IdD6YDmpbQFHn2ishaYF0YDZIkBS3WLDFrtk/efaar" +
            "BCpGAVXeEiVQE4LewECQQD5W1rpkq+dHDRzzdtdi1bJ479wun5CfmVDV" +
            "b2CJH7Iz0t8zyp/iEVV2QELnxZMphmoSzKaLXutTTj2OImpzCtRAkEA1" +
            "VMxG6nQq9NkU51H1+I3mlUXRZ0XeFA1GFJ7xWpNRAVhEWlDz2zy9v/g" +
            "X+RFpNC3f5uznycas70Xp78ew43TEQJAZFFqi9mlqTF1sLk67bFnIyX" +
            "rGPEOZrXvC13tNfR0xVkQZ4/46wHp0xXQo9pG4GNaoyhNnVV7EkelCPn" +
            "J+HPZYQJAQh6T9QgQZoGR8hyovPAf3dUL7oa/VIo/urcuJ8VIB5JHQNdI" +
            "rk0NjaNHj1E4iNosVgATj3vWWel9IIArb99QkQJAKvfm78lwnImtg5IM6" +
            "04hdn/Wu1XF8tpxsKLWcnfchMr0bM9rCmKmhAY+wdmqSyPZRiNb1QaaaD" +
            "TqJxLy6AnQ+Q==";
    // 支付宝公钥
    public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCd6rV3vOE578e6V" +
            "lGEakZpPdsX2QmGdIfi/yHe cg1CIEWzX9wn2LNFGtu1EzYQyKACG/RKeog0pUJEVGfBG30zFdNY2YocYJNdPtA" +
            "DqhJbS0GJm7f8 1vRiLKtOwKjdiz9oMEwxhc/5fysfMbercidRmlCDPU9BNL1UPb9bAx25JwIDAQAB";
    private static final int SDK_PAY_FLAG = 1;
    //阿里云服务器，用真机测试
    private static final String URL_JSON = "http://101.200.142.201:8080/alipayServer/AlipayDemo";
    private OrderBean order;

    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(PayActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_main);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String price = intent.getStringExtra("price");
        payPrice.setText("￥：" + price);//设置金额
        payStyles = new ArrayList<PayStyle>();
        payStyles.add(new PayStyle(false, appleCb, payApplell));
        payStyles.add(new PayStyle(false, yuECb, payYuEll));
        payStyles.add(new PayStyle(false, wechatCb, payWechat));
        payStyles.add(new PayStyle(false, aliayCb, payAliay));

    }

    @OnClick({R.id.pay_wechat, R.id.pay_aliay, R.id.pay_ok,R.id.pay_yuECb,R.id.pay_appleCb,R.id.pay_applell,R.id.pay_yuEll,R.id.pay_aliayCb,R.id.pay_wechatCb})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.pay_appleCb:
                ArrayList<PayStyle> payStyles1 = Tool.setChecked(this.payStyles, 0);
                for (int i=0;i<payStyles1.size();i++) {
                    Log.i("sss",payStyles1.get(i).isCheck+"");
                    payStyles1.get(i).cb.setChecked(payStyles1.get(i).isCheck);
                }
                break;
            case R.id.pay_applell:
                ArrayList<PayStyle> payStyles2 = Tool.setChecked(this.payStyles, 0);
                for (int i=0;i<payStyles2.size();i++) {
                    Log.i("sss",payStyles2.get(i).isCheck+"");
                    payStyles2.get(i).cb.setChecked(payStyles2.get(i).isCheck);
                }
                break;
            case R.id.pay_yuECb:
                ArrayList<PayStyle> payStyles3 = Tool.setChecked(this.payStyles, 1);
                for (int i=0;i<payStyles3.size();i++) {
                    Log.i("sss",payStyles3.get(i).isCheck+"");
                    payStyles3.get(i).cb.setChecked(payStyles3.get(i).isCheck);
                }
                break;
            case R.id.pay_yuEll:
                ArrayList<PayStyle> payStyles4 = Tool.setChecked(this.payStyles, 1);
                for (int i=0;i<payStyles4.size();i++) {
                    Log.i("sss",payStyles4.get(i).isCheck+"");
                    payStyles4.get(i).cb.setChecked(payStyles4.get(i).isCheck);
            }
                break;
            case R.id.pay_wechat:
                ArrayList<PayStyle> payStyles5 = Tool.setChecked(this.payStyles, 2);
                for (int i=0;i<payStyles5.size();i++) {
                    Log.i("sss",payStyles5.get(i).isCheck+"");
                    payStyles5.get(i).cb.setChecked(payStyles5.get(i).isCheck);
                }

                break;
            case R.id.pay_wechatCb:
                ArrayList<PayStyle> payStyles6 = Tool.setChecked(this.payStyles, 2);
                for (int i=0;i<payStyles6.size();i++) {
                    Log.i("sss",payStyles6.get(i).isCheck+"");
                    payStyles6.get(i).cb.setChecked(payStyles6.get(i).isCheck);
                }
                break;
            case R.id.pay_aliay:
                ArrayList<PayStyle> payStyles7 = Tool.setChecked(this.payStyles, 3);
                for (int i=0;i<payStyles7.size();i++) {
                    Log.i("sss",payStyles7.get(i).isCheck+"");
                    payStyles7.get(i).cb.setChecked(payStyles7.get(i).isCheck);
                }
                break;
            case R.id.pay_aliayCb:
                ArrayList<PayStyle> payStyles = Tool.setChecked(this.payStyles, 3);
                for (int i=0;i<payStyles.size();i++) {
                    Log.i("sss",payStyles.get(i).isCheck+"");
                    payStyles.get(i).cb.setChecked(payStyles.get(i).isCheck);
                }
                break;


            case R.id.pay_ok:
                // 点击确认后   查找是哪种支付方式  去支付  默认直接去支付宝支付
                alipay();

                break;
        }
    }

    //------本地支付--------
    private void alipay() {
        //支付信息  暂时写为固定的   实际项目开发中  从order得到相关订单信息
        String orderinfo = PayUtils.getOrderInfo("测试商品", "购买一部手机", "0.01");
        String signinfo = PayUtils.getSignInfo(orderinfo);
        final String totalInfo = PayUtils.getTotalInfo(orderinfo, signinfo);

        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(PayActivity.this);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(totalInfo, true);
                Log.i("TAG", "走了pay支付方法.............");
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }
    //与后台交互   得到预支付结果信息  后台加密
    /*private void paySignFromServer() {
        //添加参数，暂时写死，项目中，从页面获取
        String url ="http://169.254.249.24:8080/PayServer/AlipayDemo";
        StringBuffer sb = new StringBuffer("?");
        sb.append("subject=");
        sb.append(order.getMsg());
        sb.append("&");
        sb.append("body=");
        sb.append("该测试商品的详细描述");
        sb.append("&");
        sb.append("total_fee=");
        sb.append(order.getData().getAmount());
        url =	url + sb.toString();
        //到服务器进行订单加密
        HttpUtils http = new HttpUtils();

        http.send(HttpRequest.HttpMethod.POST, url, new RequestCallBack<String>() {
            @Override
            public void onLoading(long total, long current, boolean isUploading)
            {
                // super.onLoading(total, current, isUploading);
                Log.i("TAG", "加载过程...............");
            }

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                // TODO Auto-generated method stub
                Log.i("TAG", "失败............");
            }
            @Override
            public void onSuccess(ResponseInfo<String> arg0) {
                // TODO Auto-generated method stub
                final String signResult =arg0.result;
                Log.i("TAG", signResult);

                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        // 构造PayTask 对象
                        PayTask alipay = new PayTask(PayActivity.this);
                        // 调用支付接口，获取支付结果
                        String result = alipay.pay(signResult, true);
                        Log.i("TAG", "走了pay支付方法.............");

                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };

                Thread payThread = new Thread(payRunnable);
                payThread.start();
            }
        });
    }*/
}
