package com.example.doanmon.Activity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doanmon.R;

public class EditFood extends AppCompatActivity {
    EditText txt_name, txt_pri, txt_des;
    ImageButton img_cam,img_folder;
    ImageView imv_fol;
    Button btn_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_edit_food);
        txt_name=findViewById(R.id.edt_namefood_dal);
        txt_pri=findViewById(R.id.edt_price_dal);
        txt_des=findViewById(R.id.edt_review_dal);
        img_cam=findViewById(R.id.imv_camera_dal);
        img_folder=findViewById(R.id.imv_folder_dal);
        imv_fol=findViewById(R.id.imv_add_food_dal);
        btn_edit=findViewById(R.id.btn_edit_dal);
    }
}
