package com.ezy.paceshopapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //views
    Button mDaftarBtn, mMasukBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init views
        mDaftarBtn = findViewById(R.id.dftr_btn);
        mMasukBtn = findViewById(R.id.msk_btn);

        //handle register button click
        mDaftarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //star register activity
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            }
        });
    }
}