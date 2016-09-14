package com.syl.basicandroid;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.syl.basicandroid.utils.StringTool;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * author   j3767
 * date     2016/8/28 15:43
 * desc
 * httpUrlConnection网络请求
 */
public class MainActivity extends Activity {

    private static final int SUCCESS = 0;
    private static final int ERROR = 1;
    private TextView mTv;
    private Button mBtnLoad;
    private Handler mHandler;
    private EditText mEt;
    private String mPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        mTv = (TextView) findViewById(R.id.tv);
        mEt = (EditText) findViewById(R.id.et);
        mBtnLoad = (Button) findViewById(R.id.btn_load);
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case 0:
                        String value = (String) msg.obj;
                        mTv.setText(value);
                        break;
                    case 1:

                        break;
                }
                super.handleMessage(msg);
            }
        };
    }

    public void load(View view){
        mPath = mEt.getText().toString();
        display();
    }

    private void display() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(mPath);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    //User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0

                    connection.setConnectTimeout(5000);
                    connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0");

                    int responseCode = connection.getResponseCode();
                    if (responseCode==200){
                        InputStream inputStream = connection.getInputStream();
                        String responseValue = StringTool.decodeString(inputStream);

                        Message msg = Message.obtain();
                        msg.what = SUCCESS;
                        msg.obj = responseValue;
                        mHandler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Message msg = Message.obtain();
                    msg.what = ERROR;
                    mHandler.sendMessage(msg);
                    Toast.makeText(MainActivity.this, "网络请求失败!!!!!!!!!!", Toast.LENGTH_SHORT).show();
                }
            }
        }){}.start();

    }
}
