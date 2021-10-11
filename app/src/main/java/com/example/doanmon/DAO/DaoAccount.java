package com.example.doanmon.DAO;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.doanmon.Entity.Account;

import java.util.List;

@Dao
public interface DaoAccount  {
    

    @Query("select * from account_db")
    List <Account> ACCOUNT_LIST();

    @Insert()
    void insert_account(Account account);

    @Update
    void update_account (Account account);

    @Delete
    void delete_account( Account account);

}
