package org.studentApp.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.ramir.studentapp.R;

import java.util.ArrayList;
import java.util.List;

public class Drawer extends View {
    List<Integer> commonList= new ArrayList<>();
    Bitmap car;
    private Canvas canvas;

    public Drawer(Context context) {
        super(context);
        init(null);
    }

    public Drawer(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public Drawer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public Drawer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){

    }

    @Override
    protected void onDraw(Canvas canvas){
        this.canvas = canvas;
        drawMap();
    }

    private void imageList(){
        String path = "R.drawable.";
        for (int i = 1; i <= 20; i++ ){
            int image = Integer.valueOf(path + "comun" + i);
            commonList.add(image);
        }
    }
    private void drawMap(){
        //imageList();
        car = BitmapFactory.decodeResource(getResources(), R.drawable.amarillo);
        Bitmap scaledCar= Bitmap.createScaledBitmap(car, 100, 100, false);
        canvas.drawBitmap(scaledCar, 0, 0, null);
    }


}
