package com.syl.broadcastreceiver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by j3767 on 2016/9/20.
 *
 * @Describe
 * @Called
 */
public class ShengRecevier extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("省级政府收到了---"+getResultData());
        setResultData("每人发5斤大米");//修改广播内容
//        abortBroadcast();//终止广播
    }
}
