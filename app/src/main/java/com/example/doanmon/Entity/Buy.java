package com.example.doanmon.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "buy_db")
public class Buy {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "price")
    private Double price;
    public Buy() {
    }
    public Buy(Double price) {

        this.price = price;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}
