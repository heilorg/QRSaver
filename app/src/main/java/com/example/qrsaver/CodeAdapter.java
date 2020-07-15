package com.example.qrsaver;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;

public class CodeAdapter extends RecyclerView.Adapter<CodeAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<QRVO> list_qr;

    private OnItemClickListener mListener = null ;

    public interface OnItemClickListener {
        void onItemClick(View v, int position) ;
    }

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }

    public CodeAdapter(Context mContext, ArrayList<QRVO> list_qr) {
        this.mContext = mContext;
        this.list_qr = list_qr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(mContext).inflate(R.layout.qr_item, parent, false);
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        QRVO qrVO = list_qr.get(position);

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try{
            BitMatrix bitMatrix = multiFormatWriter.encode(qrVO.getData(), BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            holder.qr_img.setImageBitmap(bitmap);
        }catch (Exception e){}
        holder.qr_title.setText(qrVO.getTitle());
        holder.qr_date.setText(qrVO.getDate());
        holder.qr_panel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
//                Toast.makeText(context, position + "", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, LoadqrActivity.class);
                intent.putExtra("position", position+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_qr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout qr_panel;
        private ImageView qr_img;
        private TextView qr_title;
        private TextView qr_date;

        public ViewHolder(View convertView) {
            super(convertView);

            qr_panel = (LinearLayout) convertView.findViewById(R.id.qr_panel);
            qr_img = (ImageView) convertView.findViewById(R.id.qr_img);
            qr_title = (TextView) convertView.findViewById(R.id.qr_title);
            qr_date = (TextView) convertView.findViewById(R.id.qr_date);

//            convertView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition();
//
//                    if(position != RecyclerView.NO_POSITION){
//                        if(mListener !=null){
//                            mListener.onItemClick(v,position);
//                        }
//                    }
//                }
//            });
        }
    }


}

