package com.syl.mmsassistant;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEtNumber = (EditText) findViewById(R.id.et_number);
        mEtContent = (EditText) findViewById(R.id.et_content);
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
