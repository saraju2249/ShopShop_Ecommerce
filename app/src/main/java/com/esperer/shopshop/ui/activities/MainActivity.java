package com.esperer.shopshop.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.esperer.shopshop.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity  extends AppCompatActivity {

    private EditText signinEmail;
    private EditText signinPassword;
    private ImageView signinButton;
    private TextView signupButton;
    private static final String url = "http://192.168.43.159:8080/user/webapi/data/getall";

    private String dbMail;
    private String dbUser;
    private String dbPassword;

    private String email;
    private String password;
    private  boolean userExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);


        signinEmail =  findViewById(R.id.signin_email);
        signinPassword = findViewById(R.id.signup_password);
        signinButton = findViewById(R.id.signin_btn);
        signupButton =  findViewById(R.id.need_account);

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userExit = false;
                email = signinEmail.getText().toString();
                password = signinPassword.getText().toString();


                //userSignin(email,password);
                // Toast.makeText(MainActivity.this, email + password, Toast.LENGTH_LONG).show();

//                if (!TextUtils.isEmpty(signinEmail.getText())&& !TextUtils.isEmpty(signinPassword.getText()))
//                {
//
//                    userSignin(email, password);
//                }
//                else {  Toast.makeText(MainActivity.this,"check usename or password",Toast.LENGTH_LONG).show(); }


                try {
                    //doGetRequest(url);
                    doGetRequest(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }




            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signupintent = new Intent(MainActivity.this,SignupActivity.class);
                startActivity(signupintent);
            }
        });

    }


    public void doGetRequest(String url) throws IOException {
//
        OkHttpClient client = new OkHttpClient();
        //request object from okhttp NOT from other libs
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

                Log.d("Failure","happened");
                Log.d("failed", e.getMessage());
            }
            //
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();

               // Log.d("hhhhhh","hhhh");

                try {

                    JSONObject Jobject = new JSONObject(res);
                    JSONArray Jarray = Jobject.getJSONArray("profile");

                    for (int i = 0; i < Jarray.length(); i++) {
                        JSONObject object = Jarray.getJSONObject(i);
                        dbMail = object.getString("email");
                        dbUser= object.getString("name");
                        dbPassword = object.getString("password");



                        if (email.equals(dbMail) && password.equals(dbPassword))
                        {
                            userExit = true;
                        }



                    }
                    if(userExit == true)
                    {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,"Login ",Toast.LENGTH_LONG).show();

                                Intent mainIntent = new Intent(MainActivity.this, home_activity.class);
                                startActivity(mainIntent);

                            }
                        });

                    } else

                    { runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,"Incorrect email or password",Toast.LENGTH_LONG).show();

                        }
                    });
                    }

                }catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        });


    }



}
