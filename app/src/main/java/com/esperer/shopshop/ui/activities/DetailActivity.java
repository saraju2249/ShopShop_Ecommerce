package com.esperer.shopshop.ui.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.esperer.shopshop.R;
import com.esperer.shopshop.constants.Constant;
import com.esperer.shopshop.database.DatabaseHelper;
import com.esperer.shopshop.database.databaseFavorite;
import com.esperer.shopshop.models.StyleSingle;
import com.esperer.shopshop.models.TendingSingle;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {


    private StyleSingle detail;
    private TendingSingle trending;
    private ImageView imgDetail, addToWish;
    private TextView nameDetail,priceDetail,descDetail,discountDetail,brandDetail;

    private DatabaseHelper mDatabaseHelper;
   private databaseFavorite mDatabaseFavorite;
    private TextView addToCart,buyButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_activity);

        imgDetail = findViewById(R.id.detail_img);
        nameDetail = findViewById(R.id.detail_name);
        descDetail = findViewById(R.id.description_detail);
        priceDetail = findViewById(R.id.price_detail);
        discountDetail = findViewById(R.id.discount_styles);
        brandDetail =  findViewById(R.id.brand_detail);
        addToCart = findViewById(R.id.add_to_cart);
        buyButton = findViewById(R.id.buy_button);
        addToWish = findViewById(R.id.add_to_wish);
        mDatabaseHelper = new DatabaseHelper(this);
        mDatabaseFavorite = new databaseFavorite(this);






        if((getIntent().getParcelableExtra(Constant.stylesKey))instanceof StyleSingle) {
            detail = (StyleSingle) getIntent().getParcelableExtra(Constant.stylesKey);




            nameDetail.setText(detail.getProductName());
            priceDetail.setText("Rs. "+detail.getProductPrice());
            descDetail.setText(detail.getProductDesc());
            brandDetail.setText(""+detail.getBrandName());
            Picasso.with(getApplicationContext()).load(detail.getProductImg()).into(imgDetail);

            addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    addToCart(detail.getProductId()+"",detail.getProductName(),detail.getBrandName(),
                            detail.getProductImg(),1+"",detail.getProductPrice()+"");
                }
            });

            addToWish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    addToWish(detail.getProductId()+"",detail.getProductName(),detail.getBrandName(),
                            detail.getProductImg(),detail.getProductPrice()+"",detail.getProductDesc(),
                            detail.getProductDiscount()+"",detail.getProductVarieties());

                }
            });

            buyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(DetailActivity.this,"Feature not available",Toast.LENGTH_SHORT).show();
                }
            });

        }




        else if((getIntent().getParcelableExtra("tending") )instanceof TendingSingle) {
            trending = (TendingSingle) getIntent().getParcelableExtra("tending");


            nameDetail.setText(trending.getProductName());
            priceDetail.setText(trending.getProductPrice() + "");
            descDetail.setText(trending.getProductDesc());
            brandDetail.setText(trending.getBrandName());
            Picasso.with(getApplicationContext()).load(trending.getProductImg()).into(imgDetail);

            addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    addToCart(trending.getProductId()+"",trending.getProductName(),trending.getBrandName(),
                            trending.getProductImg(),1+"",trending.getProductPrice()+"");
                }
            });
        }


    }

    @Override public void finish() {
        super.finish();
        overridePendingTransition(0,0);
    }

    public void addToCart( String product_id, String product_name,String product_brand, String product_img,
                           String product_quantity, String product_price )
    {
        int checkAvailability =  getQuantity(product_id);

      if (checkAvailability == 0)
      {

            boolean isAdded =  mDatabaseHelper.insert(product_id, product_name,product_brand,  product_img,
                    product_quantity, product_price );


            if (isAdded == true)
            {
                Toast.makeText(DetailActivity.this,"Added to Cart",Toast.LENGTH_SHORT).show();


          }
        }

        else {

            int quantity = checkAvailability+Integer.parseInt(product_quantity);
            boolean isAdded =  mDatabaseHelper.update(product_id, product_name,product_brand,  product_img,
                    quantity+"", product_price );


            if (isAdded == true)
            {
                Toast.makeText(DetailActivity.this,"Added to Cart",Toast.LENGTH_SHORT).show();


            }

          Toast.makeText(DetailActivity.this,checkAvailability+"",Toast.LENGTH_SHORT).show();


        }

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

        return i;
    }


    public void addToWish( String product_id, String product_name,String product_brand, String product_img,
                String product_price,String product_desc, String product_discount, String product_varieties )
    {
        int checkAvailability =  getQuantity(product_id);


            boolean isAdded =  mDatabaseFavorite.insert(product_id, product_name,product_brand,  product_img,
                      product_price,product_discount);


            if (isAdded == true)
            {
                Toast.makeText(DetailActivity.this,"Added to Favorite",Toast.LENGTH_SHORT).show();


            }

            else {

                Toast.makeText(DetailActivity.this,"Already present in favoriteList",Toast.LENGTH_SHORT).show();

            }

    }







}
