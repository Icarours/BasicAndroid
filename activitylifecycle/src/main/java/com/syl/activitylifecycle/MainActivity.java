package com.syl.activitylifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * author   j3767
 * date     2016/9/14 9:08
 * desc
 * Activity的生命周期
 * 简单游戏
 * 横竖屏切换
 */
public class MainActivity extends Activity {

    private ImageView mIvPlayer;
    private ImageView mIvBoss;
    private ProgressBar mPbHb;
    int blood = 100;//boss的血值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(this.getClass().getSimpleName(), "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIvPlayer = (ImageView) findViewById(R.id.iv_player);
        mIvBoss = (ImageView) findViewById(R.id.iv_boss);
        mPbHb = (ProgressBar) findViewById(R.id.pb_hb);
        mPbHb.setMax(100);
    }

    /**
     * 轻拳
     *
     * @param view
     */
    public void qq(View view) {
        hit(3,R.mipmap.qq);
    }

    private void hit(int hitForce,int img) {
        mIvPlayer.setImageResource(img);
        blood -= hitForce;
        mPbHb.setProgress(blood);
        if (blood <= 0) {
            mIvBoss.setImageResource(R.mipmap.die);
        }
    }

    /**
     * 重拳
     *
     * @param view
     */
    public void zq(View view) {
        hit(7,R.mipmap.zq);
    }

    /**
     * 重脚
     *
     * @param view
     */
    public void zj(View view) {
        hit(8,R.mipmap.zj);
    }

    @Override
    protected void onStart() {
        Log.v(this.getClass().getSimpleName(), "onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.v(this.getClass().getSimpleName(), "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.v(this.getClass().getSimpleName(), "onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.v(this.getClass().getSimpleName(), "onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.v(this.getClass().getSimpleName(), "onResume");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.v(this.getClass().getSimpleName(), "onRestart");
        super.onRestart();
    }
}
