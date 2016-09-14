package com.syl.loadimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import static android.graphics.BitmapFactory.*;

/**
 * 创建者     苏永亮
 * 创建时间   2016/8/19 23:57
 * 描述
 * 加载图片,使用BitmapFactory,option压缩图片,然后显示
 * 读取外存卡也是需要权限的
 * 向模拟器导入文件时要注意模拟器文件夹的读写权限,并不是所有的文件夹都可以进行读写
 * <p/>
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}$
 */
public class MainActivity extends AppCompatActivity {

    private ImageView mIv;
    private int mScreenWidth;
    private int mScreenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //屏幕的宽高
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        mScreenWidth = wm.getDefaultDisplay().getWidth();
        mScreenHeight = wm.getDefaultDisplay().getHeight();

        Log.d(this.getClass().getSimpleName(), "手机屏幕宽===" + mScreenWidth + "===手机屏幕高===" + mScreenHeight);
        mIv = (ImageView) findViewById(R.id.iv);
    }

    public void load(View view) {
        Options options = new Options();
        //返回null,不去真正解析图片,只是得到图片的宽高
        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile("/mnt/sdcard/test.jpg", options);
        //Android不建议硬编码,目录路径最好使用Environment.getExternalStorageDirectory().getPath()获取
        BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath() + "test.jpg", options);
        //图片的宽高
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;

        Log.d(this.getClass().getSimpleName(), "图片宽度==" + outWidth + "===图片高度===" + outHeight);

        //定义压缩比
        int scale;
        int scaleX = outWidth / mScreenWidth;
        int scaleY = outHeight / mScreenHeight;

        scale = scaleX > scaleY ? scaleX : scaleY;
        options.inSampleSize = scale;

        //真正解析图片
        options.inJustDecodeBounds = false;
//        Bitmap bitmap = BitmapFactory.decodeFile("/mnt/sdcard/test.jpg", options);
        Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getPath() + "test.jpg", options);

        mIv.setImageBitmap(bitmap);
    }
}













