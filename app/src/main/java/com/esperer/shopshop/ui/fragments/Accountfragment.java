package com.esperer.shopshop.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.esperer.shopshop.R;
import com.esperer.shopshop.firebase.StartActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Accountfragment extends Fragment {


    TextView userAccount,logOut;
    private FirebaseAuth mAuth;

    SharedPreferences sharedpreferences;
    TextView textName;
    ImageView  accountImg;

    private TextView cartButton,favoriteButton, ordersButton, settingAccount;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          View view = inflater.inflate(R.layout.fragment_account, container, false);

       mAuth = FirebaseAuth.getInstance();
       userAccount = view.findViewById(R.id.user_account);
       logOut = view.findViewById(R.id.log_out_btn);
       textName = view.findViewById(R.id.user_account);
       cartButton = view.findViewById(R.id.cart_btn);
       favoriteButton = view.findViewById(R.id.favorite_btn);
       accountImg = view.findViewById(R.id.account_img);
       ordersButton = view.findViewById(R.id.orders_button);
       settingAccount =  view.findViewById(R.id.settiing_button);

        sharedpreferences = getActivity().getSharedPreferences("info", Context.MODE_PRIVATE);

        String userName = sharedpreferences.getString("username",null);



        FirebaseUser currentUser = mAuth.getCurrentUser();




        if(currentUser == null )
        {
            logOut.setText("SIGN IN");
            accountImg.setImageResource(R.drawable.ic_person_brown_24dp);
        }


        if(userName != null && currentUser != null ) {
            textName.setText(userName);
            accountImg.setImageResource(R.drawable.ic_exit_to_app_brown_24dp);
        }


       logOut.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

              mAuth.signOut();
               sendToStart();
           }
       });

            openCart( );
            openFavorite( );
            openOrders( );
            openSetting( );


            return view;
    }

    private void sendToStart( )
    {
                Intent startIntent = new Intent(getActivity().getApplicationContext(),StartActivity.class);
               startActivity(startIntent);
               getActivity().finish();
    }

    public  void  openCart(  )
    {

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                android.support.v4.app.Fragment f = new CartFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f).addToBackStack(null).commit();
            }
        });


    }

    public  void openFavorite( )
    {
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                android.support.v4.app.Fragment f = new FavoriteFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f).addToBackStack(null).commit();

            }
        });
    }

    public  void openOrders( )
    {

        ordersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                android.support.v4.app.Fragment f = new OrdersFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f).addToBackStack(null).commit();

            }
        });

    }

    public void openSetting(  )
    {
         settingAccount.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 AppCompatActivity activity = (AppCompatActivity) v.getContext();
                 android.support.v4.app.Fragment f = new SettingFragment();
                 activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, f).addToBackStack(null).commit();


             }
         });


    }


}
