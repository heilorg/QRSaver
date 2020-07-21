package com.example.qrsaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText id;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(R.string.login);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF3B6DFF));

        id = findViewById(R.id.idForm);
        password = findViewById(R.id.pwForm);


        // 로그인 버튼 클릭 이벤트
        Button moveNav = findViewById(R.id.moveNav);
        moveNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_text = id.getText().toString();
                String pw_text = password.getText().toString();

                if(id_text.length() == 0 && pw_text.length() == 0){ // 로그인 비밀번호 입력창이 둘다 비었을 때
                    toast(R.string.idpwfail);
                }else if(id_text.length() == 0){ // 아이디만 비었을 때
                    toast(R.string.idfail);
                }else if(pw_text.length() == 0){ // 비밀번호만 비었을 때
                    toast(R.string.pwfail);
                }else if(!id_text.equals("user1")){ // 아이디가 틀리다면
                    toast(R.string.noid);
                }else if(!pw_text.equals("password")){ // 비밀번호가 틀리다면
                    toast(R.string.notpw);
                }else{ // 로그인 성공 시
                    Intent intent = new Intent(LoginActivity.this, NavActivity.class);
                    startActivity(intent);
                }
            }
        });

        // 회원가입 버튼 클릭 이벤트
        Button moveRegister = findViewById(R.id.moveRegister);
        moveRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // 로그인 없이 시작 버튼 클릭 이벤트
        Button notLogin = findViewById(R.id.notLogin);
        notLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, NavActivity.class);
                startActivity(intent);
            }
        });
    }
    public void toast(int text){
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
}
