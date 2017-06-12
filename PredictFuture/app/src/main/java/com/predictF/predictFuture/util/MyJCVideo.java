package com.predictF.predictFuture.util;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.predictF.predictFuture.bean.EventBean;

import org.greenrobot.eventbus.EventBus;

import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by 赵倩 on 2017/6/2.
 * <p/>
 * 类的用途：
 */
public class MyJCVideo extends JCVideoPlayerStandard {
    public MyJCVideo(Context context) {
        super(context);
       // EventBus.getDefault().register(this);//注册
    }

    public MyJCVideo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void setStateAndUi(int state) {
        super.setStateAndUi(state);
        switch (mCurrentState) {
            case CURRENT_STATE_NORMAL:
                //结束的时候  发送事件

                Log.i("state","CURRENT_STATE_NORMAL");
              //  changeUiToNormal();
                break;
            case CURRENT_STATE_PREPAREING:
                EventBus.getDefault().post(new EventBean("playing"));
            //    EventBus.getDefault().post("palying");
                Log.i("state","CURRENT_STATE_PREPAREING");
               // changeUiToShowUiPrepareing();
               // startDismissControlViewTimer();
                break;
            case CURRENT_STATE_PLAYING:
                Log.i("state","CURRENT_STATE_PLAYING");
              //  changeUiToShowUiPlaying();
               // startDismissControlViewTimer();
                break;
            case CURRENT_STATE_PAUSE:
                Log.i("state","CURRENT_STATE_PAUSE");
              //  changeUiToShowUiPause();
              //  cancelDismissControlViewTimer();
                break;
            case CURRENT_STATE_ERROR:
                Log.i("state","CURRENT_STATE_ERROR");
             //   changeUiToError();
                break;
        }

    }

    @Override
    public void onAutoCompletion() {
        super.onAutoCompletion();
        EventBus.getDefault().post(new EventBean("end"));
        Log.i("state","onAutoCompletion");
    }

    @Override
    public void onCompletion() {
        super.onCompletion();
        Log.i("state","onCompletion");
    }
}
