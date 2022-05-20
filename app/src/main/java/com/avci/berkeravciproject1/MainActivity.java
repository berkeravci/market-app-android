package com.avci.berkeravciproject1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private GestureDetectorCompat mDetector;
    private MediaPlayer player;
    TextView add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mDetector = new GestureDetectorCompat(this, new MyGestureListener());
        player = MediaPlayer.create(this, R.raw.intro);


        player.start();



    }

    public void parse(View view) {
        Intent intent=new Intent(MainActivity.this,ParseActivity.class);
        startActivity(intent);

    }
    public void onClick(View view) {

        Intent intent= new Intent(MainActivity.this,NoteActivity.class);
        startActivity(intent);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void AddClick(View view) {
        Intent intent=new Intent(MainActivity.this,AddActivity.class);
        startActivity(intent);
    }

    public void image(View view) {
        Intent intent=new Intent(MainActivity.this,PhotoActivity.class);
        startActivity(intent);
    }

    public void fragment(View view) {
        Intent intent=new Intent(MainActivity.this, CatalogActivity.class);
        startActivity(intent);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {



        public void onLongPress(MotionEvent motionEvent) {
            Intent intent = new Intent(MainActivity.this, NoteActivity.class);

            startActivity(intent);

        }

    }
}