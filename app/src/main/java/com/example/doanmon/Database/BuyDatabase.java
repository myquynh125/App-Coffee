package com.example.doanmon.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.doanmon.DAO.DaoBuy;
import com.example.doanmon.Entity.Buy;


@Database(entities = Buy.class,version = 1)
public abstract class BuyDatabase extends RoomDatabase {
    private static final String DATABASE_NAME="buy_db";
    public abstract DaoBuy daoBuy();
    private static BuyDatabase instance;

    public static synchronized BuyDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(), BuyDatabase.class
                    ,DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
