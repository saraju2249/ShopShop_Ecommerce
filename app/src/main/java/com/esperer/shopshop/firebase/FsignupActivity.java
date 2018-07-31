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
import android.widget.Toast;
import com.esperer.shopshop.R;
import com.esperer.shopshop.ui.activities.home_activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FsignupActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    /// view
    private EditText name ;
    private EditText email;
    private EditText password;
    private Button create_btn;
    private ProgressDialog mRegisterProgress;

    public static String signupEmail;

    ///  sharedPreference
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;


    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fsignup);




        mRegisterProgress = new ProgressDialog(this);


        mAuth = FirebaseAuth.getInstance();


        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
        name = findViewById(R.id.signup_name);
        create_btn = findViewById(R.id.createButton);


        signupEmail = email.getText().toString();





        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uemail = email.getText().toString();
                String upassword = password.getText().toString();
                String uname = name.getText().toString();

                if (!TextUtils.isEmpty(email.getText())&& !TextUtils.isEmpty(password.getText()) && !TextUtils.isEmpty(name.getText()))
                {   mRegisterProgress.setTitle("Registering");
                    mRegisterProgress.setMessage("Please wait while Registering");
                    mRegisterProgress.setCanceledOnTouchOutside(false);
                    mRegisterProgress.show();
                    createAccount(uemail,upassword);

                    sharedpreferences =  getApplicationContext().getSharedPreferences("info", Context.MODE_PRIVATE);
                    editor = sharedpreferences.edit();
                    editor.putString("username", uname);
                    editor.apply();
                }
                else {  Toast.makeText(FsignupActivity.this, "please provide valid details" , Toast.LENGTH_LONG).show(); }



            }
        });
    }

    private void createAccount(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            mRegisterProgress.dismiss();
                            Intent startIntent = new Intent(FsignupActivity.this,home_activity.class);
                            startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(startIntent);
                            finish();
                            //Log.d(TAG, "createUserWithEmail:success");
                        } else {
                            mRegisterProgress.hide();
                            Toast.makeText(FsignupActivity.this,"Something went wrong, Try again later", Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });
    }
}
