package com.avci.berkeravciproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView txtProObj;
    Button save;
    Intent intent;
    com.avci.berkeravciproject1.DatabaseHelper dbHelper;
    com.avci.berkeravciproject1.Product pro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_second);

        dbHelper = new com.avci.berkeravciproject1.DatabaseHelper(this);

        txtProObj = findViewById(R.id.txtPro);


        txtProObj.setText(com.avci.berkeravciproject1.Commons.curentSelectedPro.toString()+"");
    }


}