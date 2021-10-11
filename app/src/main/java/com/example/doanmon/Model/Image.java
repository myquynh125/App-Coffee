package com.example.doanmon.Model;

public class Image {
    int images;
    String text ;

    public Image( String text,int images) {
        this.text = text;
        this.images = images;
    }

    public Image() {
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
