package com.esperer.shopshop.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.esperer.shopshop.R;


public class StartActivity extends AppCompatActivity {
    private Button signin_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        Thread thread = new Thread()
        {
            public void run( )
            {
                try{

                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(), FSigninActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };

        thread.start();


    }
}
