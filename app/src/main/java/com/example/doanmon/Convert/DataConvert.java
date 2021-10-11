package com.example.doanmon.Convert;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class DataConvert {
    public static byte [] ConvertImages (Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,0,stream);
        return stream.toByteArray();
    }

    public static Bitmap ConvertBitmap (byte [] arr){
        return BitmapFactory.decodeByteArray(arr,0,arr.length);
    }
}
