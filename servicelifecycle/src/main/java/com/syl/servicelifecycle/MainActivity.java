package com.syl.servicelifecycle;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.syl.servicelifecycle.interface_.IXiaoMi;
import com.syl.servicelifecycle.service.BindServiceLifeCycle;

public class MainActivity extends AppCompatActivity {
    IXiaoMi mIXiaoMi;
    private MyServiceConnection mConn;
    private boolean mIsBindService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Activity销毁时解绑服务,否则会造成内存泄露
     */
    @Override
    protected void onDestroy() {
        if (mIsBindService) {
            unbindService(mConn);
            mConn = null;
        }
        super.onDestroy();
    }

    /**
     * 绑定服务
     *
     * @param view
     */
    public void bind(View view) {
        Intent intent = new Intent(this, BindServiceLifeCycle.class);
        mConn = new MyServiceConnection();
        mIsBindService = bindService(intent, mConn, BIND_AUTO_CREATE);
    }

    /**
     * 解绑服务
     *
     * @param view
     */
    public void unbind(View view) {
        unbindService(mConn);
        mConn = null;
    }

    public void call(View view) {
        mIXiaoMi.banZheng("张三", 160);
    }

    /**
     * 通讯频道
     * 创建MyServiceConnection实现ServiceConnection
     */
    class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIXiaoMi = (IXiaoMi) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}

