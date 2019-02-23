package com.crown.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

public class LoginActivity extends AppCompatActivity {
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //单击登录用volley发数据给服务器
        Button loginBtn = (Button) findViewById(R.id.login_submit_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1,找到控件
                EditText usernameEt = (EditText)
                        findViewById(R.id.login_username_editText);
                EditText passwordEt = (EditText)
                        findViewById(R.id.login_password_editText);
                //2,接收界面上用户输入的数据
                 username = usernameEt.getText().toString();
                String password = passwordEt.getText().toString();
                //3,判断数据
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_LONG).show();
                } else {

                    //4.联网发送
                    //4.1 拼出url
                    String url = PdApplication.SERVER_IP + "/user/login.html" +
                            "?username=" + username +
                            "&password=" + password;
                    //4.2 发送
                    MySuccessListener successListener = new MySuccessListener();
                    MyErrorListener errorListener = new MyErrorListener();
                    StringRequest request = new StringRequest(url, successListener, errorListener);

                    RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                    queue.add(request);


                }
            }
        });

        //单击登录界面中的注册按钮跳到注册界面
        Button registerBtn = (Button) findViewById(R.id.login_register_btn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳到注册界面
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    //内部类
    //1，联网成功后回调
    class MySuccessListener implements Response.Listener<String> {

        @Override
        public void onResponse(String s) {
            //4.3 在回调的方法中接收数据
            //4.4 处理数据
            //4.5 把结果显示在界面上
            ServerResult serverResult = JSON.parseObject(s, ServerResult.class);
            if (serverResult.getStatus() == 200) {
                //调dao保存数据
                UserDAO userDAO=new UserDAO();
                userDAO.insert(LoginActivity.this,username);

                Toast.makeText
                        (LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(LoginActivity.this,MainFragmentActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText
                        (LoginActivity.this, "登录失败", Toast.LENGTH_LONG).show();
            }
        }
    }

    //2,联网失败后回调
    class MyErrorListener implements Response.ErrorListener {

        @Override
        public void onErrorResponse(VolleyError volleyError) {
            System.out.println("出错了" + volleyError.getMessage());
        }
    }
}
