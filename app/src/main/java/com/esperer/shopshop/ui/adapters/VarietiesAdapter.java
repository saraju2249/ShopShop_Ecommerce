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
import com.esperer.shopshop.models.VarietiesSingle;
import com.esperer.shopshop.ui.activities.Styles;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VarietiesAdapter extends RecyclerView.Adapter<VarietiesAdapter.MyViewHolder> {

    ArrayList<VarietiesSingle> data = new ArrayList<>();
    Context context;



    public VarietiesAdapter(Context mContext,ArrayList<VarietiesSingle> data) {
        this.data = data;
        this.context = mContext;


    }

    @Override
    public VarietiesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.varieties_single, parent, false);
        return new VarietiesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VarietiesAdapter.MyViewHolder holder, int position) {

        VarietiesSingle singleVarieties = data.get(position);

        holder.varietiesName.setText(singleVarieties.getVarietiesName());
        Picasso.with(context).load(singleVarieties.getVarietiesImg()).into(holder.varietiesImg);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView varietiesName;
        ImageView varietiesImg;

        public MyViewHolder(View itemView) {
            super(itemView);
            varietiesName = itemView.findViewById(R.id.varieties_name);
            varietiesImg =  itemView.findViewById(R.id.varieties_img);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION)
                    {

                        {   VarietiesSingle  varieties = data.get(position);
                            Intent varietiesIntent = new Intent(context,Styles.class);
                            varietiesIntent.putExtra(Constant.varietiesKey,varieties.getVarietiesName());
                            varietiesIntent.putExtra("varieties","varieties");
                            varietiesIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(varietiesIntent);


                            //Toast.makeText(view.getContext(),varieties.getVarietiesName(),Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }
    }
}
