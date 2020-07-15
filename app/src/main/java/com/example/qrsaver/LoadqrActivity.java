package com.example.qrsaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class LoadqrActivity extends AppCompatActivity {

    private int position;
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadqr);
        setTitle(R.string.storage);

        Intent intent = getIntent();

        position = Integer.parseInt(intent.getExtras().getString("position")) + 1;
        DataBaseAdapter db = new DataBaseAdapter(this);
        db.open();

        Cursor codes =  db.fetchCode(position);
        QRVO qr = new QRVO();

        if(codes != null){
            if(codes.moveToFirst()){
                String title = codes.getString(codes.getColumnIndex("title"));
                String data = codes.getString(codes.getColumnIndex("data"));
                String date = codes.getString(codes.getColumnIndex("date"));
                qr.setTitle(title);
                qr.setData(data);
                qr.setDate(date);
            }
        }

        iv = findViewById(R.id.load_qr);

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try{
            BitMatrix bitMatrix = multiFormatWriter.encode(qr.getData(), BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            iv.setImageBitmap(bitmap);
        }catch (Exception e){}
        TextView title = (TextView)findViewById(R.id.load_title);
        TextView date = (TextView)findViewById(R.id.load_date);
        title.setText(qr.getTitle());
        date.setText(qr.getDate());

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF3B6DFF));
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
            Intent intent = new Intent(LoadqrActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
