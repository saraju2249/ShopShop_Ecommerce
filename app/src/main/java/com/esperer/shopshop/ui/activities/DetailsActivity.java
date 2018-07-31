package com.esperer.shopshop.ui.activities;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.esperer.shopshop.R;
import com.esperer.shopshop.api.ServiceRequest;
import com.esperer.shopshop.api.ServiceHandler;
import com.esperer.shopshop.database.DatabaseHelper;
import com.esperer.shopshop.database.databaseFavorite;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DetailsActivity extends AppCompatActivity implements ServiceRequest {

    ///views
    private ImageView imgDetail, addToWish;
    private TextView nameDetail,priceDetail,descDetail,discountDetail,brandDetail,addToCart,buyButton;
    private RelativeLayout detailsLayout;
    private ProgressBar mProgressBar;

   // reference keyId
    int keyId ;

    private String productId ;
    private String productName;
    private String productImg;
    private String productDesc;
    private String productCategories;
    private int productDiscount;
    private int productPrice;
    private String productBand;
    private String productVarieties;

    private String responseData = "";
    private ServiceRequest serviceRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

         serviceRequest = DetailsActivity.this;;

         ///  get reference key id from activity who invoked DetailsActivity

         keyId = getIntent().getExtras().getInt("details");
         detailsLayout = findViewById(R.id.details_layout);
         mProgressBar = findViewById(R.id.progress_bar);


        imgDetail = findViewById(R.id.detail_img);
        nameDetail = findViewById(R.id.detail_name);
        descDetail = findViewById(R.id.description_detail);
        priceDetail = findViewById(R.id.price_detail);
        discountDetail = findViewById(R.id.discount_styles);
        brandDetail =  findViewById(R.id.brand_detail);
        addToCart = findViewById(R.id.add_to_cart);
        buyButton = findViewById(R.id.buy_button);
        addToWish = findViewById(R.id.add_to_wish);

        getProduct();

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addToCart(productId+"",productName,productBand,
                        productImg,1+"",productPrice+"");
            }
        });

        addToWish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addToWish(productId+"",productName,productBand,
                        productImg,productPrice+"",productDesc,
                        productDiscount+"",productVarieties);

            }
        });

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailsActivity.this,"Feature not available",Toast.LENGTH_SHORT).show();
            }
        });


    }

   /// check KeyId from api if it matches then store values

    public void getProduct(){

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




                        responseData = response.body().string();

                        JSONObject jsnobject = new JSONObject(responseData);

                        JSONArray jsonArray = jsnobject.getJSONArray("products");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject food = jsonArray.getJSONObject(i);

                            int product_id = food.getInt("productId");

                            if(product_id == keyId)
                            {

                                 productName = food.getString("productName");
                                 productImg = food.getString("productImg");
                                 productCategories = food.getString("category");
                                 productDesc = food.getString("productDesc");
                                 productPrice= food.getInt("productPrice");
                                 productDiscount = food.getInt("productDiscount");
                                 productId = food.getInt("productId")+"";
                                 productBand = food.getString("brandName");
                                 productVarieties = food.getString("varietiesName");

                            }
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

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                detailsLayout.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.GONE);



                nameDetail.setText(productName);
                priceDetail.setText("Rs. "+productPrice);
                descDetail.setText(productDesc);
                brandDetail.setText(""+productBand);
                Picasso.with(getApplicationContext()).load(productImg).into(imgDetail);
            }
        });



    }

          public int getQuantity( String id )
      {
          DatabaseHelper helper = new DatabaseHelper(this);
          Cursor cursor = helper.getQuantity(id);
          int i = 0;

          while (cursor.moveToNext())
         {
            String j = cursor.getString(0);
            i = i + Integer.parseInt(j);
         }
          cursor.close();
          helper.close();
          return i;
      }


    public void addToCart( String product_id, String product_name,String product_brand, String product_img,
                           String product_quantity, String product_price )
    {
        int checkAvailability =  getQuantity(product_id);

        DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);

        if (checkAvailability == 0)
        {

            boolean isAdded =  mDatabaseHelper.insert(product_id, product_name,product_brand,  product_img,
                    product_quantity, product_price );

            mDatabaseHelper.close();


            if (isAdded == true)
            {
                Toast.makeText(DetailsActivity.this,"Added to Cart",Toast.LENGTH_SHORT).show();


            }

        }

        else {

            int quantity = checkAvailability+Integer.parseInt(product_quantity);
            boolean isAdded =  mDatabaseHelper.update(product_id, product_name,product_brand,  product_img,
                    quantity+"", product_price );


            if (isAdded == true)
            {
                Toast.makeText(DetailsActivity.this,"Added to Cart",Toast.LENGTH_SHORT).show();


            }

            mDatabaseHelper.close();


        }

    }



    public void addToWish( String product_id, String product_name,String product_brand, String product_img,
                           String product_price,String product_desc, String product_discount, String product_varieties )
    {

        databaseFavorite mDatabaseFavorite = new databaseFavorite(DetailsActivity.this);
        boolean isAdded =  mDatabaseFavorite.insert(product_id, product_name,product_brand,  product_img,
                product_price,product_discount);

        mDatabaseFavorite.close();


        if (isAdded == true)
        {
            Toast.makeText(DetailsActivity.this,"Added to Favorite",Toast.LENGTH_SHORT).show();


        }

        else {

            Toast.makeText(DetailsActivity.this,"Already present in favoriteList",Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onError(String errorResponse) {

    }
}
