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

        Button moveNav = findViewById(R.id.moveNav);
        moveNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_text = id.getText().toString();
                String pw_text = password.getText().toString();

                if(id_text.length() == 0 && pw_text.length() == 0){
                    toast(R.string.idpwfail);
                }else if(id_text.length() == 0){
                    toast(R.string.idfail);
                }else if(pw_text.length() == 0){
                    toast(R.string.pwfail);
                }else if(!id_text.equals("user1")){
                    toast(R.string.noid);
                }else if(!pw_text.equals("password")){
                    toast(R.string.notpw);
                }else{
                    Intent intent = new Intent(LoginActivity.this, NavActivity.class);
                    startActivity(intent);
                }
            }
        });

        Button moveRegister = findViewById(R.id.moveRegister);
        moveRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

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
