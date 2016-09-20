package com.syl.broadcast1.recevier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by j3767 on 2016/9/20.
 *
 * @Describe
 * 监听屏幕的关闭和点亮
 * @Called
 */
public class LockScreenRecevier extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("屏幕点亮了或者关闭了...");
    }
}
