package com.syl.renpingjs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RenpingActivity extends AppCompatActivity {
    private EditText mEtName;
    private Button mBtnResult;
    private TextView mTvContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renping);

        mEtName = (EditText) findViewById(R.id.et_name);
        mBtnResult = (Button) findViewById(R.id.btn_result);
        mTvContent = (TextView) findViewById(R.id.tv_content);
    }

    /**
     * 计算人品
     * @param view
     */
    public void calculate(View view){
        String name = mEtName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(RenpingActivity.this, "姓名不能为空!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        //计算人品值
        int total = 0;
        byte[] bs = name.getBytes();
        for (byte b :
                bs) {
            int rs = b & 0xff;
            rs = Math.abs(rs);
            total += rs;
        }
        int rpz = total % 100;
        if (rpz > 90) {
            mTvContent.setText(rpz+"人品最好!!!!!");
        } else if (rpz > 80) {
            mTvContent.setText(rpz+"人品次好!!!!!");
        } else if (rpz > 70) {
            mTvContent.setText(rpz+"人品一般!!!!!");
        } else if (rpz > 60) {
            mTvContent.setText(rpz+"人品还行!!!!!");
        } else {
            mTvContent.setText(rpz+"人品最不好!!!!!");
        }
    }
}
