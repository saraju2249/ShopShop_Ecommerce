package com.esperer.shopshop.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.esperer.shopshop.R;
import com.esperer.shopshop.api.IServiceRequest;
import com.esperer.shopshop.api.ServiceHandler;
import com.esperer.shopshop.models.StyleSingle;
import com.esperer.shopshop.ui.activities.home_activity;
import com.esperer.shopshop.ui.adapters.SearchAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class SearchFragment  extends Fragment  implements IServiceRequest{


    private RecyclerView mRecyclerView;
    EditText searchEditText;
    private ArrayList<StyleSingle> filterProducts;
    TextView defaultMessage;

    public static List<StyleSingle> allProduct;
    IServiceRequest serviceRequest;
    String responseData = "";
    SearchAdapter searchAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);


        serviceRequest = SearchFragment.this;
        allProduct = new ArrayList<>();
        getAllProduct();

        mRecyclerView = view.findViewById(R.id.searchProductsRecyclerView);

        searchEditText = view.findViewById(R.id.search_edittext);
        defaultMessage = view.findViewById(R.id.defaultMessage);
        defaultMessage.setText("Search Any Product");



        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Log.d("text", editable.toString());
                searchProducts(editable.toString());
            }
        });


        return view;
    }

    public void searchProducts(String search) {

        filterProducts = new ArrayList<>();



        if (search.length() > 0) {
            for (int i = 0; i < allProduct.size(); i++)
                if (allProduct.get(i).getProductName().toLowerCase().contains(search.toLowerCase().trim())) {
                    filterProducts.add(allProduct.get(i));
                }

        if (allProduct.size() < 1) {
            defaultMessage.setText("Record Not Found");
            defaultMessage.setVisibility(View.VISIBLE);
        } else {
            defaultMessage.setVisibility(View.GONE);
        }
        //Log.d("size", filterProducts.size() + "" + home_activity.allProduct.size());
    } else

    {
        filterProducts = new ArrayList<>();
        defaultMessage.setText("Search Any Product");
        defaultMessage.setVisibility(View.VISIBLE);
    }

        setProductsData();

}
    private void setProductsData() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);
         searchAdapter = new SearchAdapter(getActivity().getApplicationContext(), filterProducts);
        mRecyclerView.setAdapter(searchAdapter);

    }

    public void getAllProduct(){

        ServiceHandler.get("https://api.myjson.com/bins/1cwqym", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();

                // Log.d("tag","failed");
                responseData = "No internet Connection";
                serviceRequest.onError(responseData);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {

                    if (response.isSuccessful()) {

                        // Log.d("tag","succ");


                        responseData = response.body().string();

                        JSONObject jsnobject = new JSONObject(responseData);

                        JSONArray jsonArray = jsnobject.getJSONArray("products");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject food = jsonArray.getJSONObject(i);



                            String product_name = food.getString("productName");
                            String product_img = food.getString("productImg");
                            String product_category = food.getString("category");
                            String product_desc = food.getString("productDesc");
                            int product_price = food.getInt("productPrice");
                            int product_discount = food.getInt("productDiscount");
                            int product_id = food.getInt("productId");
                            String brand_name = food.getString("brandName");
                            String varieties_name = food.getString("varietiesName");

                            StyleSingle styleSingle = new StyleSingle(product_name, product_desc,
                                    product_img, product_price, product_discount, product_category,
                                    product_id,brand_name,varieties_name);
                            allProduct.add(styleSingle);


                        }



                        serviceRequest.onResponse(responseData);
                        //Log.i("Response", responseData);
                    } else {

                        responseData = "Something went Wrong";
                        serviceRequest.onError(responseData);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    responseData = "IO Error";
                    serviceRequest.onError(responseData);
                } catch (JSONException e) {
                    e.printStackTrace();
                    responseData = "Parsing Error";
                    serviceRequest.onError(responseData);
                }
            }
        });


    }


    @Override
    public void onResponse(String response) {

        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {

                /// refreshing adapter
                if (searchAdapter != null) {
                    searchAdapter.notifyDataSetChanged();

                }

            }
        });

    }

    @Override
    public void onError(String errorResponse) {

        final String e = errorResponse;

        if(this.getActivity() != null) {

            this.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    Toast.makeText(getActivity().getApplicationContext(),e,Toast.LENGTH_LONG).show();
                }
            });


        }

    }
}



