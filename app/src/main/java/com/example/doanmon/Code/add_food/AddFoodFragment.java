package com.example.doanmon.Code.add_food;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


import com.example.doanmon.Activity.Home_Activity2;
import com.example.doanmon.Convert.DataConvert;
import com.example.doanmon.DAO.AppDatabase;
import com.example.doanmon.Entity.Foody;
import com.example.doanmon.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AddFoodFragment extends Fragment {
    private static final int REQUESTCODE_CAMERA = 777;
    private static final int REQUESTCODE_FOLDER = 999;
    private EditText EditText_name;
    private EditText EditText_price;
    private EditText EditText_discribe;
    private Spinner Spinner_food;
    private Button Button_add;
    private ImageButton imageButton_camera;
    private ImageButton imageButton_folder;
    private ImageView ImageView_imagesfood;
    Context context;
    private List<String> list;
    Bitmap bitmapImages = null;

    private TextView textView;
    private AddFoodVIewModel addFoodVIewModel;
    private View Button_folder;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addFoodVIewModel =
                ViewModelProviders.of(this).get(AddFoodVIewModel.class);
        View root = inflater.inflate(R.layout.fragment_addfood, container, false);
        EditText_name = root.findViewById(R.id.edt_namefood);
        EditText_price = root.findViewById(R.id.edt_price);
        EditText_discribe = root.findViewById(R.id.edt_describe);
        Button_add = root.findViewById(R.id.btn_addfood);
        imageButton_camera = root.findViewById(R.id.imv_camera);
        //imageButton_folder = root.findViewById(R.id.imv_folder);
        Button_folder = root.findViewById(R.id.btn_folder);

        ImageView_imagesfood = root.findViewById(R.id.imv_add_food);
        Spinner_food = root.findViewById(R.id.spn_food);
        list = new ArrayList<>();
        list.add("coffee");
        list.add("tea");
        list.add("smoothie");
        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, list);
        Spinner_food.setAdapter(adapter);
        EditText_price.setInputType(InputType.TYPE_CLASS_NUMBER |
                InputType.TYPE_NUMBER_VARIATION_NORMAL);
        imageButton_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera, REQUESTCODE_CAMERA);

            }
        });

        Button_folder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUESTCODE_FOLDER);
            }
        });

        Button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loai = Spinner_food.getSelectedItem().toString();
                String name = EditText_name.getText().toString();
                String pri = EditText_price.getText().toString();
                String review = EditText_discribe.getText().toString();
                try {
                    if (name.isEmpty()) {
                        EditText_name.setError("Nhập tên sản phẩm!");
                        return;
                    }
                    if (pri.isEmpty()) {
                        EditText_price.setError("Giá sản phẩm");
                        return;
                    }
                    if (review.isEmpty()) {
                        EditText_discribe.setError("Mô tả drink !");
                        return;
                    }
                } catch (Exception e) {
                    Log.e("ERRO", "" + e);
                }

                try {
                    AppDatabase db = AppDatabase.getInstance(getContext());
                    Foody foody = new Foody();
                    foody.setName(name);
                    foody.setCategory(loai);
                    Double price = Double.parseDouble(pri);
                    foody.setPrice(price);
                    foody.setDetail(review);
                    foody.setImage(DataConvert.ConvertImages(bitmapImages));
                    db.daoFood().insertFoody(foody);
                    Intent intent = new Intent(getContext(), Home_Activity2.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Log.e("ERRO", "" + e);
                }
            }
        });

        return root;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUESTCODE_CAMERA && resultCode == Activity.RESULT_OK && data != null) ;
        {
            try {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                ImageView_imagesfood.setImageBitmap(bitmap);
                bitmapImages = bitmap;

            } catch (Exception e) {
                Log.e("erro", "" + e);
            }

        }
        if (requestCode == REQUESTCODE_FOLDER && resultCode == Activity.RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContext().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                bitmapImages = bitmap;
                ImageView_imagesfood.setImageURI(data.getData());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Toast.makeText(getContext(), "Thêm ảnh thành công !", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}





