package com.example.qrsaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class SaveqrActivity extends AppCompatActivity {
    private ImageView iv;
    private String codeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saveqr);
        setTitle(R.string.shooting);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF3B6DFF));

        // 이미지뷰 설정
        iv = findViewById(R.id.shooting_scannedImage);

        // qr코드 스캔
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setOrientationLocked(false);
        integrator.setPrompt("QR 코드를 스캔하세요");
        integrator.initiateScan();

        // 재촬영 버튼 클릭
        Button reshooting = findViewById(R.id.shooting_reshootingBtn);
        reshooting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finish();
            startActivity(getIntent());
            }
        });

        // 실행버튼 클릭
        Button shootingExeBtn = findViewById(R.id.shooting_exeBtn);
        shootingExeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(codeData));
            startActivity(intent);
            finish();
            }
        });

        // 저장버트 클릭
        Button shootingSaveBtn = findViewById(R.id.shooting_saveBtn);
        shootingSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            DataBaseAdapter db = new DataBaseAdapter(SaveqrActivity.this);
            db.open();
            EditText shootingSaveTitle = findViewById(R.id.shooting_title);
            db.insertCode(shootingSaveTitle.getText().toString(),codeData);
            Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(codeData));
            startActivity(intent);
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
            Intent intent = new Intent(SaveqrActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // qr코드 촬영 후 데이터 페이지에 적용
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                finish();
                // todo
            } else {
                codeData = result.getContents();
                Toast.makeText(this, "Scanned: " + codeData, Toast.LENGTH_LONG).show();

                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try{
                    BitMatrix bitMatrix = multiFormatWriter.encode(codeData, BarcodeFormat.QR_CODE,200,200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    iv.setImageBitmap(bitmap);
                }catch (Exception e){}
                // todo
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
