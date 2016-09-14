package com.syl.newsapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.syl.newsapp.bean.NewsItem;
import com.syl.newsapp.utils.NewsUtil;
import com.syl.newsapp.utils.SmartImage;

import java.util.List;

public class MainActivity extends Activity {

    private static final int SUCCESS = 0;
    private static final int ERROR = 1;
    private ListView mListView;
    private MyAdapter mAdapter;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    mListView.setAdapter(mAdapter);
                    break;
                case ERROR:
                    Toast.makeText(MainActivity.this, "解析失败", Toast.LENGTH_SHORT).show();
                default:
                    break;
            }
        }
    };
    private List<NewsItem> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listView);

        mAdapter = new MyAdapter();
        new Thread() {
            @Override
            public void run() {
                String path = getResources().getString(R.string.urlPath);
                mList = NewsUtil.getAllItems(path);

                if (mList.size() > 0) {
                    Message msg = Message.obtain();
                    msg.what = SUCCESS;
                    handler.sendMessage(msg);
                } else {
                    Message msg = Message.obtain();
                    msg.what = ERROR;
                    handler.sendMessage(msg);
                }
            }
        }.start();
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            if (mList != null) {
                return mList.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (mList != null) {
                return mList.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            if (mList != null) {
                return position;
            }
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(MainActivity.this, R.layout.item_list, null);
                holder.ivIcon = (SmartImage) convertView.findViewById(R.id.iv_icon);
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
                holder.tvDesc = (TextView) convertView.findViewById(R.id.tv_desc);
                holder.tvType = (TextView) convertView.findViewById(R.id.tv_type);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            NewsItem newsItem = mList.get(position);

            holder.ivIcon.setImageUrl(newsItem.getImage(),R.mipmap.mm2);
            holder.tvTitle.setText(newsItem.getTitle());
            holder.tvDesc.setText(newsItem.getDescription());
            String tvType = newsItem.getType();
            if ("1".equals(tvType)) {//评论
                holder.tvType.setBackgroundColor(Color.RED);
                holder.tvType.setText("评论:");
            } else if ("2".equals(tvType)) {//视屏
                holder.tvType.setBackgroundColor(Color.BLUE);
                holder.tvType.setText("视屏:");
            } else if ("3".equals(tvType)) {//直播
                holder.tvType.setBackgroundColor(Color.GREEN);
                holder.tvType.setText("直播:");
            }
            return convertView;
        }

        class ViewHolder {
            SmartImage ivIcon;
            TextView tvTitle;
            TextView tvDesc;
            TextView tvType;
        }
    }
}
