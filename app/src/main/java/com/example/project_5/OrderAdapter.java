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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrdersHolder>{

    private Context context;
    private View orderView;

    public OrderAdapter(Context context, @NonNull View orderView) {
        this.context = context;
        this.orderView = orderView;
    }
    @NonNull
    @Override
    public OrdersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_menu_item, parent, false);
        return new OrdersHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrdersHolder holder, int position) {
        String titleSetter = context.getResources().getString(R.string.coffee_title);
        if(Order.currOrder.menuList().get(position).isDonut()) {
            titleSetter = context.getResources().getString(R.string.donut_title);
        }
        holder.itemTitle.setText(titleSetter);
        holder.itemDesc.setText(Order.currOrder.menuList().get(position).toString());
        holder.itemPrice.setText(DecimalFormat.getCurrencyInstance()
                .format((Order.currOrder.menuList().get(position).itemPrice())));
        holder.itemImage.setImageResource(Order.currOrder.menuList().get(position).itemImage());
    }

    @Override
    public int getItemCount() {
        return Order.currOrder.menuList().size();
    }
    public static class OrdersHolder extends RecyclerView.ViewHolder {

        private ImageView itemImage;

        private TextView itemTitle;

        private TextView itemDesc;

        private TextView itemPrice;

        private Button removeItem;
        private ConstraintLayout parentLayout;

        public OrdersHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemDesc = itemView.findViewById(R.id.itemDesc);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            removeItem = itemView.findViewById(R.id.removeItem);
            parentLayout = itemView.findViewById(R.id.rowLayout);
            itemImage = itemView.findViewById(R.id.im_donut);
            setRemoveButtonOnClick(itemView);
        }
        /**
         * Set the onClickListener for the button on each row to give the option to remove an item.
         * Clicking on the button will create an AlertDialog with the options of YES/NO.
         * @param itemView the view corresponding to the row view clicked.
         */
        private void setRemoveButtonOnClick(@NonNull View itemView) {
            removeItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                    alert.setTitle("Remove from order? :" + itemTitle.getText().toString());
                    alert.setMessage(itemDesc.getText().toString());
                    alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Order.currOrder.removeItem(itemDesc.getText().toString());
                            Toast.makeText(itemView.getContext(),
                                    itemTitle.getText().toString() + " removed.", Toast.LENGTH_LONG).show();
                        }
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(itemView.getContext(),
                                    itemTitle.getText().toString() + " was not removed.", Toast.LENGTH_LONG).show();
                        }
                    });
                    AlertDialog dialog = alert.create();
                    dialog.show();
                }
            });
        }
    }
}
