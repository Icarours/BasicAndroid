package com.syl.servicelifecycle.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.syl.servicelifecycle.interface_.IXiaoMi;

/**
 * Created by j3767 on 2016/9/21.
 *
 * @Describe
 * Service的生命周期
 * @Called
 */
public class BindServiceLifeCycle extends Service {
    private class XiaoMi extends Binder implements IXiaoMi {
        @Override
        public void banZheng(String name, int money) {
            if (money < 150) {
                Toast.makeText(BindServiceLifeCycle.this, name+"您付的钱不够,我们需要按制度办事,至少150元", Toast.LENGTH_SHORT).show();
                return;
            }
            methodInService(name, money);
        }
    }

    /**
     * 绑定服务时会被调用
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.v(this.getClass().getSimpleName(), "onBind方法被调用了..");
        return new XiaoMi();
    }

    /**
     * 解绑服务时会被调用
     * @param intent
     * @return
     */
    @Override
    public boolean onUnbind(Intent intent) {
        Log.v(this.getClass().getSimpleName(), "onUnbind方法被调用了..");
        return super.onUnbind(intent);
    }

    /**
     * 服务创建时调用
     */
    @Override
    public void onCreate() {
        Log.v(this.getClass().getSimpleName(), "onCreate方法被调用了..服务创建");
        super.onCreate();
    }

    /**
     * 服务销毁时调用
     */
    @Override
    public void onDestroy() {
        Log.v(this.getClass().getSimpleName(), "onDestroy方法被调用了..服务销毁");
        super.onDestroy();
    }

    /**
     * 收到服务开启命令时调用
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(this.getClass().getSimpleName(), "onStartCommand方法被调用了..");
        return super.onStartCommand(intent, flags, startId);
    }

    public void methodInService(String name, int money) {
        Log.v(this.getClass().getSimpleName(), name + "你的证办好了...花费" + money + "元");
    }
}
