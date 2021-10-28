package com.example.doanmon.Code.Admin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.doanmon.Activity.MainActivity;
import com.example.doanmon.Adapter.FoodAdapter;
import com.example.doanmon.DAO.AppDatabase;
import com.example.doanmon.Database.AccountDatabase;
import com.example.doanmon.Database.BuyDatabase;
import com.example.doanmon.Entity.Account;
import com.example.doanmon.Entity.Buy;
import com.example.doanmon.Entity.Foody;
import com.example.doanmon.R;

import java.util.ArrayList;
import java.util.List;

public class MeFragment extends Fragment {
    private Button Button_logout;
    private TextView TextView_name_me;
    private ListView ListView_me;
    TextView TextViewThunhap;
    TextView TextViewSoluong;
    TextView TextViewSoluongdangban;
    TextView TextViewSoluongtaikhoan;
    private RecyclerView recyclerView;
    private AppDatabase db;
    private ArrayList<Foody> foodies;
    Button buttondsacc;
    private MeViewModel meViewModel;
    private Context context;
    private FoodAdapter foodAdapter;
    ArrayList <String> arr = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
    ViewGroup container, Bundle savedInstanceState) {
        meViewModel =
        ViewModelProviders.of(this).get(MeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_me, container, false);
        Button_logout=root.findViewById(R.id.btn_logout);

        Button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        TextViewThunhap= root.findViewById(R.id.tv_thunhap);
        TextViewSoluong= root.findViewById(R.id.tv_soluong);
        TextViewSoluongdangban=root.findViewById(R.id.tv_soluongdangban);
        TextViewSoluongtaikhoan=root.findViewById(R.id.tv_soluongtaikhoan);
        AccountDatabase database3 = AccountDatabase.getInstance(getContext());
        List<Account> accounts = database3.daoAccount().ACCOUNT_LIST();
        TextViewSoluongtaikhoan.setText("Số tài khoản "+accounts.size());


        BuyDatabase database = BuyDatabase.getInstance(getContext());
        AppDatabase database1 = AppDatabase.getInstance(getContext());
        List<Foody> foodyList = database1.daoFood().FOODY_LIST();
        TextViewSoluongdangban.setText("Sản phẩm đang có : "+foodyList.size());

      List<Buy> buyList = database.daoBuy().BUY_LIST();
      double tn= 0.0;
      for (Buy b : buyList){
          tn += b.getPrice();
      }
      TextViewThunhap.setText("Tổng thu nhập : "+tn+" VND");
      TextViewSoluong.setText("Đơn đã bán : "+buyList.size());


        return root;

    }
}