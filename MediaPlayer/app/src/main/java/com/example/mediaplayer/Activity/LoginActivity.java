package com.example.mediaplayer.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mediaplayer.R;
import com.example.mediaplayer.DataBase.DBManger;

public class LoginActivity extends AppCompatActivity {

    EditText accountEt,passwordEt;
    Button loginBtn,registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {

        accountEt = findViewById(R.id.account_Et);
        passwordEt = findViewById(R.id.password_Et);
        loginBtn = findViewById(R.id.login_btn);
        registerBtn = findViewById(R.id.register_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String acc = accountEt.getText().toString().trim();
                String pas = passwordEt.getText().toString().trim();
                if (DBManger.isExist(acc) == 1) {
                    if (DBManger.getPasswod(acc).equals(pas)) {
                        //账号和密码都正确后跳转到主界面
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "密码错误，请重新输入密码", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this,"账号不存在，请注册账号！",Toast.LENGTH_SHORT).show();
                }

            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent,1);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == 1) {
            if (data != null) {
                String name = data.getStringExtra("account");
                String password = data.getStringExtra("password");
                accountEt.setText(name);
                passwordEt.setText(password);
                Toast.makeText(this, "注册成功，本次已自动为您填写用户名和密码！", Toast.LENGTH_SHORT).show();
            }
        }
    }
}