package com.esperer.shopshop.ui.fragments;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.esperer.shopshop.R;
import com.esperer.shopshop.database.DatabaseHelper;
import com.esperer.shopshop.models.OrderSingle;
import com.esperer.shopshop.ui.adapters.CartAdapter;
import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment {

    public static TextView totalPrice;

    private DatabaseHelper helper;

    RelativeLayout emptyCart ;
    LinearLayout cartView;
    RecyclerView recyclerView ;
    CartAdapter adapter;
    public  static List<OrderSingle> orderList;
    OrderSingle order;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerView = view.findViewById(R.id.cart_view);
        cartView =  view.findViewById(R.id.cartView);
        emptyCart = view.findViewById(R.id.emptyCart);





        orderList = getOrder();
        //helper.close();




        if (orderList.isEmpty()) {
            cartView.setVisibility(View.GONE);
            emptyCart.setVisibility(View.VISIBLE);


        }
        else {
            cartView.setVisibility(View.VISIBLE);
            emptyCart.setVisibility(View.GONE);



        }

            adapter = new CartAdapter(getActivity().getApplicationContext(), orderList);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));


            totalPrice = view.findViewById(R.id.total_price);
            totalPrice.setText(getTotalprice(getActivity().getApplicationContext()));
            //helper.close();





        return view;
    }


           public  List<OrderSingle> getOrder( )
           {    helper = new DatabaseHelper(getActivity().getApplicationContext());

               Cursor cursor = helper.getAllData();
               orderList = new ArrayList<>();
               while (cursor.moveToNext())
               {  String product_id =  cursor.getString(0);
                  String product_name = cursor.getString(1);
                  String product_brand = cursor.getString(2);
                  String product_img = cursor.getString(3);
                  String product_quantity = cursor.getString(4);
                  String product_price = cursor.getString(5);


                  order = new OrderSingle(Integer.parseInt(product_id),product_name,product_brand,
                          product_img,Integer.parseInt(product_quantity),Integer.parseInt(product_price));



                  orderList.add(order);




               }

               Log.d("test",orderList.toString());

               return orderList;
           }




    public String getTotalprice(Context context)
           {
                helper = new DatabaseHelper(getActivity().getApplicationContext());
               Cursor cursor = helper.getTotalPrice();

               int i = 0;


               while (cursor.moveToNext())
               {
                   String j = cursor.getString(0);
                    i = i + Integer.parseInt(j);
               }



                 return i+"";


           }





}

