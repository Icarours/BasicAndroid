package com.syl.servicedemo3.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.syl.servicedemo3.interface_.IXiaoMi;

/**
 * Created by j3767 on 2016/9/21.
 *
 * @Describe
 * @Called
 */
public class BindServiceCycle extends Service{
    private class XiaoMi extends Binder implements IXiaoMi{

        @Override
        public void banZheng(String name, int money) {
            methodInService(name, money);
        }
    }
    public void methodInService(String name,int money){
        if (money<150){
            Toast.makeText(BindServiceCycle.this, name+",对不起,这点钱不够,我们必须按照制度办事..", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.v(this.getClass().getSimpleName(),name+"您的证办好了...花费金额---"+money);
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.v(this.getClass().getSimpleName(),"onBind方法被调用了...");
        return new XiaoMi();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v(this.getClass().getSimpleName(),"onUnbind方法被调用了...");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        Log.v(this.getClass().getSimpleName(),"onCreate方法被调用了...");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.v(this.getClass().getSimpleName(),"onDestroy方法被调用了...");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(this.getClass().getSimpleName(),"onStartCommand方法被调用了...");
        return super.onStartCommand(intent, flags, startId);
    }
}
