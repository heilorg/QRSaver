package com.example.qrsaver;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class StorageFragment extends Fragment {
    private RecyclerView rcc_qr;
    private CodeAdapter codeAdapter;
    ViewGroup view;

    public StorageFragment() {
        // Required empty public constructor
    }

//https://re-build.tistory.com/42
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = (ViewGroup)inflater.inflate(R.layout.fragment_storage, container, false);
        rcc_qr = (RecyclerView) view.findViewById(R.id.rcc_qr);

        return view;
    }

    @Override
    public void onStart() {
        // 프래그먼트 생명주기 - 유저에게 보일때마다
        super.onStart();

        //데이터베이스 로드
        DataBaseAdapter db = new DataBaseAdapter(getActivity());
        db.open();
        Cursor codes =  db.fetchAllCode();
        ArrayList<QRVO> qrs = new ArrayList<QRVO>();

        // qr코드의 수만큼 반복
        if(codes != null){
            if(codes.moveToFirst()){
                do{
                    String title = codes.getString(codes.getColumnIndex("title"));
                    String data = codes.getString(codes.getColumnIndex("data"));
                    String date = codes.getString(codes.getColumnIndex("date"));
                    int id = codes.getInt(codes.getColumnIndex("id"));
                    Toast.makeText(getActivity(), id+"", Toast.LENGTH_SHORT).show();
                    QRVO qr = new QRVO();
                    qr.setTitle(title);
                    qr.setData(data);
                    qr.setDate(date);
                    qr.setId(id);
                    qrs.add(qr);

                }while(codes.moveToNext());
            }
        }

        // 리사이클러 뷰에 적용 (렌더)
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        rcc_qr.setLayoutManager(mLayoutManager);
        rcc_qr.addItemDecoration(new QRCodeDecoration(getActivity()));
        codeAdapter = new CodeAdapter(getActivity(), qrs);
        rcc_qr.setAdapter(codeAdapter);

        db.close();
    }
}
