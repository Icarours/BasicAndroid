package com.syl.netnovel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.syl.netnovel.achieve.WriteNovel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WriteNovel novel = new WriteNovel("杜伟");
        novel.getNovelDetail();
    }
}
