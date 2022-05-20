package com.avci.berkeravciproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    EditText twname;
    EditText twquan;
    EditText twprice;
    com.avci.berkeravciproject1.DatabaseHelper dbHelper;
    com.avci.berkeravciproject1.Product pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        dbHelper = new com.avci.berkeravciproject1.DatabaseHelper(this);

        twname=findViewById(R.id.twname);
        twquan=findViewById(R.id.twquan);
        twprice=findViewById(R.id.twprice);

    }

    public void add(View view) {
        Intent intent=new Intent(this, com.avci.berkeravciproject1.InsertDBIntentService.class);
        String name = twname.getText().toString();
        String number = twquan.getText().toString();
        String price = twprice.getText().toString();
        com.avci.berkeravciproject1.ProductDB.insert(dbHelper,name,number,price);
    }
}