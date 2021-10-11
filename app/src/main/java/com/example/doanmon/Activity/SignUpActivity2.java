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

public class SignUpActivity2 extends AppCompatActivity {
    private EditText EditText_user;
    private EditText EditText_pass1;
    private EditText EditText_pass2;
    private Button Button_signup;
    private Context context;
    private List<Account> accountList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
        mapping();
        Button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String us = EditText_user.getText().toString();
                String pas1 = EditText_pass1.getText().toString();
                String pas2 =EditText_pass2.getText().toString();
                AccountDatabase database = AccountDatabase.getInstance(getApplicationContext());
                accountList = database.daoAccount().ACCOUNT_LIST();
                for (Account account : accountList){
                    if (us.equals(account.getUser())){
                        EditText_user.setError("Tên đăng nhập đã tồn tại");
                        return;
                    }
                }
                if(us.isEmpty()){
                    EditText_user.setError("Tên đăng nhập trống");
                    return;
                }
                if(pas1.isEmpty()){
                    EditText_pass1.setError("Mật khẩu trống");
                    return;
                }
                if(pas1.equals(pas2) == false){
                    EditText_pass2.setError("Xác nhận không đúng !");
                    return;
                }
                else{
                    try {
                        Account account = new Account(us,pas1);
                        AccountDatabase database2 =AccountDatabase.getInstance(getApplicationContext());
                        database.daoAccount().insert_account(account);
                        Intent intent =new Intent(SignUpActivity2.this,MainActivity.class);
                        Toast.makeText(getApplicationContext(),
                        "Đăng kí thành công !", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }catch (Exception e){
                        Log.e("ERRO",""+e);
                    }

                }


            }
        });
    }

    private void mapping() {
        EditText_user=findViewById(R.id.edt_user_sup);
        EditText_pass1=findViewById(R.id.edt_pass_sup);
        EditText_pass2=findViewById(R.id.edt_pass2_sup);
        Button_signup=findViewById(R.id.btn_signup);
    }
}