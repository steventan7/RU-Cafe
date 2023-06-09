package com.example.project_5;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This is an Adapter class used to instantiate an adapter for the DonutRecyclerView.
 * @author Steven Tan, David Fabian
 */
class DonutAdapter extends RecyclerView.Adapter<DonutAdapter.DonutsHolder>{

    private Context context;
    private ArrayList<Donut> donuts;
    private ArrayList<Donut> selectedDonuts;

    /**
     * Constructs a DonutAdapter instance used to populate the DonutRecyclerView
     * @param context the context details for obtaining the contents of the recycler view
     * @param items the list of donuts provided by the cafe
     */
    public DonutAdapter(Context context, ArrayList<Donut> items) {
        this.context = context;
        this.donuts = items;
    }

    /**
     * Creates a LayoutInflater object that is used to create each individual donut row of the recycler
     * @param parent the parent of the ViewGroup
     * @param viewType the type of view that the inflater is converted to
     */
    @NonNull
    @Override
    public DonutsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view, parent, false);
        return new DonutsHolder(view);
    }

    /**
     * Populates the values of each donut holder with regard to the donut name, price, and item image
     * @param holder the current DonutHolder
     * @param position the position of the donut in the recyclerview
     */
    @Override
    public void onBindViewHolder(@NonNull DonutsHolder holder, int position) {
        holder.tv_name.setText(donuts.get(position).donutFlavor());
        holder.tv_price.setText(DecimalFormat.getCurrencyInstance()
                .format((donuts.get(position).itemPrice())));
        holder.im_item.setImageResource(donuts.get(position).donutImage());
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.quantity, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.quantitySpinner.setAdapter(adapter);
        holder.quantitySpinner.setSelection(0);
        holder.donutType = donuts.get(position).donutType();
    }

    /**
     * Calls on the .size() function to obtain the number of donuts
     * @return the number of donuts in the donuts ArrayList
     */
    @Override
    public int getItemCount() {
        return donuts.size();
    }

    /**
     * This is an inner class that extends the RecyclerView class that is used to create the contents of each row
     * of the DonutRecyclerView
     */
    public static class DonutsHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_price;
        private String donutType;
        private ImageView im_item;
        private Button btn_add;
        private Spinner quantitySpinner;

        /**
         * Sets the corrosponding values of each element of the donut rows to their respective name, price, and image
         * Contains a button that allows the user to add the donut to order when clicked
         * @param itemView the view that is being added to the RecyclerView
         */
        public DonutsHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.donut_flavor);
            tv_price = itemView.findViewById(R.id.tv_price);
            im_item = itemView.findViewById(R.id.im_donut);
            btn_add = itemView.findViewById(R.id.btn_add);
            quantitySpinner = itemView.findViewById(R.id.donutquantity);
            setAddButtonOnClick(itemView);
        }

        /**
         * Set the onClickListener for the button on each row.
         * Clicking on the button will create an AlertDialog with the options of YES/NO.
         * @param itemView the view corresponding to the row view clicked.
         */
        private void setAddButtonOnClick(@NonNull View itemView) {
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Integer.parseInt(quantitySpinner.getSelectedItem().toString()) == 0) {
                        Toast.makeText(itemView.getContext(),
                                R.string.empty_donut_message, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                    alert.setTitle("Add to order");
                    alert.setMessage(tv_name.getText().toString());
                    alert.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if(Order.currOrder == null) {
                                Order.currOrder = new Order();
                            }
                            Order.currOrder.addItem(new Donut(donutType,
                                    tv_name.getText().toString(),
                                    Integer.parseInt(quantitySpinner.getSelectedItem().toString()),
                                    0));
                            Toast.makeText(itemView.getContext(),
                                    tv_name.getText().toString() + " added.", Toast.LENGTH_LONG).show();
                        }
                    }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(itemView.getContext(),
                                    tv_name.getText().toString() + " not added.", Toast.LENGTH_LONG).show();
                        }
                    });
                    AlertDialog dialog = alert.create();
                    dialog.show();
                }
            });
        }
    }
}
