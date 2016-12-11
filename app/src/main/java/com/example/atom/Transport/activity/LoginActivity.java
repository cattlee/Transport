package com.example.atom.Transport.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.atom.Transport.R;
import com.example.atom.Transport.ui.MainActivity;
import com.example.atom.Transport.utils.CommonTools;

/**
 * Created by ltf on 2016/12/5.
 * 登录账户
 */
public class LoginActivity extends Activity {

    private ProgressBar mProgressBar;
    private EditText name;
    private EditText password;
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
        name = (EditText) findViewById(R.id.acountID);
        password = (EditText) findViewById(R.id.password);
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
                //发送请求 获取结果,作相应的处理 4.0以后不支持直接访问网络需要使用Handler对象
                final Handler mHandler=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        String response=(String)msg.obj;
                        if("TRUE".equals(response)){
                            //如果登陆成功 ，进入主界面
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                        }else{
                            CommonTools.showShortToast(LoginActivity.this,"用户名或密码不匹配");
                        }
                    }
                };
                new Thread(new Runnable(){

                    @Override
                    public void run() {//子线程中访问网络
                        LoginToServer login=new LoginToServer();
                        String result = login.doPost(name.getText().toString(),password.getText().toString());
                        Message msg=new Message();
                        msg.obj=result;
                        mHandler.sendMessage(msg);
                    }
                });


                //CommonTools.showShortToast(LoginActivity.this,"登录");
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
