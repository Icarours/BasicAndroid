package com.syl.newsapp.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by j3767 on 2016/9/1.
 *
 * @Describe自定义smartIamage,可以直接加载网络图片
 * @Called
 */
public class SmartImage extends ImageView {
    private static final int SUCCESS = 0;
    private static final int ERROR = 1;

    public SmartImage(Context context) {
        super(context);
    }

    public SmartImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SmartImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SmartImage(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    Bitmap bitmap = (Bitmap) msg.obj;
                    setImageBitmap(bitmap);
                    break;
                case ERROR:
                    int resourceId = (int) msg.obj;
                    //发生错误时加载的图片
                    setBackgroundResource(resourceId);
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 设置图片
     *
     * @param imageUrl
     */
    public void setImageUrl(final String imageUrl) {
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(imageUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(5000);
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = connection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        Message msg = Message.obtain();
                        msg.what = SUCCESS;
                        msg.obj = bitmap;
                        mHandler.sendMessage(msg);
                    }else {
                        Message msg = Message.obtain();
                        msg.what = ERROR;
                        mHandler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    Message msg = Message.obtain();
                    msg.what = ERROR;
                    mHandler.sendMessage(msg);
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 设置图片(重载)
     *
     * @param imageUrl
     * @param resourceId
     */
    public void setImageUrl(final String imageUrl, final int resourceId) {
        new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(imageUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setConnectTimeout(5000);
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        InputStream inputStream = connection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                        Message msg = Message.obtain();
                        msg.what = SUCCESS;
                        msg.obj = bitmap;
                        mHandler.sendMessage(msg);
                    }else {
                        Message msg = Message.obtain();
                        msg.what = ERROR;
                        msg.obj = resourceId;
                        mHandler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    Message msg = Message.obtain();
                    msg.what = ERROR;
                    msg.obj = resourceId;
                    mHandler.sendMessage(msg);
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
