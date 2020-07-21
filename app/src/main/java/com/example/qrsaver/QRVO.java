package com.example.qrsaver;

// qr코드 데이터 저장 포맷
public class QRVO{
    private String title; // 제목
    private String date; // 생성 시간
    private String data; // 데이터
    private int id; // 아이디

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }
    public void setData(String data){
        this.data = data;
    }
    @Override
    public String toString() {
        return "AlbumVO{" +
                ", date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", data=" + data +
                '}';
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}