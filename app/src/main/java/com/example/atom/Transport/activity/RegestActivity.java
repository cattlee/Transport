package com.example.atom.Transport.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.atom.Transport.R;
import com.example.atom.Transport.utils.CommonTools;

/**
 * 注册账户
 */
public class RegestActivity extends AppCompatActivity {

    private ImageView mImageView_back;//注册页返回键
    private EditText mEditText_id;//ID
    private EditText mEditText_name;//名字
    private EditText mEditext_mash;//番号
    private EditText mEditText_key1;//输入密码
    private EditText mEditext_key2;//确认密码
    private Button mButton_signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regest);
        findViewById();
        initView();
    }

    private void findViewById() {
        mImageView_back = (ImageView) findViewById(R.id.regest_back);
        mEditText_id = (EditText) findViewById(R.id.regest_ID);
        mEditText_name = (EditText) findViewById(R.id.regest_nanme);
        mEditext_mash = (EditText) findViewById(R.id.regest_mash);
        mEditText_key1 = (EditText) findViewById(R.id.regest_key1);
        mEditext_key2 = (EditText) findViewById(R.id.regest_key2);
        mButton_signin = (Button) findViewById(R.id.email_sign_in_button);
    }

    private void initView() {
        mButton_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonTools.showShortToast(RegestActivity.this,"注册完成");
            }
        });
    }
}
