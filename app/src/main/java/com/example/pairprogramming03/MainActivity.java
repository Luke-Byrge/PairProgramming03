package com.example.pairprogramming03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    ConstraintLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        layout = (ConstraintLayout)findViewById(R.id.constraintlayout);
        Button cameraButton = (Button)findViewById(R.id.cameraButton);
        imageView = (ImageView)findViewById(R.id.imageView);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);

            }
        });

        layout.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this){
            @Override
            public void onSwipeLeft(){
                super.onSwipeLeft();
                imageView.setRotation(-90);
            }
            @Override
            public void onSwipeRight(){
                super.onSwipeRight();
                imageView.setRotation(90);
            }
            @Override
            public void onSwipeUp(){
                super.onSwipeUp();
                imageView.setRotation(0);
            }

            @Override
            public void onSwipeDown(){
                super.onSwipeDown();
                imageView.setRotation(180);
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);
    }
}