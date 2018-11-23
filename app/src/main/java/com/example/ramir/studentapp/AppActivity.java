package com.example.ramir.studentapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class AppActivity extends AppCompatActivity {

    ImageView image;
    Canvas canvas = new Canvas();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.azul);

        canvas.drawBitmap(bmp, 0,0, null);

    }
}
