package com.avci.berkeravciproject1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ParseActivity extends AppCompatActivity {

    RecyclerView proRecyclerView;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_parse);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        dbHelper = new DatabaseHelper(this);
        proRecyclerView = findViewById(R.id.proRecyclerView);

        IntentFilter filter = new IntentFilter();
        filter.addAction("JSON_PARSE_COMPLETED_ACTION");
        registerReceiver(mbroadcastreciver, filter);

        IntentFilter filterInsert = new IntentFilter();
        filterInsert.addAction( "INSERT_PRO_ACTION");
        registerReceiver(mbroadcastreciverInsert, filterInsert);


    }

    public void onClick(View v){

        if(v.getId() == R.id.btnJSON){
            LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            proRecyclerView.setLayoutManager(layoutManager);
            MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(this, Commons.proList);

            proRecyclerView.setAdapter(adapter);

            Intent intent = new Intent(this, MyIntentService.class);
            startService(intent);
        }
        else if(v.getId() == R.id.btnShowDB){
            Commons.proList = ProductDB.getAllBooks(dbHelper);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            proRecyclerView.setLayoutManager(layoutManager);
            MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(this, Commons.proList);
            proRecyclerView.setAdapter(adapter);
        }
    }

    private BroadcastReceiver mbroadcastreciver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra("result");
            if(result.contains("NOT")){
                Toast.makeText(ParseActivity.this, "ERROR; JSON CANNOT BE PARSED",Toast.LENGTH_LONG).show();
            }
            else {
                Bundle b = intent.getExtras();
                Commons.proList = b.getParcelableArrayList("products");
                MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(ParseActivity.this, Commons.proList);
                proRecyclerView.setAdapter(adapter);
            }
        }
    };

    private BroadcastReceiver mbroadcastreciverInsert = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra("insertResult");
            Toast.makeText(ParseActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    };
}