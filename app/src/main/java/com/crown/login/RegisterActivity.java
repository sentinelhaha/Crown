package com.crown.login;

import android.content.SyncStatusObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //用户单击注册把信息发给服务器，取服务器返回的结果
        Button registerBtn= (Button) findViewById(R.id.register_submit_btn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1,找到5个editText
                EditText usenameEt= (EditText) findViewById(R.id.register_username_editText);
                EditText passwordEt= (EditText) findViewById(R.id.register_password_editText);
                EditText confirmPasswordEt= (EditText) findViewById(R.id.register_confirmPassword_editText);
                EditText emailEt= (EditText) findViewById(R.id.register_email_editText);
                EditText phoneEt= (EditText) findViewById(R.id.register_phone_editText);
                //2,接收用户输入的数据
                String username=usenameEt.getText().toString();
                String password=passwordEt.getText().toString();
                String confirmPassword=confirmPasswordEt.getText().toString();
                String email=emailEt.getText().toString();
                String phone=phoneEt.getText().toString();
                //3,判断数据
                if (password.equals(confirmPassword)) {
                    //4,联网发送数据，接收结果
                    //4.1拼出url
                    String url=PdApplication.SERVER_IP+"/user/register.html" +
                            "?username=" +username+
                            "&password=" +password+
                            "&phone=" +phone+
                            "&email="+email;
                    //4.2 发数据
                    MySuccessListener successListener=new MySuccessListener();
                    MyErrorListener errorListener=new MyErrorListener();
                    StringRequest stringRequest=new StringRequest(url,successListener,errorListener);
                    RequestQueue queue= Volley.newRequestQueue(RegisterActivity.this);
                    queue.add(stringRequest);

                }
            }
        });
    }

    //内部类
    //1,联网成功回调
    class MySuccessListener
            implements Response.Listener<String>{

        @Override
        public void onResponse(String s) {
            //4.3 接数据
            //4.4 对数据处理
            ServerResult serverResult=
                    JSON.parseObject(s,ServerResult.class);
            if (serverResult.getStatus()==200)
            {
                Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_LONG).show();
            }else
            {
                Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_LONG).show();

            }

        }
    }
    //2,联网失败回调
    class MyErrorListener implements Response.ErrorListener{

        @Override
        public void onErrorResponse(VolleyError volleyError) {
            System.out.println("出错了"+volleyError.getMessage());
        }
    }
}
