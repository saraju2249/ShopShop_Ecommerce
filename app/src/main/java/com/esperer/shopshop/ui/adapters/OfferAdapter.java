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
import com.esperer.shopshop.models.OfferSingle;
import com.esperer.shopshop.ui.activities.Styles;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.MyViewHolder> {

    ArrayList<OfferSingle> data = new ArrayList<>();
    Context context;



    public OfferAdapter(Context mContext,ArrayList<OfferSingle> data) {
        this.data = data;
        this.context = mContext;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_single, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        OfferSingle singleOffer = data.get(position);


       // holder.offerCategoryName.setText(singleOffer.getCategoryName());

        Picasso.with(context).load(singleOffer.getOfferImg()).into(holder.offerCategoryImg);

        //Log.d("main",singleOffer.getCategoryName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView offerCategoryName;
        TextView offerDiscount;
        ImageView offerCategoryImg;


        public OfferSingle product;


        public MyViewHolder(View itemView) {
            super(itemView);
           // offerCategoryName = itemView.findViewById(R.id.discount_category);
            //offerDiscount = itemView.findViewById(R.id.min_discount);
            offerCategoryImg =  itemView.findViewById(R.id.discount_img);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION)
                    {   OfferSingle  offersingle = data.get(position);
                        Intent styleActivity = new Intent(view.getContext(), Styles.class);

                        styleActivity.putExtra(Constant.offerType,offersingle.getType());
                        styleActivity.putExtra("offerCat",offersingle.getCategoryName());
                        styleActivity.putExtra("offerDisc",offersingle.getDiscount() );
                        styleActivity.putExtra("offerBrandName",offersingle.getOfferBrandName() );

                        styleActivity.putExtra("offer","offer" );

                        context.startActivity(styleActivity);


//                        Toast.makeText(view.getContext(),offersingle.getDiscount()+"",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
