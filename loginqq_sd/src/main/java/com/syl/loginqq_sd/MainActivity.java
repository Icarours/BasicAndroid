package com.syl.loginqq_sd;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText mEt_name;
    private EditText mEt_pwd;
    private CheckBox mCb_rem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEt_name = (EditText) findViewById(R.id.et_name);
        mEt_pwd = (EditText) findViewById(R.id.et_pwd);

        mCb_rem = (CheckBox) findViewById(R.id.cb_remember);
    }
    public void login(View v){

        String name = mEt_name.getText().toString();
        String pwd = mEt_pwd.getText().toString();

        if (TextUtils.isEmpty(name)||TextUtils.isEmpty(pwd)){
            Toast.makeText(MainActivity.this, "用户名,密码不能为空", Toast.LENGTH_SHORT).show();
        }

        boolean checked = mCb_rem.isChecked();
        if (checked){

            try {
                String currentStorageState = Environment.getExternalStorageState();
                if (!Environment.MEDIA_MOUNTED.equals(currentStorageState)){
                    Toast.makeText(MainActivity.this, "sd卡没有成功挂载", Toast.LENGTH_SHORT).show();
                    return;
                }

                //总共sd卡内存
                long totalSpace = Environment.getExternalStorageDirectory().getTotalSpace();
                Log.d(TAG,"sd卡内存"+totalSpace);
                System.out.println("sd卡内存"+totalSpace);
                //剩余内存
                long freeSpace = Environment.getExternalStorageDirectory().getFreeSpace();
                Log.d(TAG,"sd卡剩余内存"+freeSpace);
                System.out.println("sd卡内存"+freeSpace);

                //文件保存在sd卡
                File file = new File(Environment.getExternalStorageDirectory(),"info");
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                String str = name+"#syl#"+pwd;
                fileOutputStream.write(str.getBytes());
                fileOutputStream.close();

                Log.d(TAG,"保存成功");
                Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Log.d(TAG,"保存失败");
                Toast.makeText(MainActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
            }
        }else{
            Log.d(TAG, "没有保存用户名和密码");
        }

    }
}
