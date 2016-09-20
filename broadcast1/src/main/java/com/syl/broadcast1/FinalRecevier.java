package com.syl.broadcast1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by j3767 on 2016/9/20.
 *
 * @Describe
 * 最终的广播接收者不需要到清单文件中进行注册
 * @Called
 */
public class FinalRecevier extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("最终的接收者---"+getResultData());
    }
}
