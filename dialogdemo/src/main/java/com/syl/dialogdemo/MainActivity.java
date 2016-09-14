package com.syl.dialogdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Create     苏永亮
 * Date   2016/8/20 15:07
 * Des	      ${TODO}$
 * 常见对话框,确定/取消/单选/多选,可以组合使用
 */

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.click1)
    Button mClick1;
    @Bind(R.id.click2)
    Button mClick2;
    @Bind(R.id.click3)
    Button mClick3;
    @Bind(R.id.click4)
    Button mClick4;
    @Bind(R.id.click5)
    Button mClick5;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        mContext = this;
        mClick1.setText("ok/cancel");
        mClick2.setText("单选对话框");
        mClick3.setText("多选对话框");
        mClick4.setText("不带进度条的对话框");
        mClick5.setText("click5");
    }

    /**
     * 普通对话框
     *
     * @param view
     */
    public void click1(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("欲练此功,必先自宫");
        builder.setIcon(R.mipmap.ic_launcher);
        //确定对话框
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "aaaaaaaaaaa.....", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        //取消对话框
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "还是先想想吧!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    /**
     * 单选对话框
     *
     * @param view
     */
    public void click2(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("请选择您的性别:");
        builder.setIcon(R.mipmap.ic_launcher);
        String[] genders = {"男", "女"};
        //单选对话框
        builder.setSingleChoiceItems(genders, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Toast.makeText(MainActivity.this, "男被选中了!!!!!!!!!!!!", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(MainActivity.this, "女被选中了", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "没有选中任何性别", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    /**
     * 多选对话框
     * boolean 基本数据类型和Boolean类是两个不同的类
     *
     * @param view
     */
    String[] hobbies = {"读书", "看电视", "上网", "玩游戏", "敲代码"};
    boolean[] checkedItems = {true, true, false, false, false};

    public void click3(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("请你选择自己的爱好:");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMultiChoiceItems(hobbies, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Toast.makeText(MainActivity.this, "被选中的是 :　" + hobbies[which] + ", 位置是 : " + which + "状态是 : " + isChecked, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "您选中了自己的爱好", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    /**
     * 进度对话框,不带实时进度
     *
     * @param view
     */
    public void click4(View view) {
        //view的创建要放在主线程
        final ProgressDialog dialog = ProgressDialog.show(mContext, "正在加载", "Loading ............");
        new Thread(new Runnable() {
            @Override
            public void run() {//主线程不能休眠
                SystemClock.sleep(3000);
                dialog.dismiss();
            }
        }).start();
    }

    public void click5(View view) {

    }
}
