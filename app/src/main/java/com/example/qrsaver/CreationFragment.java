package com.example.qrsaver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreationFragment extends Fragment {
    ViewGroup viewGroup;
    public CreationFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_creation, container, false);

        // 콤보박스 설정
        Spinner spinner = (Spinner)viewGroup.findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.qr_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // 생성 버튼 이벤트 설정
        Button createQr = viewGroup.findViewById(R.id.createQr);
        final EditText createData = (EditText)viewGroup.findViewById(R.id.createData);
        createQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = createData.getText().toString().trim();
                if(!data.equals("")){
                    Intent intent = new Intent(getActivity(), CreateqrActivity.class);
                    intent.putExtra("data", data);
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), "빈 텍스트는 입력할 수 없습니다.", Toast.LENGTH_LONG).show();
                }
            }
        });
        return viewGroup;
    }
}
