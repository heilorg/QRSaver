package com.example.qrsaver;

public class QRVO{
    private String title;
    private String date;
    private String data;
    private int id;

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