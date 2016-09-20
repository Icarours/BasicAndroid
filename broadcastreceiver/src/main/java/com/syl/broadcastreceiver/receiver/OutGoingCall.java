package com.syl.broadcastreceiver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by j3767 on 2016/9/14.
 *
 * @Describe
 * @Called
 */
public class OutGoingCall extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("接到外拨电话的广播了........");
        Log.v(this.getClass().getSimpleName(),"接到外拨电话的广播了........");

        String data = getResultData();

        System.out.println(data);
        if (data.startsWith("0")){
            setResultData("17951"+data);
        }
    }
}
