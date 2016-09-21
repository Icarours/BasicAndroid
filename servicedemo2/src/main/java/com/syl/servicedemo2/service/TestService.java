package com.syl.servicedemo2.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.syl.servicedemo2.interface_.INeiXian;

/**
 * Created by j3767 on 2016/9/21.
 *
 * @Describe
 * @Called
 */
public class TestService extends Service {

    private NeiXian mNeiXian;

    /**
     * author   j3767
     * date     2016/9/21 11:14
     * desc
     * 1.内线,帮外部类调用service中方法的方法
     * 内部类必须用public修饰才能被其他的类调用
     *
     * 2.将内部类私有,如果要想对外部暴露方法可以通过特定的接口,让该内部类实现接口
     */
    private class NeiXian extends Binder implements INeiXian {
        public void callMethodInService() {
            //调用service中的方法
            methodInService();
        }
        public void eat(){
            Log.v(this.getClass().getSimpleName(),"一起去吃饭");
        }
        public void rest(){
            Log.v(this.getClass().getSimpleName(),"一起去休息...");
        }
    }

    /**
     * 当服务被绑定时会回调的方法,返回的就是那个内线,外部通过内线去调用service中的方法
     *
     * @param intent
     * @return IBinder的实现类, NeiXian继承了IBinder的实现类Binder
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        if (mNeiXian == null) {
            mNeiXian = new NeiXian();
        }
        return mNeiXian;
    }

    @Override
    public void onCreate() {
        Log.v(this.getClass().getSimpleName(), "onCreate调用了...........");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.v(this.getClass().getSimpleName(), "onDestroy调用了.....");
        super.onDestroy();
    }

    public void methodInService() {
        Log.v(this.getClass().getSimpleName(), "亲爱的,今天晚上约会吧...");
    }
}
