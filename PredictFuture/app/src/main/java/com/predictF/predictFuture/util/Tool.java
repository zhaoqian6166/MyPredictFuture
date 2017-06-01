package com.predictF.predictFuture.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 赵倩 on 2017/5/26.
 * <p>
 * 类的用途：
 */
public class Tool {
    //日志拦截器+Retrofit
    private String TAG = "tool";

    public static IRetrofitService getNetData(String url) {
        //打印retrofit日志
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(final String message) {
                //打印retrofit日志
                Log.i("tool", "url = " + message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //创建OkHttpClien
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        IRetrofitService retrofitService = retrofit.create(IRetrofitService.class);
        return retrofitService;
    }

    //获得设备ID  是设备的唯一标识
    public static String getDevId(Context context) {
        TelephonyManager TelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String szImei = TelephonyMgr.getDeviceId();
        return szImei;
    }

    //获得版本号
    public static int getVersionCode(Context context) {
        PackageManager manager = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        int versionCode = info.versionCode;
        return versionCode;

    }

    //获得时间戳
    public static String getTick() {
        long time = System.currentTimeMillis() / 1000;//获取系统时间的10位的时间戳
        String str = String.valueOf(time);
        return str;

    }

    //MD5加密算法
    public static String md5(String string) {
        //便利map集合  得到value，拼接参数，加密运算

        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    //获取最后的签名字段
    public static String getSign(HashMap<String, String> map,Context context) {
        StringBuffer sb = new StringBuffer(getP_key(context));
        for (String s : map.keySet()) {
            //map.keySet()返回的是所有key的值
            sb.append(map.get(s));
            System.out.println(sb + "     " + sb);
        }
        String string= sb.toString();
        Log.d("sign",string);
        // String s=key+Api.TYPE+getDevId(context)+getVersionCode(context)+getTick();
        String sign = md5(string).toUpperCase();//md5加密算法后变为大写  获得最后的签名
        Log.d("tool", sign);
        return sign;

    }
    public static String getSign(String string) {
      /*  StringBuffer sb = new StringBuffer();
        for (String s : map.keySet()) {
            //map.keySet()返回的是所有key的值
            sb.append(map.get(s));
            System.out.println(sb + "     " + sb);
        }
        String string= sb.toString();*/
        // String s=key+Api.TYPE+getDevId(context)+getVersionCode(context)+getTick();
        String sign = md5(string).toUpperCase();//md5加密算法后变为大写  获得最后的签名
        Log.d("tool", sign);
        return sign;

    }

    //获得SharedPrefrence
    public static SharedPreferences getSpf(Context context) {
        SharedPreferences spf = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return spf;

    }

    //获得appid
    public static String getAppid(Context context) {
        String app_id = getSpf(context).getString("app_id", "");
        return app_id;
    }

    //获得private_key
    public static String getP_key(Context context) {
        String private_key = getSpf(context).getString("private_key", "");
        return private_key;
    }

    //获得新的url
    public static String getNewUrl(Context context) {
        SharedPreferences spf = getSpf(context);
        String url_host = spf.getString("url_host", "");
        return url_host;

    }

    public static HashMap<String, String> getMap(Context context) {
        HashMap<String, String> map = new HashMap<String, String>();
       // map.put("private_key", getP_key(context));
        map.put("app_id", Tool.getAppid(context));
        map.put("dev_id", Tool.getDevId(context));
        map.put("ver_code", Tool.getVersionCode(context) + "");
        map.put("tick", Tool.getTick());
        return map;
    }


}
