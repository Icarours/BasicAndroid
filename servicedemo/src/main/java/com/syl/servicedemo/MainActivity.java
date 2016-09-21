package com.syl.servicedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.syl.servicedemo.service.DemoService;

/**
 * author   j3767
 * date     2016/9/20 23:42
 * desc
 */
public class MainActivity extends AppCompatActivity {
    boolean mFlag = false;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v(this.getClass().getSimpleName(), "onCreate方法执行,服务创建了...");
        new Thread() {
            @Override
            public void run() {
                super.run();
                mFlag = true;
                while (mFlag) {
                    String threadName = Thread.currentThread().getName();
                    Log.v(this.getClass().getSimpleName(), "Activity---检查USB接口是否接入了设备---threadName" + threadName + "---" + count++);
                }
            }
        }.start();
    }

    public void startService(View view) {
        Intent intent = new Intent(this, DemoService.class);
        startService(intent);
    }

    public void stopService(View view) {
        Intent intent = new Intent(this, DemoService.class);
        stopService(intent);
    }
}
