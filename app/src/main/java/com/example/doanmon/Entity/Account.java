package com.example.doanmon.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "account_db")
public class Account {
    @PrimaryKey(autoGenerate = true)
    int id ;
    @ColumnInfo(name = "user")
    String user;
    @ColumnInfo(name = "pass")
    String pass;


    public Account(String user, String pass) {
        this.user = user;
        this.pass = pass;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


}
