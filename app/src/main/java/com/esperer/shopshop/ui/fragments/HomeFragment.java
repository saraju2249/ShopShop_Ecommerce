package com.esperer.shopshop.ui.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.amulyakhare.textdrawable.TextDrawable;
import com.esperer.shopshop.R;
import com.esperer.shopshop.api.ServiceRequest;
import com.esperer.shopshop.api.ServiceHandler;
import com.esperer.shopshop.database.databaseFavorite;
import com.esperer.shopshop.models.BrandSingle;
import com.esperer.shopshop.models.CategorySingle;
import com.esperer.shopshop.models.OfferSingle;
import com.esperer.shopshop.models.TendingSingle;
import com.esperer.shopshop.models.VarietiesSingle;
import com.esperer.shopshop.ui.adapters.MainAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class HomeFragment extends Fragment implements ServiceRequest {




    private ServiceRequest serviceRequest;
    String responseData = "";

    /// Views
    private RecyclerView recyclerView;
    private MainAdapter adapter ;
    private static Context sContext;
    private ProgressBar mProgressBar;
    private CoordinatorLayout coordinatorLayout;

    /// create arrays to store List of data
    private ArrayList<Object> objects = new ArrayList<>();
    private static ArrayList<CategorySingle> categoryList;
    private static ArrayList<OfferSingle> offerList;
    private static ArrayList<BrandSingle> brandList;
    private static  ArrayList<VarietiesSingle> varietiesList;
    private static ArrayList<TendingSingle> tendingList;

    private Toolbar toolbar;
    public static int number;
    private int count ;





    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        sContext = getActivity().getApplicationContext();


        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)  getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        mProgressBar = view.findViewById(R.id.progress_bar);
        coordinatorLayout = view.findViewById(R.id.homeCoordinate);


        serviceRequest = HomeFragment.this;
        categoryList = new ArrayList<>();
        offerList = new ArrayList<>();
        brandList = new ArrayList<>();
        varietiesList = new ArrayList<>();
        tendingList = new ArrayList<>();


        recyclerView = view.findViewById(R.id.all_recycler);
        adapter = new MainAdapter(getContext(), getObject());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        callService();
        return view;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();

        databaseFavorite favorite =  new databaseFavorite(getActivity().getApplicationContext());
        count =  favorite.noOfItem();

       inflater.inflate(R.menu.main_menu, menu);
        MenuItem  menuItem =  menu.findItem(R.id.favorite);
        menuItem.setIcon(buildCounterDrawable(count));



        super.onCreateOptionsMenu(menu, inflater);
    }


  /// create Drawer for toolbar item badge
    private Drawable buildCounterDrawable(int count){
        LayoutInflater inflater = LayoutInflater.from(getActivity().getApplicationContext());
        View view = inflater.inflate(R.layout.counter_menu_item_layout, null);


        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .textColor(Color.WHITE)
                .useFont(Typeface.DEFAULT)
                .fontSize(30) /* size in px */
                .bold()
                .toUpperCase()
                .endConfig()
                .buildRound(count+"", Color.RED);

        ImageView image = (ImageView)  view.findViewById(R.id.BadgeRelativeLayout);
        image.setImageDrawable(drawable);

        if (count == 0) {
            View counterTextPanel = view.findViewById(R.id.BadgeRelativeLayout);
            counterTextPanel.setVisibility(View.GONE);
        } else {
//            TextView textView = (TextView) view.findViewById(R.id.BadgeCount);
//            //textView.setText("" + count);
        }


        view.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return new BitmapDrawable(getResources(), bitmap);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favorite:

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FavoriteFragment()).commit();
                break;

            case R.id.notification:

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new NotificationFragment()).commit();

                break;
        }
        return true;

    }




    ///  returning ObjectList
       private ArrayList<Object> getObject() {

        return objects;
        }

       ///  returning CategoryList
      public  ArrayList<CategorySingle> getCategory() {

        return  categoryList ;
        }

      /// returning offerList
      public ArrayList<OfferSingle> getOffer() {

        return  offerList ;
       }

    /// returning brandList

      public ArrayList<BrandSingle> getBrand() {

        return  brandList ;
       }

    /// returning varietiesList

      public  ArrayList<VarietiesSingle> getVarieties() {

        return  varietiesList ;
       }

    /// returning trendingList

       public ArrayList<TendingSingle> getTrending() {

        return tendingList;
    }


      // returning Activity Context
      public static Context getFContext() {
        return sContext;
    }

    ///  networking codes
    public void callService() {

        ServiceHandler.get("https://api.myjson.com/bins/1cwqym", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();

                //Log.d("tag","failed");
                responseData = "No internet Connection";
                serviceRequest.onError(responseData);
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {

                    if (response.isSuccessful()) {

                        //Log.d("tag","succ");

                        responseData = response.body().string();
// jsonParsing
                        JSONObject jsnobject = new JSONObject(responseData);

                        JSONArray categoryArray = jsnobject.getJSONArray("category");
                        JSONArray topOfferArray = jsnobject.getJSONArray("topOffer");
                        JSONArray brandArray = jsnobject.getJSONArray("brands");
                        JSONArray varietiesArray = jsnobject.getJSONArray("varieties");
                        JSONArray trendingArray = jsnobject.getJSONArray("trending");

                        for (int i = 0; i < categoryArray.length(); i++) {
                            JSONObject category = categoryArray.getJSONObject(i);


                            ///   storing json data
                            String categoryType = category.getString("category");
                            String categoryName = category.getString("categoryName");
                            String categoryImg = category.getString("categoryImg");


                            ///  adding data to CategorySingle
                            CategorySingle singlecategory = new CategorySingle(categoryType, categoryName, categoryImg);
                            categoryList.add(singlecategory);


                        }





                        for (int i = 0; i < topOfferArray.length(); i++)
                        {
                            JSONObject offer = topOfferArray.getJSONObject(i);


                            ///   storing json data
                            String type = offer.getString("type");
                            String categoryName = offer.getString("categoryName");
                            String offerImg = offer.getString("offerImg");
                            int offerDiscount =  offer.getInt("minDiscount");
                            String offerBrandName = offer.getString("offerBrandName");

                            ///  adding data to OfferSingle
                                OfferSingle offerSingle = new OfferSingle(offerImg, categoryName,offerDiscount ,type,offerBrandName);
                                offerList.add(offerSingle);
                                //Log.d("check",categoryName);

                         }

                           for (int i = 0; i < varietiesArray.length(); i++) {
                            JSONObject varieties = varietiesArray.getJSONObject(i);

                            String varietiesName = varieties.getString("varietiesName");
                            String varietiesImg = varieties.getString("varietiesImg");

                            VarietiesSingle singleVarieties = new VarietiesSingle(varietiesName, varietiesImg);
                            varietiesList.add(singleVarieties);


                          }

                        for (int i = 0; i < brandArray.length(); i++) {
                            JSONObject brand = brandArray.getJSONObject(i);

                            String brand_name = brand.getString("brandName");
                            String brand_img = brand.getString("brandImg");
                            int brand_id = brand.getInt("brandId");

                            ///  adding data to BrandSingle
                            BrandSingle singleBrand = new BrandSingle(brand_name,brand_img, brand_id);
                            brandList.add(singleBrand);


                          }


                          for (int i = 0; i < trendingArray.length(); i++) {
                            JSONObject trending = trendingArray.getJSONObject(i);


                            String product_name = trending.getString("productName");
                            String product_img = trending.getString("productImg");
                            String product_category = trending.getString("category");
                            String product_desc = trending.getString("productDesc");
                            int product_price = trending.getInt("productPrice");
                            int product_discount = trending.getInt("productDiscount");
                            int product_id = trending.getInt("productId");
                            String product_varieties = trending.getString("varietiesName");
                            String brand_name = trending.getString("brandName");


                              ///  adding data to TendingSingle
                            TendingSingle trendingSingle = new TendingSingle(product_name, product_desc, product_img, product_price, product_discount, product_category, product_id,brand_name,product_varieties);
                            tendingList.add(trendingSingle);


                        }


                        ///  adding data to ObjectList
                        if(getCategory().size() > 0)
                        {
                            objects.add(getCategory().get(getCategory().size()-1));
                        }
                        if(getCategory().size() > 0)
                        {
                            objects.add(getVarieties().get(getVarieties().size()-1));
                        }

                        if(getCategory().size() > 0)
                        {
                            objects.add(getBrand().get(getBrand().size()-1));
                        }

                        if(getCategory().size() > 0)
                        {
                            objects.add(getOffer().get(getOffer().size()-1));
                        }
                        if(getCategory().size() > 0)
                        {
                            objects.add(getTrending().get(getTrending().size()-1));
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

        recyclerView.post(new Runnable() {
            @Override
            public void run() {
   /// refreshing adapter
                adapter.notifyDataSetChanged();
                recyclerView.setVisibility(VISIBLE);
                mProgressBar.setVisibility(GONE);
            }
        });

    }

    @Override
        public void onError( String errorResponse) {

        /// show error snackbar
            final String e = errorResponse;

        if(this.getActivity() != null) {

            this.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {


                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, e, Snackbar.LENGTH_LONG).setAction("Close", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    getActivity().finish();
                                    System.exit(0);

                                }
                            });
                    snackbar.show();
                }
            });


        }
        }



}
