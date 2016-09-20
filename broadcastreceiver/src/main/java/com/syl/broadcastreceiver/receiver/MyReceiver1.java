package com.syl.broadcastreceiver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by j3767 on 2016/9/20.
 *
 * @Describe
 * @Called
 */
public class MyReceiver1 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v(this.getClass().getSimpleName(),"接收到了broadcast发送的无序广播");
    }
}
