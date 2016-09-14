package com.syl.mmsassistant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;

import com.syl.mmsassistant.activity.ContactsActivity;
import com.syl.mmsassistant.activity.TemplateActivity;

public class MainActivity extends AppCompatActivity {

    private EditText mEtNumber;
    private EditText mEtContent;
    private SharedPreferences mSp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEtNumber = (EditText) findViewById(R.id.et_number);
        mEtContent = (EditText) findViewById(R.id.et_content);

        //回显
        mSp = getSharedPreferences("sms",Context.MODE_PRIVATE);
        mEtNumber.setText(mSp.getString("mEtNumber",""));
        mEtContent.setText(mSp.getString("mEtContent",""));
    }

    @Override
    protected void onDestroy() {
        //使用SharedPreferences保存数据
        mSp = getSharedPreferences("sms", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = mSp.edit();
        edit.putString("mEtNumber",mEtNumber.getText().toString());
        edit.putString("mEtContent",mEtContent.getText().toString());
        edit.apply();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (data != null) {
                    String clickedNumber = data.getStringExtra("clickedNumber");
                    mEtNumber.setText(clickedNumber);
                }
                break;
            case 2:
                if (data != null) {
                    String template = data.getStringExtra("template");
                    mEtContent.setText(template);
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 选择联系人
     *
     * @param view
     */
    public void selectContact(View view) {
        Intent intent = new Intent(MainActivity.this, ContactsActivity.class);
        startActivityForResult(intent, 1);
    }

    /**
     * 选择模板
     *
     * @param view
     */
    public void selectModule(View view) {
        Intent intent = new Intent(MainActivity.this, TemplateActivity.class);
        startActivityForResult(intent, 2);
    }

    /**
     * 发送短信
     *
     * @param view
     */
    public void send(View view) {
        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(mEtNumber.toString(),null,mEtContent.toString(),null,null);
    }
}
