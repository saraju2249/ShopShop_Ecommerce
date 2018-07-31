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
import com.esperer.shopshop.models.StyleSingle;
import com.esperer.shopshop.ui.activities.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StylesAdapter extends  RecyclerView.Adapter<StylesAdapter.StylesViewHolder> {

    private List<StyleSingle> styles;

    private Context context;

    public StylesAdapter(Context mContext, List<StyleSingle> foods) {
        this.context = mContext;
        this.styles = foods;

    }


    @Override
    public StylesViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.styles_single, null);



        StylesViewHolder userViewHolder = new StylesViewHolder(itemLayoutView);

        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(StylesViewHolder foodViewHolder, int position) {

        StyleSingle styleSingle = styles.get(position);
        foodViewHolder.product = styleSingle;



        foodViewHolder.productName.setText(styleSingle.getProductName() );
        foodViewHolder.productBrand.setText(styleSingle.getBrandName() );
        foodViewHolder.productPrice.setText("Rs."+styleSingle.getProductPrice() );
        foodViewHolder.productDiscount.setText("- "+styleSingle.getProductDiscount()+ "%" );
        Picasso.with(context).load(styleSingle.getProductImg()).into(foodViewHolder.productImg);


    }

    @Override
    public int getItemCount() {
        return styles.size();
    }

    public static class StylesViewHolder extends RecyclerView.ViewHolder {

        private TextView productName,productBrand,productPrice,productDiscount, categoryName;

        public ImageView productImg;

        public StyleSingle product;

        public StylesViewHolder(View itemLayoutView) {
            super(itemLayoutView);



            productName    =  itemLayoutView.findViewById(R.id.styles_name);
            productBrand    =  itemLayoutView.findViewById(R.id.styles_brand);
            productPrice   =  itemLayoutView.findViewById(R.id.price_styles);
            productDiscount = itemLayoutView.findViewById(R.id.discount_styles);
            productImg      = itemLayoutView.findViewById(R.id.styles_img);


            itemLayoutView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                    //Toast.makeText(view.getContext(), product.getProductName() , Toast.LENGTH_SHORT).show();


                    Intent detailActivity = new Intent(view.getContext(), DetailActivity.class);
                    detailActivity.putExtra(Constant.stylesKey,product );
                    view.getContext().startActivity(detailActivity);


                }


            });

            itemLayoutView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override

                public boolean onLongClick(View view) {



                    Toast.makeText(view.getContext(),product.getProductDesc(), Toast.LENGTH_LONG).show();

                    return true;

                }

            });


        }



    }

    }


