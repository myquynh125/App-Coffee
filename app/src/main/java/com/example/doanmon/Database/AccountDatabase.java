package com.example.doanmon.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.doanmon.DAO.DaoAccount;
import com.example.doanmon.Entity.Account;


@Database(entities= Account.class, version = 1)
public abstract class AccountDatabase  extends RoomDatabase {

    private static final String DATABASE_NAME="account_db";

    public abstract DaoAccount daoAccount();

    private static AccountDatabase instance;

    public static synchronized AccountDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),AccountDatabase.class
                    ,DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
