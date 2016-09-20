package com.syl.broadcastreceiver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        System.out.println("接到外拨电话的广播了........");
        Log.v(this.getClass().getSimpleName(), "接到外拨电话的广播了........");

        String data = getResultData();

        System.out.println(data);
        if (data.startsWith("0")) {
            setResultData("17951" + data);
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
