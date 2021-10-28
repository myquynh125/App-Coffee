package com.example.doanmon.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "food_db")
public class Foody implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "category")
    private String category;
    @ColumnInfo(name = "price")
    private Double price;
    @ColumnInfo(name = "detail")
    private String detail;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte [] image;

    public Foody() {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.detail = detail;
    }



    @Ignore
    public Foody(String name, String category, Double price, String detail) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.detail = detail;
    }

    public Foody(String name, String category, Double price, String detail, byte[] image) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.detail = detail;
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
