package com.syl.phoneluck;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.syl.phoneluck.bean.Phone;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * author   j3767
 * date     2016/8/29 23:44
 * desc
 * 手机测试吉凶,httpURLConnection,handler
 * tomcat内置一静态文本,通过手机访问获取数据
 */
public class MainActivity extends AppCompatActivity {

    private static final int SUCCESS = 0;
    private static final int ERROR = 1;
    private static final int ERROR_EXCEPTION = 2;
    private EditText mNum;
    private TextView mContent;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNum = (EditText) findViewById(R.id.et_num);
        mContent = (TextView) findViewById(R.id.tv_content);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case SUCCESS:
                        Phone phone = (Phone) msg.obj;
                        mContent.setText(phone.toString());
                        break;
                    case ERROR:
                        break;
                    case ERROR_EXCEPTION:
                        String e = (String) msg.obj;
                        mContent.setText(e);
                }
                super.handleMessage(msg);
            }
        };
    }

    public void test(View view) {
        final String path = getResources().getString(R.string.path);
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(5000);
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = connection.getInputStream();

                        /*
                        * <smartresult>
                            <product type="mobile">
                            <phonenum>13691689238</phonenum>
                            <location>广东深圳移动神州行卡</location>
                            <phoneJx>名虽可得，利则难获，艺界发展，可望成功 凶带吉</phoneJx>
                            </product>
                            </smartresult>
                        * */

                        //使用pull解析器
                        XmlPullParser pullParser = Xml.newPullParser();
                        //告诉pull解析器解析的对象
                        pullParser.setInput(inputStream, "UTF-8");
                        //模板代码
                        int type = pullParser.getEventType();
                        //将解析出来的数据封装在bean对象中
                        Phone phone = new Phone();
                        while (type != XmlPullParser.END_DOCUMENT) {
                            if (type == XmlPullParser.START_TAG) {
                                if ("product".equals(pullParser.getName())) {
                                    String typeValue = pullParser.getAttributeName(0);
                                    phone.setType(typeValue);
                                } else if ("phonenum".equals(pullParser.getName())) {
                                    String phonenum = pullParser.nextText();
                                    phone.setPhoneName(phonenum);
                                } else if ("location".equals(pullParser.getName())) {
                                    String location = pullParser.nextText();
                                    phone.setLocation(location);
                                } else if ("phoneJx".equals(pullParser.getName())) {
                                    String phoneJx = pullParser.nextText();
                                    phone.setPhoneJx(phoneJx);
                                }
                            }
                            //如果不调下面这句,就是死循环
                            type = pullParser.next();
                        }
                        Message msg = Message.obtain();
                        msg.what = SUCCESS;
                        msg.obj = phone;
                        mHandler.sendMessage(msg);
                    } else {
                        Message msg = Message.obtain();
                        msg.what = ERROR;
                        mHandler.sendMessage(msg);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Message msg = Message.obtain();
                    msg.what = ERROR_EXCEPTION;
                    msg.obj = e.toString();
                    mHandler.sendMessage(msg);
                }
            }
        }.start();
    }
}
