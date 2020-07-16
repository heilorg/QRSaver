package com.example.qrsaver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class LoadqrActivity extends AppCompatActivity {

    private int id;
    private ImageView iv;
    private QRVO qr;
    private Bitmap bitmap;

    Button exeBtn;
    Button shareBtn;
    Button deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadqr);
        setTitle(R.string.storage);

        Intent intent = getIntent();

        // qr데이터 불러오기
        id = Integer.parseInt(intent.getExtras().getString("id"));
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
//        DataBaseAdapter db = new DataBaseAdapter(this);
//        db.open();
//        Cursor codes =  db.fetchCode(id);
//        qr = new QRVO();
//
//        if(codes != null){
//            if(codes.moveToFirst()){
//                String title = codes.getString(codes.getColumnIndex("title"));
//                String data = codes.getString(codes.getColumnIndex("data"));
//                String date = codes.getString(codes.getColumnIndex("date"));
//                qr.setTitle(title);
//                qr.setData(data);
//                qr.setDate(date);
//            }
//        }
//        db.close();
//
//        iv = findViewById(R.id.load_qr);
//
//        // qr데이터 적용
//        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
//        try{
//            BitMatrix bitMatrix = multiFormatWriter.encode(qr.getData(), BarcodeFormat.QR_CODE,200,200);
//            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
//            bitmap = barcodeEncoder.createBitmap(bitMatrix);
//            iv.setImageBitmap(bitmap);
//        }catch (Exception e){}
//        TextView title = (TextView)findViewById(R.id.load_title);
//        TextView date = (TextView)findViewById(R.id.load_date);
//        title.setText(qr.getTitle());
//        date.setText(qr.getDate());
//
//        // 실행버튼
//        exeBtn = findViewById(R.id.load_exeBtn);
//        exeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(qr.getData()));
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        // 공유버튼 클릭
//        shareBtn = findViewById(R.id.load_shareBtn);
//        shareBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
//                Uri screenshotUri = getImageUri(v.getContext(), bitmap);
//
//                sharingIntent.setType("image/png");
//                sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
//                startActivity(Intent.createChooser(sharingIntent, "Share image using"));
//            }
//        });
//
//        // 삭제 버튼 클릭
//        deleteBtn = findViewById(R.id.load_deleteBtn);
//        deleteBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DataBaseAdapter db = new DataBaseAdapter(v.getContext());
//                db.open();
//                db.deleteCode(id);
//                db.close();
//                Toast.makeText(v.getContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        });

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

    private Uri getImageUri(Context context, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}
