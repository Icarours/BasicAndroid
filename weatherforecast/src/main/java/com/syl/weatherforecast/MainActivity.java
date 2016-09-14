package com.syl.weatherforecast;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.syl.weatherforecast.utils.StringTool;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * author   j3767
 * date     2016/8/31 22:10
 * desc
 * handler,
 * JSONObject解析
 * httpUrlConnection网络请求
 */
public class MainActivity extends AppCompatActivity {
    public static final int SUCCESS = 0;
    private static final int FAILURE = 2;
    private static final int ERROR = 1;
    private static final int INVALID_CITY = 3;
    @Bind(R.id.tv1)
    TextView mTv1;
    @Bind(R.id.tv2)
    TextView mTv2;
    @Bind(R.id.tv3)
    TextView mTv3;
    @Bind(R.id.tv4)
    TextView mTv4;
    @Bind(R.id.tv5)
    TextView mTv5;
    @Bind(R.id.et_city)
    EditText mEtCity;

    //url地址一定要写完整,不要漏掉或者省略http
    public static final String BASEURL = "http://wthrcdn.etouch.cn/weather_mini";
    private Handler mHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ScrollView只能有一个孩子
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case SUCCESS:
                        JSONArray forcast = (JSONArray) msg.obj;
                        try {
                            String forcast1 = forcast.getJSONObject(0).toString();
                            String forcast2 = forcast.getJSONObject(1).toString();
                            String forcast3 = forcast.getJSONObject(2).toString();
                            String forcast4 = forcast.getJSONObject(3).toString();
                            String forcast5 = forcast.getJSONObject(4).toString();

                            mTv1.setText(forcast1);
                            mTv2.setText(forcast2);
                            mTv3.setText(forcast3);
                            mTv4.setText(forcast4);
                            mTv5.setText(forcast5);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    case ERROR:
                        Toast.makeText(MainActivity.this, "出现异常,请求失败!!", Toast.LENGTH_SHORT).show();
                        break;
                    case FAILURE:
                        Toast.makeText(MainActivity.this, "出现异常,请求失败!!", Toast.LENGTH_SHORT).show();
                        break;
                    case INVALID_CITY:
                        Toast.makeText(MainActivity.this, "无效的城市!!", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        };
    }

    public void search(View view) {
        final String city = mEtCity.getText().toString();
        if (TextUtils.isEmpty(city)) {
            Toast.makeText(MainActivity.this, "城市名称不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread() {
            @Override
            public void run() {
//                wthrcdn.etouch.cn/weather_mini?city=上海
                try {
//                    String encodeCity = URLEncoder.encode(city, "UTF-8");
                    String path = BASEURL + "?city=" + city;
                    URL url = new URL(path);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(5000);
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = connection.getInputStream();
                        String jsonData = StringTool.decodeString(inputStream);

                        /*------------------ 使用JSONObject解析数据 -----------------*/
                        JSONObject jsonObject = new JSONObject(jsonData);
                        String desc = jsonObject.getString("desc");
                        if ("OK".equals(desc)) {//数据有效
                            JSONObject data = jsonObject.getJSONObject("data");
                            JSONArray forecast = data.getJSONArray("forecast");

                            /*String forecast1 = forecast.getJSONObject(0).toString();
                            String forecast2 = forecast.getJSONObject(1).toString();
                            String forecast3 = forecast.getJSONObject(2).toString();

                            System.out.println(forecast1);
                            System.out.println(forecast2);
                            System.out.println(forecast3);*/

                            Message msg = Message.obtain();
                            msg.obj = forecast;
                            msg.what = SUCCESS;
                            mHandler.sendMessage(msg);
                        } else {//数据无效
                            Message msg = Message.obtain();
                            msg.what = INVALID_CITY;
                            mHandler.sendMessage(msg);
                        }
                    } else {
                        Message msg = Message.obtain();
                        msg.what = FAILURE;
                        mHandler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    Message msg = Message.obtain();
                    msg.obj = e;
                    msg.what = ERROR;
                    mHandler.sendMessage(msg);
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
