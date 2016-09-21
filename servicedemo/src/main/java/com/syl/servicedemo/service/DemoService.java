package com.syl.servicedemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by j3767 on 2016/9/21.
 *
 * @Describe
 * service是运行在主线程中的,如果service中出现了耗时的操作就会出现ANR
 * @Called
 */
public class DemoService extends Service {

    private boolean mFlag = false;
    int count;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(this.getClass().getSimpleName(),"onCreate方法执行,服务创建了...");
//        for (int i = 0; i < 100; i++) {
//            String threadName = Thread.currentThread().getName();
//            System.out.println("threadName"+threadName+"-----检查USB接口是否插入了设备  i = " + i);
//        }
        new Thread(){
            @Override
            public void run() {
                super.run();
                mFlag = true;
                while (mFlag){
                    String threadName = Thread.currentThread().getName();
                    Log.v(this.getClass().getSimpleName(),"检查USB接口是否接入了设备---threadName"+threadName+"---"+count++);
                }
            }
        }.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFlag = false;
        Log.v(this.getClass().getSimpleName(),"onDestroy方法执行,服务销毁了..."+count);
    }
}
