package com.example.doanmon.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.doanmon.Model.Image;
import com.example.doanmon.R;

import java.util.ArrayList;

public class ImagesAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Image> imageList;

    public ImagesAdapter(Context context, int layout, ArrayList<Image> imageList) {
        this.context = context;
        this.layout = layout;
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;
        if(convertView == null){
            holder= new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder.imageView = convertView.findViewById(R.id.img_intro);
            holder.textView = convertView.findViewById(R.id.tv_intro);
            convertView.setTag(holder);
        }
        else{
           holder= (ViewHolder) convertView.getTag();
        }
        Image image = imageList.get(position);
        holder.imageView.setImageResource(image.getImages());
        holder.textView.setText(image.getText());

        return convertView;
    }
}
