package com.esperer.shopshop.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.esperer.shopshop.R;
import com.esperer.shopshop.constants.Constant;
import com.esperer.shopshop.models.CategorySingle;
import com.esperer.shopshop.ui.activities.Styles;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter  extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    ArrayList<CategorySingle> data = new ArrayList<>();
    Context context;



    public CategoryAdapter(Context mContext,ArrayList<CategorySingle> data) {
        this.data = data;
        this.context = mContext;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_single, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        CategorySingle singleCategory = data.get(position);

        holder.categoryName.setText(singleCategory.getCategoryName());
        Picasso.with(context).load(singleCategory.getCategoryImg()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.category_id);
            image =  itemView.findViewById(R.id.category_product_img);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION)
                    {
                        CategorySingle  category = data.get(position);
                        Intent styleIntent = new Intent(context,Styles.class);
                        styleIntent.putExtra(Constant.CategoryKey,category.getCategory());
                        styleIntent.putExtra("category","category");
                        styleIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(styleIntent);

                        //Toast.makeText(view.getContext(),category.getCategoryName(),Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
