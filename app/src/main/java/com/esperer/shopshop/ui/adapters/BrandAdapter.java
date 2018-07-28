package com.esperer.shopshop.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import com.esperer.shopshop.R;
import com.esperer.shopshop.constants.Constant;
import com.esperer.shopshop.models.BrandSingle;
import com.esperer.shopshop.ui.activities.Styles;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.MyViewHolder> {

    ArrayList<BrandSingle> data = new ArrayList<>();
    Context context;



    public BrandAdapter(Context mContext,ArrayList<BrandSingle> data) {
        this.data = data;
        this.context = mContext;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brand_single, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        BrandSingle brandsingle = data.get(position);

        Picasso.with(context).load(brandsingle.getBrandImg()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //TextView categoryName;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            //categoryName = itemView.findViewById(R.id.category_id);
            image =  itemView.findViewById(R.id.brand_img);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION)
                    {   BrandSingle  brand = data.get(position);
                        Intent brandIntent = new Intent(context,Styles.class);
                        brandIntent.putExtra(Constant.brandKey,brand.getBrandName());
                        brandIntent.putExtra("brand","brand");
                        brandIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(brandIntent);

                        //Toast.makeText(view.getContext(),brand.getBrandName(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
