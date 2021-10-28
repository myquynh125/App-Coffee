package com.example.doanmon.Code.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.doanmon.Activity.EditFood;
import com.example.doanmon.Adapter.FoodAdapter;
import com.example.doanmon.Adapter.ImagesAdapter;
import com.example.doanmon.DAO.AppDatabase;
import com.example.doanmon.Database.OrderDatabase;
import com.example.doanmon.Entity.Foody;
import com.example.doanmon.Entity.Order;
import com.example.doanmon.Model.Image;
import com.example.doanmon.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private static final int MY_REQUEST_CODE = 100;
    private GridView GridViewIntro;
    private RecyclerView RecyclerViewFood;
    private ArrayList<Image> imageArrayList = new ArrayList<>();
    private ImagesAdapter images_adapter;
    private FoodAdapter foodAdapter;
    private HomeViewModel homeViewModel;
    private EditText SearchView;

    Context context;
    private AppDatabase db;
    private ArrayList<Foody> foodies;


    public View onCreateView(@NonNull LayoutInflater inflater,
        ViewGroup container, Bundle savedInstanceState) { homeViewModel
        = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        GridViewIntro = root.findViewById(R.id.gv_intro);


        RecyclerViewFood = root.findViewById(R.id.rcl_food);
        SearchView = root.findViewById(R.id.sv_serch);
        SearchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                 filter(s.toString());
            }
        });


        images_adapter = new ImagesAdapter(getContext(), R.layout.images_intro, imageArrayList);
        GridViewIntro.setAdapter(images_adapter);
        mapping();
        // Set Recy
        db = AppDatabase.getInstance(getContext());

        foodies = (ArrayList<Foody>) db.daoFood().FOODY_LIST();
        foodAdapter = new FoodAdapter((ArrayList<Foody>) foodies, getContext());

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        RecyclerViewFood.setLayoutManager(manager);
        RecyclerViewFood.setAdapter(foodAdapter);
        RecyclerViewFood.smoothScrollToPosition(RecyclerViewFood.getAdapter().getItemCount());
        foodAdapter.setOnItemClickListener(new FoodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final int Position) {



            }

            @Override
            public void deletefood(int Position) {
                Toast.makeText(getContext(), ""+Position, Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Bạn muốn ?");

                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ArrayList<Foody> foodies1 = (ArrayList<Foody>) db.daoFood().FOODY_LIST();
                        Foody foody= foodies1.get(Position);
                        db.daoFood().deleteFooy(foody);
                        try {
                            foodies = (ArrayList<Foody>) db.daoFood().FOODY_LIST();
                            foodAdapter = new FoodAdapter((ArrayList<Foody>) foodies, getContext());
                            RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
                            RecyclerViewFood.setLayoutManager(manager);
                            RecyclerViewFood.setAdapter(foodAdapter);
                        }catch (Exception e){
                            Log.e("ERROR",""+e);
                        }
                        Toast.makeText(getContext(), "Đã xóa "+foody.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }

            @Override
            public void insertItem(final int Position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Mua hàng");
                builder.setPositiveButton("Mua", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Foody foody = foodies.get(Position);
                        Order order = new Order();
                        order.setName(foody.getName());
                        order.setPrice(foody.getPrice());
                        order.setImage(foody.getImage());
                        OrderDatabase database = OrderDatabase.getInstance(getContext());
                        database.daoOrder().insertorder(order);
                        Toast.makeText(getContext(), "Đã thêm vào đơn hàng ", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
        return root;
    }




    private void filter(String Text) {
        ArrayList<Foody> foodyList = new ArrayList<>();
        for (Foody foody : foodies) {
            if (foody.getName().toLowerCase().contains(Text.toLowerCase())) {
                foodyList.add(foody);
            }
        }
        foodAdapter.FilterList(foodyList);
    }

    private void mapping() {
        imageArrayList.add(new Image("Trà sửa đồng giá ", R.drawable.now1));
        imageArrayList.add(new Image("Phí ship chỉ 10k", R.drawable.now3));
        imageArrayList.add(new Image("Giảm 40%", R.drawable.now4));
        imageArrayList.add(new Image("1 tặng 1", R.drawable.now5));

    }


}
