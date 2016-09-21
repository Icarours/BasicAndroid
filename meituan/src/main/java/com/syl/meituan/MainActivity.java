package com.syl.meituan;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.syl.alipay.aidl.IPayNeiXian;

/**
 * author   j3767
 * date     2016/9/21 20:14
 * desc
 * 使用支付宝AliPay提供的远程服务完成支付
 */
public class MainActivity extends AppCompatActivity {
    IPayNeiXian mIPayNeiXian;
    private AliPayServiceConnection mConn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bind(View view) {
        //com.aliPay.PAYSERVICE
        Intent intent = new Intent();
        intent.setAction("com.aliPay.PAYSERVICE");
        mConn = new AliPayServiceConnection();
        bindService(intent, mConn, BIND_AUTO_CREATE);
    }

    public void unBind(View view) {
        unbindService(mConn);
        mConn = null;
    }

    public void call(View view) {
        try {
            mIPayNeiXian.callPay("张三京东", (long) 120.1);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * author   j3767
     * date     2016/9/21 19:03
     * desc
     * 通讯频道创建之后该类的方法就会被回调
     */
    class AliPayServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            mIPayNeiXian = (IPayNeiXian) service;//以前用这种方法
            //使用aidl技术可以直接使用以下方法
            mIPayNeiXian = IPayNeiXian.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
