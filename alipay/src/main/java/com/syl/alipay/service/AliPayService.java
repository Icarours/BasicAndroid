package com.syl.alipay.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.syl.alipay.aidl.IPayNeiXian;


/**
 * Created by j3767 on 2016/9/21.
 *
 * @Describe
 * 使用aidl技术实现进程间通信
 * 1.在支付宝里面创建一个aidl文件,aidl文件类似于接口文件,但是接口名和方法名都没有修饰符
 * 2.将该aidl文件放到需要使用它的app下,注意,必须将该aidl文件放到与原来的包名相同的包下
 * @Called
 */
public class AliPayService extends Service {
    private class PayNeiXian extends IPayNeiXian.Stub{

        @Override
        public void callPay(String merchantId, long money) {
            pay(merchantId, money);
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new PayNeiXian();
    }
    public void pay(String merchantId,long money){
        Log.v(this.getClass().getSimpleName(),merchantId+"---商家收到,货款---"+money);
    }
}

