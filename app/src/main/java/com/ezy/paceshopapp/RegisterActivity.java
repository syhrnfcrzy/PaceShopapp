package com.ezy.paceshopapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class RegisterActivity extends AppCompatActivity {

    //views
    EditText mEmail, mPassword, mNamaPengguna, mNotelp, mKonfirmasi;
    Button mDaftarBtn;

    //progress bar to display while registering user
    ProgressDialog progressDialog;


    //Declarate an instace of FirebaseAuth
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Actionbar and its title
        //ActionBar actionBar = getSupportActionBar();
        //Objects.requireNonNull(actionBar).setTitle("Buat Akun");
        //enable back button
        //actionBar.setDisplayHomeAsUpEnabled(true);
        //actionBar.setDisplayShowHomeEnabled(true);

        //init
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mDaftarBtn = findViewById(R.id.dftr_btn);
        mNamaPengguna = findViewById(R.id.nmpnggn);
        mNotelp = findViewById(R.id.phone);
        mKonfirmasi = findViewById(R.id.konfirmasi);
        //===============//
        mAuth = FirebaseAuth.getInstance();
        //=============//
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering user ...");


        //handle register btn click
        mDaftarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //input email, passowrd
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    //set error and focus to email edittext
                    mEmail.setError("Invalid Email");
                    mEmail.setFocusable(true);
                }
                else if (password.length()<8) {
                    mPassword.setError("Password length at least 8 characthers");
                    mPassword.setFocusable(true);
                }
                else {
                    registerUser (email, password);
                }

            }
        });



    }

    private void registerUser(String email, String password) {
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, dissmis dialog and start register activity
                            progressDialog.dismiss();
                            FirebaseUser user = mAuth.getCurrentUser();
                            assert user != null;
                            Toast.makeText(RegisterActivity.this,"Registered ....\n"+user.getEmail(),Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,ProfiileActivity.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }


                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //error, dissmis progres dialog and get and show the error message
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this,""+ e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); //go previous activity
        return super.onSupportNavigateUp();
    }}