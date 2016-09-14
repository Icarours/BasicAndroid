package com.syl.mmsassistant.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.syl.mmsassistant.MainActivity;
import com.syl.mmsassistant.R;

import java.util.ArrayList;
import java.util.List;

public class TemplateActivity extends AppCompatActivity {

    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);

        initData();

        ListView lvTemplates = (ListView) findViewById(R.id.templates_lv_templates);
        //如果是数组或者集合,可以用ArrayAdapter
        lvTemplates.setAdapter(new ArrayAdapter<>(TemplateActivity.this,R.layout.template_lv_item,mList));
        lvTemplates.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String template = mList.get(position);
                Intent data = new Intent(TemplateActivity.this, MainActivity.class);
                data.putExtra("template",template);
                setResult(0,data);//将要传递数据的数据带回启动当前Activity的原Activity
                finish();//关闭当前的Activity，回到启动他的Activity
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mList = new ArrayList<>();
        mList.add("茫茫人海中，相识了你，是一种缘份，只希望用我的真诚，换取你的真情。");
        mList.add("你微笑，我的心情很美妙；你流泪，我会伤心夜难寐；你的喜怒哀乐影响我的肺和胃，任沧海变，时光飞，我的真爱永不悔！");
        mList.add("真的想和你一起慢慢变老，直到老得哪儿也去不了，我依然会把你当成我手心里的宝。");
        mList.add("让我们做一对幸福的老鼠吧，笨笨地相爱，每天做的事是依偎在一起晒太阳，生一群小老鼠，冬天时大雪封山，就是躲在温暖的草堆里上网。");
        mList.add("命运让我们相遇，让我沉醉在你的眼眸里。我想陪着你，为你阻挡一生的风雨。请你相信，我对你的真情，我会让你的生命充满快乐回忆。");
    }
}
