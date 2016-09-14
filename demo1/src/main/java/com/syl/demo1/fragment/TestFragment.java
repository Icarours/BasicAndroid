package com.syl.demo1.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.syl.demo1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by j3767 on 2016/8/30.
 *
 * @Describe
 * @Called
 */
public class TestFragment extends Fragment {
    private List<String> mList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout rootView = (LinearLayout) inflater.inflate(R.layout.fragment_test, null, false);
        TextView textView = new TextView(getActivity());
        textView.setText(this.getClass().getSimpleName());

        initData();
        ListView listView = new ListView(getActivity());
        listView.setAdapter(new MyAdapter());

        rootView.addView(listView);
        rootView.addView(textView);
        return rootView;

    }

    private void initData() {
        for (int i = 0; i < 100; i++) {
            mList.add("我是listView中TextView" + i);
        }
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
                convertView = View.inflate(getActivity(), R.layout.item, null);
                holder.tv = (TextView) convertView.findViewById(R.id.tv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv.setText(mList.get(position));
            return convertView;
        }

        class ViewHolder {
            TextView tv;
        }
    }
}
