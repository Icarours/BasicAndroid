package com.syl.mmsassistant.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.syl.mmsassistant.MainActivity;
import com.syl.mmsassistant.R;

public class ContactsActivity extends AppCompatActivity {
    //创建数据
    private String[] contacts = {"13363028315", "15989556322", "13210354566", "15989469069"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        ListView lvContacts = (ListView) findViewById(R.id.contacts_lv_contacts);
        //使用ArrayAdapterGoogle封装好的适配器。数组或者集合专用
        lvContacts.setAdapter(new ArrayAdapter<>(this, R.layout.contacts_lv_item, contacts));
        lvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedNumber = contacts[position];
                Intent intent = new Intent(ContactsActivity.this, MainActivity.class);
                intent.putExtra("clickedNumber",clickedNumber);
                //onActivity中接收
                setResult(0,intent);
                //关掉当前界面
                finish();
            }
        });
    }

    /**
     * 创建适配器
     */
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return contacts == null ? 0 : contacts.length;
        }

        @Override
        public Object getItem(int position) {
            return contacts == null ? null : contacts[position];
        }

        @Override
        public long getItemId(int position) {
            return contacts == null ? 0 : position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
