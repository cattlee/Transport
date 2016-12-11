package com.example.atom.Transport.activity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ltf on 2016/12/11.
 * 封装发送Http请求的工具类
 */

public class LoginToServer {
    private String  url="";
    String result="";

    /**
     * @param name
     * @param passwod
     * @return
     * httpclient 通过get方式发送http请求
     */
    public String doGet(String name,String passwod){
        return result;
    }

    /**
     * @param name
     * @param password
     * @return
     * httpclient 通过post方式进行发送http请求
     */
    public String doPost(String name,String password){
        HttpClient hc = new DefaultHttpClient();
        HttpPost hp=new HttpPost(url);//根据URL地址创建HttpPost对象,涉及到访问参数，对参数进行封装
        NameValuePair param1=new BasicNameValuePair("username",name);//将参数封装成键值对
        NameValuePair param2=new BasicNameValuePair("password",password);//将密码也封装成键值对
        List<NameValuePair> params=new ArrayList<NameValuePair>();//参数集
        params.add(param1);//参数加入到参数集
        params.add(param2);
        HttpEntity he;

        try {//处理异常信息
            he=new UrlEncodedFormEntity(params,"GBK");//将参数集绑定到HttpEntity，使用HttpPost.setentity()完成
            HttpResponse response=hc.execute(hp);
            //如果正确访问了
            if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                HttpEntity het=response.getEntity();//获得信息体
                InputStream is=het.getContent();//获取输入流(字节)
                //InputStreamReader 将字节流转换为字符流。
                BufferedReader br=new BufferedReader(new InputStreamReader(is));//将输入流封装
                String readLine=null;
                while((readLine=br.readLine())!=null){
                    result=result+readLine;
                }
                is.close();//关闭输入流
                return result;
            }else{
                return "ERROR";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Exception";
        }



    }
}
