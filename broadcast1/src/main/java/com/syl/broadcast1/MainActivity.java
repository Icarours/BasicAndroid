package com.syl.broadcast1;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.syl.broadcast1.recevier.LockScreenRecevier;

/**
 * author   j3767
 * date     2016/9/20 11:07
 * desc
 * 发送无序广播
 */
public class MainActivity extends AppCompatActivity {

    private LockScreenRecevier mRecevier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //通过代码注册广播,代码中注册广播,起作用仅限于该demo.在清单文件中注册广播,广播一直都在起作用.监听频繁操作的广播应该在代码中注册
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);

        mRecevier = new LockScreenRecevier();
        registerReceiver(mRecevier,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除注册广播接收者
        unregisterReceiver(mRecevier);
        mRecevier=null;
    }

    /**
     * 无序广播
     * @param view
     */
    public void send(View view){
        System.out.println("发送无序广播...");
        Log.v(this.getClass().getSimpleName(),"发送无序广播...");
        Intent intent = new Intent();
        intent.setAction("com.syl.XWLB");
        sendBroadcast(intent);
    }

    /**
     * 有序广播
     * @param view
     */
    public void sendRice(View view){
        Intent intent = new Intent();
        intent.setAction("com.syl.SENDRICE");
        String data = "过年发福利,每人发10斤大米";
        System.out.println(data);
        sendOrderedBroadcast(intent,null,new FinalRecevier(),null,0,data,null);
    }
}

