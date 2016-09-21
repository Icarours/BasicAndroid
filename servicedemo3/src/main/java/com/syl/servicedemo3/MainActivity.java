package com.syl.servicedemo3;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.syl.servicedemo3.interface_.IXiaoMi;
import com.syl.servicedemo3.service.BindServiceCycle;
/**
 * author   j3767
 * date     2016/9/21 16:05
 * desc
 * 混合开启服务
 * startService 开启之后一直在后台运行
 * bindService 可以调用服务中的方法
 * 混合服务结合了两种开启服务的优点.
 *
 * 混合服务要严格按照按钮所示顺序执行,否则会出一些莫名其妙的异常
 */
public class MainActivity extends AppCompatActivity {

    private boolean mIsBindService;
    private MyServiceConnection mConn;
    private IXiaoMi mIXiaoMi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
//        if (mIsBindService) {
//            unbindService(mConn);
//            mConn = null;
//        }
        super.onDestroy();
    }

    /**
     * 开启服务
     * @param view
     */
    public void start(View view) {
        Intent intent = new Intent(this, BindServiceCycle.class);
        startService(intent);
    }

    /**
     * 绑定服务
     * @param view
     */
    public void bind(View view) {
        Intent intent = new Intent(this, BindServiceCycle.class);
        mConn = new MyServiceConnection();
        mIsBindService = bindService(intent, mConn, BIND_AUTO_CREATE);
    }

    /**
     * 调用服务中的方法
     * @param view
     */
    public void call(View view) {
        mIXiaoMi.banZheng("周四", 160);
    }

    /**
     * 解绑服务
     * @param view
     */
    public void unbind(View view) {
        unbindService(mConn);
        mConn = null;
    }

    /**
     * 停止服务
     * @param view
     */
    public void stop(View view) {
        Intent intent = new Intent(this, BindServiceCycle.class);
        stopService(intent);
    }

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
