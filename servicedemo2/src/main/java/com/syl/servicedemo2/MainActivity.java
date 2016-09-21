package com.syl.servicedemo2;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import com.syl.servicedemo2.interface_.INeiXian;
import com.syl.servicedemo2.service.TestService;

/**
 * author   j3767
 * date     2016/9/21 10:16
 * desc
 * Activity 去调用service中的方法,不能直接去调用.通常的new出一个类的实例,
 * 然后再用实例对象去调用的方法也不行,四大组件只能继承,不能直接new.创建四大
 * 组件是系统帮我们做的.
 */
public class MainActivity extends AppCompatActivity {

    INeiXian mNeiXian;//面向接口编程,接口是对类的功能的扩展

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 开启服务
     * 开启服务并不能调用服务中的方法,必须使用绑定绑定服务才可以调用服务中的方法
     *
     * @param view
     */
    public void startService(View view) {
        Intent intent = new Intent(this, TestService.class);
//        startService(intent);
        /**
         * Activity通过Service中IBinder实现类或者其子类来调用Service中的方法
         */
        MyConnection conn = new MyConnection();
        //BIND_AUTO_CREATE  绑定服务的同时自动创建服务.
        bindService(intent, conn, BIND_AUTO_CREATE);
    }

    /**
     * author   j3767
     * date     2016/9/21 11:02
     * desc
     * 创建serviceConnection
     */
    class MyConnection implements ServiceConnection {

        /**
         * 当通讯建立的时候
         *
         * @param name
         * @param service
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mNeiXian = (INeiXian) service;//只使用INeiXian接口中的方法
        }

        /**
         * 当通讯中断的时候
         *
         * @param name
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    /**
     * 关闭服务
     *
     * @param view
     */
    public void stopService(View view) {
        Intent intent = new Intent(this, TestService.class);
        stopService(intent);
    }

    /**
     * 调用服务中的方法
     *
     * @param view
     */
    public void callMethodInService(View view) {
        mNeiXian.callMethodInService();//只使用INeiXian接口中的方法
    }
}
