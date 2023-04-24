package com.example.project_5;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrdersHolder>{

    private Context context;
    private Order currOrder;

    public OrderAdapter(Context context, Order currOrder) {
        this.context = context;
        this.currOrder = currOrder;
    }
    @NonNull
    @Override
    public OrdersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view, parent, false);
        return new OrdersHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrdersHolder holder, int position) {
        holder.menuTitle.setText(currOrder.menuList().get(position).toString());
        holder.itemPrice.setText(DecimalFormat.getCurrencyInstance()
                .format((currOrder.menuList().get(position).itemPrice())));
    }

    @Override
    public int getItemCount() {
        return currOrder.menuList().size();
    }
    public static class OrdersHolder extends RecyclerView.ViewHolder {

        private ImageView itemImage;

        private TextView menuTitle;

        private TextView itemDesc;

        private TextView itemPrice;

        private Button removeItem;

        public OrdersHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
