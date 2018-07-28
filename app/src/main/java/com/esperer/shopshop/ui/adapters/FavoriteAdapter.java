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
import com.esperer.shopshop.database.databaseFavorite;
import com.esperer.shopshop.models.FavoriteSingle;

import com.esperer.shopshop.ui.activities.DetailsActivity;
import com.esperer.shopshop.ui.fragments.FavoriteFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteAdapter extends  RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private Context mContext;
    private List<FavoriteSingle>favoriteList;



    public FavoriteAdapter( )
    {

    }

    public FavoriteAdapter(Context mContext, List<FavoriteSingle>favoriteList )
    {
        this.mContext = mContext;
        this.favoriteList = favoriteList;
    }


    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.favorite_list_single, null);


        FavoriteAdapter.ViewHolder favoriteViewHolder = new FavoriteAdapter.ViewHolder(itemLayoutView);

        return favoriteViewHolder;
    }



    @Override
    public void onBindViewHolder(FavoriteAdapter.ViewHolder holder, int position) {

        FavoriteSingle favoriteSingle = favoriteList.get(position);
        holder.favoriteSingle = favoriteSingle;

        holder.product_Name.setText(favoriteSingle.getProductName());
        holder.product_Brand.setText(favoriteSingle.getProductBrand());
        holder.price.setText("Rs."+favoriteSingle.getPrice());
        Picasso.with(mContext).load(favoriteSingle.getProductImg()).into(holder.productImg);


    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
          private TextView product_Name, product_Brand, quantity,price;
          private ImageView productImg;
          private ImageView deleteFavoriteItem;
          public FavoriteSingle favoriteSingle;

        public ViewHolder(View itemView) {
            super(itemView);


            product_Name = itemView.findViewById(R.id.favorite_product_name);
            product_Brand =itemView.findViewById(R.id.favorite_product_brand);
            price = itemView.findViewById(R.id.favorite_price);
            productImg = itemView.findViewById(R.id.favorite_product_img);
            deleteFavoriteItem = itemView.findViewById(R.id.delete_favorite_item);

              boolean  buttonPress;

            deleteFavoriteItem.setOnClickListener(new View.OnClickListener() {




                @Override
                public void onClick(View v) {
                   databaseFavorite favorite = new databaseFavorite(mContext);
                    Integer delete = favorite.delete(favoriteSingle.getProductId()+"");

                    favoriteList.remove(favorite);

                    favorite.getAllData();
                    notifyDataSetChanged();

                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    android.support.v4.app.Fragment f = new FavoriteFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f).commit();




                }



            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {




                    Intent detailsIntent = new Intent(mContext, DetailsActivity.class);
                    detailsIntent.putExtra("details",favoriteSingle.getProductId());
                    mContext.startActivity(detailsIntent);


                }


            });
        }
    }
}
