package com.syl.intentdemo1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/**
 * author   j3767
 * date     2016/9/7 23:28
 * desc
 * 隐式意图:
 * 1.寻找需要启动的Activity的清单文件,选中其中的一个意图,在代码中添加
 * 2.action和category都要添加到代码中,data只需要添加一个
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnMsg = (Button) findViewById(R.id.btn_msg);
        TextView tvBrowser = (TextView) findViewById(R.id.tv_browser);
        /**
         * 打开浏览器
         */
        tvBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*                intent-filter>
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE" />
                <data android:scheme="http" />
                <data android:scheme="https" />
                <data android:scheme="about" />
                <data android:scheme="javascript" />
                </intent-filter>*/
                //隐式意图,找到Android系统内置的intent-filter,将其在代码中进行配置,就可以访问相应的Android系统程序
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.addCategory("android.intent.category.BROWSABLE");
                intent.setData(Uri.parse("http://192.168.2.205:8080/"));
                startActivity(intent);
            }
        });
    }

    /**
     * 一键分享
     * @param view
     */
    public void share(View view){
/*        <intent-filter>
        <action android:name="android.intent.action.VIEW" />
        <action android:name="android.intent.action.SENDTO" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />
        <data android:scheme="sms" />
        <data android:scheme="smsto" />
        </intent-filter>*/
        //隐式意图
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setAction("android.intent.action.SENDTO");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setData(Uri.parse("smsto:10086"));

        intent.putExtra("sms_body","亲爱的!!!!!!!!...http://192.168.2.205:8080/");
        startActivity(intent);
    }
}
