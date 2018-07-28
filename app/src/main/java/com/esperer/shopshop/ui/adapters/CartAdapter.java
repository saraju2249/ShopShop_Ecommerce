package com.esperer.shopshop.ui.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.esperer.shopshop.R;
import com.esperer.shopshop.database.DatabaseHelper;
import com.esperer.shopshop.models.OrderSingle;

import com.esperer.shopshop.ui.activities.DetailsActivity;
import com.esperer.shopshop.ui.fragments.CartFragment;
import com.squareup.picasso.Picasso;
import java.util.List;

public class CartAdapter extends  RecyclerView.Adapter<CartAdapter.ViewHolder> {
     Context mContext;
     List<OrderSingle>orderList;




    public CartAdapter ( )
    {

    }

    public CartAdapter(Context mContext,  List<OrderSingle>orderList )
    {
        this.mContext = mContext;
        this.orderList = orderList;
    }


    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.cart_list_single, null);


        CartAdapter.ViewHolder cartViewHolder = new CartAdapter.ViewHolder(itemLayoutView);

        return cartViewHolder;
    }



    @Override
    public void onBindViewHolder(CartAdapter.ViewHolder holder, int position) {

        OrderSingle orderSingle = orderList.get(position);
        holder.order = orderSingle;

        holder.product_Name.setText(orderSingle.getProductName());
        holder.product_Brand.setText(orderSingle.getProductBrand());
        holder.price.setText("Rs."+orderSingle.getPrice());
        holder.quantity.setText(orderSingle.getQuantity()+"");
        Picasso.with(mContext).load(orderSingle.getProductImg()).into(holder.productImg);


    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
          private TextView product_Name, product_Brand, quantity,price;
          private ImageView productImg;
          private ImageView deleteCartItem;
          public OrderSingle order;

        public ViewHolder(View itemView) {
            super(itemView);


            product_Name = itemView.findViewById(R.id.cart_product_name);
            product_Brand =itemView.findViewById(R.id.cart_product_brand);
            quantity = itemView.findViewById(R.id.cart_quantity);
            price = itemView.findViewById(R.id.cart_price);
            productImg = itemView.findViewById(R.id.cart_product_img);

            deleteCartItem = itemView.findViewById(R.id.delete_cart_item);

              boolean  buttonPress;

            deleteCartItem.setOnClickListener(new View.OnClickListener() {




                @Override
                public void onClick(View v) {
                    DatabaseHelper helper = new DatabaseHelper(mContext);
                    Integer delete = helper.delete(order.getProductId()+"");

                    orderList.remove(order);

                    helper.getAllData();
                    notifyDataSetChanged();

                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    android.support.v4.app.Fragment f = new CartFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f).commit();



                }



            });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent detailsIntent = new Intent(mContext, DetailsActivity.class);
                    detailsIntent.putExtra("details",order.getProductId());
                    mContext.startActivity(detailsIntent);
                }
            });
        }
    }
}
