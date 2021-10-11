package com.example.doanmon.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.doanmon.Database.AccountDatabase;
import com.example.doanmon.Entity.Account;
import com.example.doanmon.R;

import java.util.List;

public class ResetAccountActivity extends AppCompatActivity {
    private EditText EditText_user;
    private EditText EditText_pas1;
    private EditText EditText_pas2;
    private Button Button_reset;
    private List <Account> accountList ;
    private Context context;
    private Account account_update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_account);
        EditText_user=findViewById(R.id.edt_user_reset);
        EditText_pas1 = findViewById(R.id.edt_pass1_reset);
        EditText_pas2 = findViewById(R.id.edt_pass2_reset);
        Button_reset = findViewById(R.id.btn_reset_pass);

        Button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user= EditText_user.getText().toString();
                String pass1= EditText_pas1.getText().toString();
                String pass2= EditText_pas2.getText().toString();
                String us = null;


                AccountDatabase database =AccountDatabase.getInstance(getApplicationContext());
                accountList = database.daoAccount().ACCOUNT_LIST();

                for ( Account account : accountList){
                    if(user.equals(account.getUser())){
                        account_update=account;
                        us=account.getUser();
                        break;
                    }
                }

                if(user.equals(us) == false){
                    EditText_user.setError("Tài khoản không tồn tại");
                    return;
                }
                if(pass1.isEmpty()){
                    EditText_pas1.setError("Mật khẩu trống");
                    return;
                }
                if(pass2.isEmpty()){
                    EditText_pas2.setError("Mật khẩu trống");
                    return;
                }
                if(pass2.equals(pass1) == false){
                    EditText_pas2.setError("Xác nhận mật khẩu không đúng");
                    return;
                }
                else{
                    account_update.setPass(pass2);
                    try {
                        database.daoAccount().update_account(account_update);
                        Toast.makeText(getApplicationContext(), "Mật khẩu của bạn đã được thay đổi", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ResetAccountActivity.this,MainActivity.class);
                        startActivity(intent);
                    }catch (Exception e){
                        Log.e("ERRO",""+e);
                    }

                }
            }
        });
    }
}