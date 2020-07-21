package com.example.qrsaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class NavActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        setTitle(R.string.app_name);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF3B6DFF));

        // 촬영 페이지 이동 버튼 클릭 이벤트
        ViewGroup movePhoto = (ViewGroup)findViewById(R.id.movePhoto);
        movePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavActivity.this, MainActivity.class);
                intent.putExtra("page", 1);
                startActivity(intent);
            }
        });

        // 저장소 페이지 이동 버튼 클릭 이벤트
        ViewGroup moveStorage = (ViewGroup)findViewById(R.id.moveStorage);
        moveStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavActivity.this, MainActivity.class);
                intent.putExtra("page", 2);
                startActivity(intent);
            }
        });

        // 생성 페이지 이동 버튼 클릭 이벤트
        ViewGroup moveCreation = (ViewGroup)findViewById(R.id.moveCreation);
        moveCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavActivity.this, MainActivity.class);
                intent.putExtra("page", 3);
                startActivity(intent);
            }
        });
    }


    // 액션바 생성
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action, menu);
        return true;
    }

    // 액션바 클릭 이벤트
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.loginAction) {
            Intent intent = new Intent(NavActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
