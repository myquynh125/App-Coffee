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
import com.example.doanmon.Entity.Order;
import com.example.doanmon.R;

import java.util.List;

public class OderAdapter extends RecyclerView.Adapter<OderAdapter.ViewHolder> {

    private Context Context;
    private List ListOrder;
    private OnItemClickListener Listener;

    public interface OnItemClickListener{
        void onItemClick(int Position);
        void deleteItem(int Position);
    }

    public void setOnItemClickListener(OnItemClickListener clickListener){
        Listener=clickListener;
    }

    public OderAdapter(Context Context, List ListOrder) {
        this.Context = Context;
        this.ListOrder = ListOrder;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context =parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View viewOrder = inflater.inflate(R.layout.item_oder,parent,false);
        ViewHolder holder = new ViewHolder(viewOrder,Listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = (Order) ListOrder.get(position);
        holder.TextViewName.setText(order.getName());
        holder.TextViewPrice.setText(String.valueOf(order.getPrice()));
        holder.ImageViewFood.setImageBitmap(DataConvert.ConvertBitmap(order.getImage()));

    }

    @Override
    public int getItemCount() {
        return ListOrder.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private TextView TextViewName;
        private TextView TextViewPrice;
       // private TextView TextViewSoluong;
        private ImageView ImageViewFood;
        private ImageView ButtonDelete;
        public ViewHolder(@NonNull View itemView, final OnItemClickListener onclickItem) {
            super(itemView);
            view=itemView;
            ImageViewFood=itemView.findViewById(R.id.imv_oder);
            TextViewName = itemView.findViewById(R.id.tv_nameorder);
            TextViewPrice=itemView.findViewById(R.id.tv_price_order);
            ButtonDelete=itemView.findViewById(R.id.imv_removeOrder);
            ButtonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onclickItem != null){
                        int Position = getAdapterPosition();
                        if ((Position != RecyclerView.NO_POSITION)){
                            onclickItem.deleteItem(Position);
                        }
                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onclickItem != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            onclickItem.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

}
