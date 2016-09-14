package com.syl.qqloginget;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.syl.qqloginget.utils.StringTool;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends Activity {

    private static final int SUCCESS = 0;
    private static final int ERORR = 1;
    private static final int FAILED = 2;
    @butterknife.Bind(R.id.imageView)
    ImageView mImageView;
    @butterknife.Bind(R.id.et_username)
    EditText mEtUsername;
    @butterknife.Bind(R.id.et_password)
    EditText mEtPassword;
    @butterknife.Bind(R.id.btn_login)
    Button mBtnLogin;
    @butterknife.Bind(R.id.tv_result)
    TextView mTvResult;
    private String mUserName;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        butterknife.ButterKnife.bind(this);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    String resultValue = (String) msg.obj;
                    mTvResult.setText(resultValue);
                    break;
                case ERORR:
                    String e = (String) msg.obj;
                    mTvResult.setText(e);
                    Toast.makeText(MainActivity.this, "对不起,出错了!!!!!!!!!!!", Toast.LENGTH_SHORT).show();
                    break;
                case FAILED:
                    Toast.makeText(MainActivity.this, "服务器异常!!!!!!!!!!", Toast.LENGTH_SHORT).show();
                default:
                    break;
            }
        }
    };

    /**
     * 登录
     *
     * @param view
     */
    public void login(View view) {
        mUserName = mEtUsername.getText().toString();
        mPassword = mEtPassword.getText().toString();

        if (TextUtils.isEmpty(mUserName) || TextUtils.isEmpty(mPassword)) {
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            mEtUsername.startAnimation(shake);
            mEtPassword.startAnimation(shake);
            return;
        } else {

        }
        new Thread() {
            @Override
            public void run() {
//                http://192.168.2.205:8080/androidLogin/login
//                String path = "http://192.168.2.205:8080/androidLogin/login?username=10000&password=123456";
                try {
                    //如果有中文,需要手动编码
                    String encodeUserName = URLEncoder.encode(mUserName, "UTF-8");
                    String path = "http://192.168.2.205:8080/androidLogin/login?username=" + encodeUserName + "&password=" + mEtPassword;
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    int responseCode = connection.getResponseCode();

                    if (responseCode == 200) {
                        InputStream inputStream = connection.getInputStream();
                        String resultValue = StringTool.decode2String(inputStream);

                        Message msg = Message.obtain();
                        msg.obj = resultValue;
                        msg.what = SUCCESS;
                        mHandler.sendMessage(msg);
                    } else {
                        Message msg = Message.obtain();
                        msg.what = FAILED;
                        mHandler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    Message msg = Message.obtain();
                    msg.obj = e.toString();
                    msg.what = ERORR;
                    mHandler.sendMessage(msg);
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
