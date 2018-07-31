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
import com.esperer.shopshop.models.TendingSingle;
import com.esperer.shopshop.ui.activities.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TendingAdapter extends RecyclerView.Adapter<TendingAdapter.MyViewHolder> {

    ArrayList<TendingSingle> data = new ArrayList<>();
    Context context;



    public TendingAdapter(ArrayList<TendingSingle> data,Context context) {
        this.data = data;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trending_single, parent, false);
        return new MyViewHolder(view);



    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        TendingSingle singleTrending = data.get(position);
        holder.trending = singleTrending;

        holder.trending_name.setText(data.get(position).getProductName());
        Picasso.with(context).load(singleTrending.getProductImg()).into(holder.trending_img);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView trending_name;
        ImageView trending_img;
        TendingSingle trending;

        public MyViewHolder(View itemView) {
            super(itemView);
            trending_name = itemView.findViewById(R.id.trending_name);

            trending_img =  itemView.findViewById(R.id.trending_img);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION)
                    {
                        //TendingSingle trending = data.get(position);

                        //Toast.makeText(view.getContext(),trending.getBrandName(),Toast.LENGTH_LONG).show();
//
                        Intent detail = new Intent(view.getContext(), DetailActivity.class);
                        detail.putExtra("tending", trending);
                        view.getContext().startActivity(detail);
                    }
                }
            });
        }
    }
}
