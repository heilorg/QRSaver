package com.example.qrsaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import android.graphics.Bitmap;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
public class CreateqrActivity extends AppCompatActivity {
    private ImageView iv;
    private String codeData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createqr);
        setTitle(R.string.create);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF3B6DFF));

        iv = (ImageView)findViewById(R.id.createdQR);

        Intent intent = getIntent();
        codeData = intent.getExtras().getString("data");

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try{
            BitMatrix bitMatrix = multiFormatWriter.encode(codeData, BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            iv.setImageBitmap(bitmap);
        }catch (Exception e){}

        Button recreateBtn = findViewById(R.id.create_recreateBtn);
        recreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button createExeBtn = findViewById(R.id.create_exeBtn);
        createExeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(codeData));
                startActivity(intent);
                finish();
            }
        });

        Button saveAndExeBtn = findViewById(R.id.create_saveBtn);
        saveAndExeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseAdapter db = new DataBaseAdapter(CreateqrActivity.this);
                db.open();
                EditText createTitle = findViewById(R.id.createTitle);
                db.insertCode(createTitle.getText().toString(), codeData);
                db.close();
                finish();
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
            Intent intent = new Intent(CreateqrActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
