package com.syl.broadcastreceiver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DataCollections extends BroadcastReceiver {
    public DataCollections() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving

        System.out.println("用户安装或者卸载了应用程序.........");

        String action = intent.getAction();
        String packageInfo = intent.getData().toString();
        System.out.println("action=="+action+"----------包名是:"+packageInfo);
    }
}
