package com.esperer.shopshop.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.esperer.shopshop.R;
import com.esperer.shopshop.api.ServiceRequest;
import com.esperer.shopshop.api.ServiceHandler;
import com.esperer.shopshop.constants.Constant;
import com.esperer.shopshop.models.StyleSingle;
import com.esperer.shopshop.ui.adapters.StylesAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Styles extends AppCompatActivity implements ServiceRequest {


    ServiceRequest serviceRequest;

    String responseData = "";
    private RecyclerView recyclerView;
    private StylesAdapter adapter ;
    private ProgressBar mProgressBar;

    private static Context sContext;

    private ArrayList<StyleSingle> productList ;

    String activity;
    String activity2;
    String activity3;
    String activity4;

    String categoryName;

    String brandName;

    String varietiesName;

    String offerType;
    String offerCategoryName;
    int offerDiscount;
    String offerBrandName;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_styles);


        recyclerView = findViewById(R.id.foods_recyclerView);
        serviceRequest = Styles.this;
        mProgressBar = findViewById(R.id.progress_bar);

        Intent startedIntent = getIntent();

        categoryName = getIntent().getExtras().getString(Constant.CategoryKey);

        brandName = getIntent().getExtras().getString(Constant.brandKey);

        varietiesName = getIntent().getExtras().getString(Constant.varietiesKey);


        offerCategoryName  = getIntent().getExtras().getString("offerCat");
        offerDiscount =  getIntent().getExtras().getInt("offerDisc");
        offerType = getIntent().getExtras().getString(Constant.offerDiscount);
        offerBrandName = getIntent().getExtras().getString("offerBrandName");

        activity =  getIntent().getExtras().getString("category");

        activity2 =  getIntent().getExtras().getString("brand");
        activity3 =  getIntent().getExtras().getString("varieties");
        activity4 =  getIntent().getExtras().getString("offer");




        productList = new ArrayList<>();

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        adapter = new StylesAdapter(this, productList);
        recyclerView.setAdapter(adapter);

        callService();

    }

    @Override public void finish() {
        super.finish();
        overridePendingTransition(0,0);
    }
    public void callService() {

        ServiceHandler.get("https://api.myjson.com/bins/1cwqym", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();

                Log.d("tag","failed");
                responseData = "No internet Connection";
                serviceRequest.onError(responseData);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {

                    if (response.isSuccessful()) {

                        //Log.d("tag","succ"+ "="+categoryName);


                        responseData = response.body().string();
// jsonParsing
                        JSONObject jsnobject = new JSONObject(responseData);

                        JSONArray jsonArray = jsnobject.getJSONArray("products");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject food = jsonArray.getJSONObject(i);


                            ///   storing json data

                            String product_name = food.getString("productName");
                            String product_img = food.getString("productImg");
                            String product_category = food.getString("category");
                            String product_desc = food.getString("productDesc");
                            int product_price = food.getInt("productPrice");
                            int product_discount = food.getInt("productDiscount");
                            int product_id = food.getInt("productId");
                            String brand_name = food.getString("brandName");
                            String varieties_name = food.getString("varietiesName");

                            //Log.d("category",product_category +"= "+ categoryName);




                            if(activity != null)
                            if( activity.equals("category" ))
                            if (categoryName.equals(product_category)) {

                                   StyleSingle styleSingle = new StyleSingle(product_name, product_desc, product_img, product_price, product_discount, product_category, product_id, brand_name, varieties_name);
                                   productList.add(styleSingle);
                                   //Log.d("check", product_category);
                               }



                           if(activity2 != null)
                           if (activity2.equals("brand"))
                           if (brandName.equals(brand_name)) {

                                       StyleSingle styleSingle = new StyleSingle(product_name, product_desc, product_img, product_price, product_discount, product_category, product_id, brand_name, varieties_name);
                                       productList.add(styleSingle);
                                       //Log.d("check", product_category);
                                   }


                            if(activity3 != null)
                                if (activity3.equals("varieties"))
                                    if(varietiesName.equals(varieties_name))
                            {

                                StyleSingle styleSingle = new StyleSingle(product_name, product_desc, product_img, product_price, product_discount, product_category, product_id,brand_name,varieties_name);
                                productList.add(styleSingle);
                                //Log.d("check",product_category);
                            }



                            if(activity4 != null)
                                if (activity4.equals("offer"))
                             if("1".equals(product_category) && offerDiscount <= product_discount && offerBrandName.equals(brand_name) )
                            {

                                StyleSingle styleSingle = new StyleSingle(product_name, product_desc, product_img, product_price, product_discount, product_category, product_id,brand_name,varieties_name);
                                productList.add(styleSingle);
                                //Log.d("check",product_category);
                            }



                        }



                        serviceRequest.onResponse(responseData);
                       // Log.i("Response", responseData);
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

        recyclerView.post(new Runnable() {
            @Override
            public void run() {

                /// refreshing adapter
                adapter.notifyDataSetChanged();
                recyclerView.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.GONE);


            }
        });

    }

    @Override
    public void onError(String errorResponse) {


        final String e = errorResponse;



            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    Toast toast = Toast.makeText(Styles.this, e, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            });

    }

}
