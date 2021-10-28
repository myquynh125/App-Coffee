package com.example.doanmon.Activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.doanmon.Convert.DataConvert;
import com.example.doanmon.DAO.AppDatabase;
import com.example.doanmon.Entity.Foody;
import com.example.doanmon.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EditFood extends Fragment {
    private EditText txt_name, txt_pri, txt_des;
    private ImageButton img_cam,img_folder;
    private ImageView imv_fol;
    private Button btn_edit;
    private Spinner spinner;
    private Foody foody;
    private List<String> list;
    private EditFoodViewModel editFoodViewModel;

    Context context;
    Bitmap bitmapImages = null;
    private static final int REQUESTCODE_CAMERA = 777;
    private static final int REQUESTCODE_FOLDER = 999;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        editFoodViewModel = ViewModelProviders.of(this).get(EditFoodViewModel.class);
        View root = inflater.inflate(R.layout.dialog_edit_food, container, false);
        txt_name=root.findViewById(R.id.edt_namefood_dal);
        txt_pri=root.findViewById(R.id.edt_price_dal);
        txt_des=root.findViewById(R.id.edt_review_dal);
        spinner = root.findViewById(R.id.txt_food);
        list = new ArrayList<>();
        list.add("coffee");
        list.add("tea");
        list.add("smoothie");
        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, list);
        spinner.setAdapter(adapter);
        img_folder=root.findViewById(R.id.imv_folder_dal);
        imv_fol=root.findViewById(R.id.imv_add_food_dal);
        btn_edit=root.findViewById(R.id.btn_edit_dal);
        if(foody!=null){
            txt_name.setText(foody.getName());
            txt_des.setText(foody.getDetail());

            txt_pri.setText(String.valueOf(foody.getPrice()));

        }

        img_folder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (img_folder==null){
                    return;
                }
                else {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, REQUESTCODE_FOLDER);
                }

            }
        });
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loai = spinner.getSelectedItem().toString().trim();
                String name = txt_name.getText().toString().trim();
                String pri = txt_pri.getText().toString().trim();
                String review = txt_des.getText().toString().trim();
                try {
                    if (name.isEmpty()) {
                        return;
                    }
                    if (pri.isEmpty()) {
                        return;
                    }
                    if (review.isEmpty()) {
                        return;
                    }
                } catch (Exception e) {
                    Log.e("ERRO", "" + e);
                }

                try {
                    AppDatabase db = AppDatabase.getInstance(getContext());
                    foody.setName(name);
                    foody.setCategory(loai);
                    Double price = Double.parseDouble(pri);
                    foody.setPrice(price);
                    foody.setDetail(review);
                    foody.setImage(DataConvert.ConvertImages(bitmapImages));
                    db.daoFood().updateFoody(foody);
                    Intent intentre = new Intent(getContext(), Home_Activity2.class);
                    startActivity(intentre);

                } catch (Exception e) {
                    Log.e("ERRO", "" + e);
                }
            }
        });
        return root;
    }
}
