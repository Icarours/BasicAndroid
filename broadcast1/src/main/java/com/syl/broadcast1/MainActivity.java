package com.syl.broadcast1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
/**
 * author   j3767
 * date     2016/9/20 11:07
 * desc
 * 发送无序广播
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

