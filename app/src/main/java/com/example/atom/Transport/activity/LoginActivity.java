package com.example.atom.Transport.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.atom.Transport.R;
import com.example.atom.Transport.utils.CommonTools;

/**
 * Created by ltf on 2016/12/5.
 * 登录账户
 */
public class LoginActivity extends Activity {

    private ProgressBar mProgressBar;
    private EditText mEditTest1;
    private EditText mEditTest2;
    private Button mButton_login;
    private Button mButton_forget;
    private Button mButton_rigest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById();
        initview();
    }

    /**
     * 找到需要的组件
     */
    private void findViewById() {
        mProgressBar = (ProgressBar) findViewById(R.id.login_progress);
        mEditTest1 = (EditText) findViewById(R.id.acountID);
        mEditTest2 = (EditText) findViewById(R.id.password);
        mButton_forget = (Button) findViewById(R.id.tv_pass_forget);
        mButton_rigest = (Button) findViewById(R.id.tv_user_regist);
        mButton_login= (Button) findViewById(R.id.email_sign_in_button);

    }

    /**
     * 初始化view
     */
    private void initview() {
        mButton_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonTools.showShortToast(LoginActivity.this,"登录");
            }
        });
        mButton_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonTools.showShortToast(LoginActivity.this,"忘记密码");
            }
        });
        mButton_rigest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(LoginActivity.this,RegestActivity.class);
                startActivity(intent);
            }
        });
    }

}
