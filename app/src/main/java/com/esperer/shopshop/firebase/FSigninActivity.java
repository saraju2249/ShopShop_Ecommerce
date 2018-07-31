package com.esperer.shopshop.firebase;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.esperer.shopshop.R;
import com.esperer.shopshop.ui.activities.home_activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FSigninActivity extends AppCompatActivity {

    /// View
    private EditText login_eamil,login_password ;
    private Button login_button;
    private TextView forgatPassword, signup_button, skip;
    private ProgressDialog mloginProgress;


    /// SharedPreferences
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;

    /// FirebaseAuth
    private FirebaseAuth mAuth;

    public static boolean skipped;
    public static String signinEmail;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fsignin);


        login_eamil = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        login_button = findViewById(R.id.login_button);
        forgatPassword = findViewById(R.id.btn_forgot_password);
        skip =  findViewById(R.id.skip);
        signinEmail = login_eamil.getText().toString();
        signup_button= findViewById(R.id.create_account_btn);


        mloginProgress = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();


        skip.setOnClickListener(  new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /// set skips as a preference
                sharedpreferences =  getApplicationContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                editor = sharedpreferences.edit();
                editor.putBoolean("skipCheck", true);
                editor.apply();

               skipped = true;
                Intent mainIntent =  new Intent(FSigninActivity.this,home_activity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(mainIntent);
                finish();

            }
        });



        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = login_eamil.getText().toString();
                String password = login_password.getText().toString();



                if (!TextUtils.isEmpty(login_eamil.getText())&& !TextUtils.isEmpty(login_password.getText()))
                { mloginProgress.setTitle("Loging in");
                    mloginProgress.setMessage("Please wait while cheking credential");
                    mloginProgress.setCanceledOnTouchOutside(false);
                    mloginProgress.show();
                    userLogin(email, password);
                }
                else {  Toast.makeText(FSigninActivity.this, "please provide valid details" , Toast.LENGTH_LONG).show(); }



            }
        });

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg_intent = new Intent(FSigninActivity.this,FsignupActivity.class);
                startActivity(reg_intent);
            }
        });


        forgatPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(FSigninActivity.this, ResetPasswordActivity.class));

            }
        });

    }


    /// method to login
    private void userLogin(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {   mloginProgress.dismiss();

                    Intent mainIntent =  new Intent(FSigninActivity.this,home_activity.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(mainIntent);
                    finish();
                }
                else {

                    mloginProgress.hide();
                    Toast.makeText(FSigninActivity.this,"Please check Your email or password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
