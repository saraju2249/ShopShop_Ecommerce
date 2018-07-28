package com.esperer.shopshop.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.esperer.shopshop.R;
import com.esperer.shopshop.constants.Constant;
import com.esperer.shopshop.models.StyleSingle;
import com.esperer.shopshop.ui.activities.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {




        ArrayList<StyleSingle> data = new ArrayList<>();
        Context context;
        public static int index  = 0;
        public  static  ArrayList<Integer> selectedList;



        public SearchAdapter(Context mContext,ArrayList<StyleSingle> data) {
            this.data = data;
            this.context = mContext;
            selectedList =  new ArrayList<>();
            for (int i = 0 ; i < data.size(); i++ )
            {
                selectedList.add(0);
            }


        }

        @Override
        public SearchAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list_items, parent, false);
            return new SearchAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(SearchAdapter.MyViewHolder holder, int position) {

            StyleSingle single = data.get(position);
            holder.product = single;

            holder.productName.setText(single.getProductName());
            holder.productBrand.setText(single.getBrandName());
            holder.productPrice.setText("Rs. "+single.getProductPrice());
            holder.productDiscount.setText("- "+single.getProductDiscount()+"%");
            Picasso.with(context).load(single.getProductImg()).into(holder.img);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            //TextView categoryName;
            ImageView img;
            public StyleSingle product;
            TextView productName, productBrand, productPrice, productDiscount;

            public MyViewHolder(View itemView) {
                super(itemView);

                img =  itemView.findViewById(R.id.productImage);
                productName = itemView.findViewById(R.id.product_name);
                productBrand = itemView.findViewById(R.id.brand);
                productPrice = itemView.findViewById(R.id.price);
                productDiscount = itemView.findViewById(R.id.discount);



                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {

                            //Toast.makeText(view.getContext(),brand.getBrandName(),Toast.LENGTH_LONG).show();

                            Intent detailActivity = new Intent(view.getContext(), DetailActivity.class);
                            detailActivity.putExtra(Constant.stylesKey,product );
                            view.getContext().startActivity(detailActivity);
                        }
                    }
                });
            }
        }
    }
