package com.example.mediaplayer.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mediaplayer.DataBase.DBManger;
import com.example.mediaplayer.R;

public class RegisterActivity extends AppCompatActivity {

    EditText accountEt,passwordEt;
    Button loginBtn,backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {

        accountEt = findViewById(R.id.account_Et);
        passwordEt = findViewById(R.id.password_Et);
        loginBtn = findViewById(R.id.login_btn);
        backBtn = findViewById(R.id.back_btn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String acc = accountEt.getText().toString().trim();
                String pas = passwordEt.getText().toString().trim();
                if (DBManger.isExist(acc) == 1) {
                    Toast.makeText(RegisterActivity.this, "用户名已经存在，请更换用户名！", Toast.LENGTH_SHORT).show();
                } else {
                    //将用户名和密码存入数据库
                    DBManger.insertUserInfo(acc,pas);
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    intent.putExtra("account",acc);
                    intent.putExtra("password",pas);
                    setResult(1,intent);
                    finish();
                }
            }
        });
    }
}