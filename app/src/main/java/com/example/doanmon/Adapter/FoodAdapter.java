package com.example.doanmon.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.doanmon.Convert.DataConvert;
import com.example.doanmon.Entity.Foody;
import com.example.doanmon.R;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    ArrayList<Foody> foodyArrayList;
    Context context;

    private OnItemClickListener Listener;
    public interface OnItemClickListener{
        void onItemClick(int Position);
        void deleteItem(int Position);
    }
    public void setOnItemClickListener(OnItemClickListener clickListener){
        Listener=clickListener;
    }
    public FoodAdapter(ArrayList<Foody> foodyArrayList, Context context) {
        this.foodyArrayList = foodyArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context= parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        View Holder = inflater.inflate(R.layout.food_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(Holder,Listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Foody foody = foodyArrayList.get(position);
        holder.ImageViewFood.setImageBitmap(DataConvert.ConvertBitmap(foody.getImage()));
        holder.TextViewName.setText(foody.getName());
        holder.TextViewPrice.setText(foody.getPrice()+" VNĐ");
        holder.TextViewCategory.setText(foody.getCategory());
        holder.TextViewDetail.setText(foody.getDetail());
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        View view;
        ImageView ImageViewFood;
        TextView TextViewName;
        TextView TextViewCategory;
        TextView TextViewPrice;
        TextView TextViewDetail;
        ImageView nImageViewAddOrder;
        public ViewHolder(@NonNull View itemView , final OnItemClickListener listener) {
            super(itemView);
            view=itemView;
            ImageViewFood=itemView.findViewById(R.id.img_food);
            TextViewName=itemView.findViewById(R.id.tv_namefood);
            TextViewCategory=itemView.findViewById(R.id.tv_category);
            TextViewPrice=itemView.findViewById(R.id.tv_price);
            TextViewDetail=itemView.findViewById(R.id.tv_detail);
            nImageViewAddOrder=itemView.findViewById(R.id.imv_add_order);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }

            });
            nImageViewAddOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.deleteItem(position);
                        }
                    }
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return foodyArrayList.size();
    }
    public void FilterList(ArrayList<Foody> foodies){
        foodyArrayList= foodies;
        notifyDataSetChanged();
    }

}
