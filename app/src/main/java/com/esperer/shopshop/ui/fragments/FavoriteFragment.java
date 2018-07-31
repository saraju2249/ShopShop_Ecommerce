package com.esperer.shopshop.ui.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.esperer.shopshop.R;
import com.esperer.shopshop.database.databaseFavorite;
import com.esperer.shopshop.models.FavoriteSingle;
import com.esperer.shopshop.ui.adapters.FavoriteAdapter;
import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment {


    private RelativeLayout emptyFavorite;
    private LinearLayout favoriteView;
    private RecyclerView recyclerView;
    private FavoriteAdapter adapter;
    private static List<FavoriteSingle> favoriteList;
    private FavoriteSingle mFavoriteSingle;
    private FloatingActionButton favFab;

    List<FavoriteSingle> singleFav;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);


        recyclerView = view.findViewById(R.id.favorite_view);
        favoriteView = view.findViewById(R.id.favoriteView);
        emptyFavorite = view.findViewById(R.id.emptyFavorite);
        favFab = view.findViewById(R.id.favorite_fab);


        getFavorite();


        if (favoriteList.isEmpty()) {
            favoriteView.setVisibility(View.GONE);
            emptyFavorite.setVisibility(View.VISIBLE);


        } else {
            favoriteView.setVisibility(View.VISIBLE);
            emptyFavorite.setVisibility(View.GONE);

        }

        adapter = new FavoriteAdapter(getActivity().getApplicationContext(), favoriteList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        fabClick ( );
        return view;

    }


    public void getFavorite()


    {

        databaseFavorite helper = new databaseFavorite(getActivity().getApplicationContext());

        Cursor cursor = helper.getAllData();
        favoriteList = new ArrayList<>();
        while (cursor.moveToNext()) {

            String product_id = cursor.getString(0);
            String product_name = cursor.getString(1);
            String product_brand = cursor.getString(2);
            String product_img = cursor.getString(3);
            String product_price = cursor.getString(4);
            String product_discount = cursor.getString(5);



            mFavoriteSingle = new FavoriteSingle(Integer.parseInt(product_id), product_name, product_brand,
                    product_img, Integer.parseInt(product_price), Integer.parseInt(product_discount));


            favoriteList.add(mFavoriteSingle);


        }
//        cursor.close();
//         helper.close();


    }

    public void fabClick( )
    {
        favFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getContext(),"fab clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }
}






