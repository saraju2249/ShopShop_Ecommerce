package com.esperer.shopshop.ui.activities;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import com.esperer.shopshop.R;
import com.esperer.shopshop.database.DatabaseHelper;
import com.esperer.shopshop.firebase.StartActivity;
import com.esperer.shopshop.ui.fragments.Accountfragment;
import com.esperer.shopshop.ui.fragments.CartFragment;
import com.esperer.shopshop.ui.fragments.HomeFragment;
import com.esperer.shopshop.ui.fragments.MoreFragment;
import com.esperer.shopshop.ui.fragments.SearchFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;


public class home_activity extends AppCompatActivity {

    ///public int number;

    /// FirebaseAuth
    private FirebaseAuth mAuth;

    /// SharedPreference
    SharedPreferences sharedpreferences;

    /// ButtomBar
    BottomBar bottomBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        /// get SharedPreference
        sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        mAuth = FirebaseAuth.getInstance();


        /// set BottomBar
        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {

            }
        });

        /// Set TabReselected to HomeFragment (which recreate fragment on TabReselected)

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(int tabId) {

                if (tabId == R.id.tab_home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new HomeFragment()).commit();
                }

            }
        });

        /// Create Fragments according to selected Tab

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {

            Fragment selected = null;
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_home) {

                    selected = new HomeFragment();
                }
                if (tabId == R.id.tab_search) {

                    selected = new SearchFragment();
                }

                if (tabId == R.id.tab_more) {

                    selected = new MoreFragment();
                }

                if (tabId == R.id.tab_cart) {

                    selected = new CartFragment();
                }

                if (tabId == R.id.tab_account) {

                    selected = new Accountfragment();


                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selected).commit();
                }
                });

    }


    @Override
    public void onResume( ) {
        super.onResume();
        setBadge();

    }


    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        setBadge();

    }

    /// SetUp of Badge on BottomNavigationBar

    public void setBadge( )
    {
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        int cartCount =  databaseHelper.noOfItem();
        BottomBarTab cart = bottomBar.getTabWithId(R.id.tab_cart);
        cart.setBadgeCount(cartCount);
    }

   /// check Current user or skip condition
   /// if both condition aren't satisfied then send to start

    @Override
    public void onStart() {
        super.onStart();

        Boolean skip = sharedpreferences.getBoolean("skipCheck",false);

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null && skip == false)
        {
            sendToStart();
        }

    }



    private void sendToStart( )
    {
        Intent startIntent = new Intent(home_activity.this,StartActivity.class);
        startActivity(startIntent);
        finish();
    }


}