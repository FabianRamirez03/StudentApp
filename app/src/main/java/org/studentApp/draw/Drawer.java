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

    private List<Integer> houseList(){
        List<Integer> commonList= new ArrayList<>();
        commonList.add(R.drawable.comun1);
        commonList.add(R.drawable.comun2);
        commonList.add(R.drawable.comun3);
        commonList.add(R.drawable.comun4);
        commonList.add(R.drawable.comun5);
        commonList.add(R.drawable.comun6);
        commonList.add(R.drawable.comun7);
        commonList.add(R.drawable.comun8);
        commonList.add(R.drawable.comun9);
        commonList.add(R.drawable.comun10);
        commonList.add(R.drawable.comun11);
        commonList.add(R.drawable.comun12);
        commonList.add(R.drawable.comun13);
        commonList.add(R.drawable.comun14);
        commonList.add(R.drawable.comun15);
        commonList.add(R.drawable.comun16);
        commonList.add(R.drawable.comun17);
        commonList.add(R.drawable.comun18);
        commonList.add(R.drawable.comun19);
        commonList.add(R.drawable.comun20);

        return commonList;
    }

    private List<Integer> buildingList(){
        List<Integer> buildingsList= new ArrayList<>();
        buildingsList.add(R.drawable.edificio1);
        buildingsList.add(R.drawable.edificio2);
        buildingsList.add(R.drawable.edificio3);
        buildingsList.add(R.drawable.edificio4);
        buildingsList.add(R.drawable.edificio5);
        buildingsList.add(R.drawable.edificio6);
        buildingsList.add(R.drawable.edificio7);
        buildingsList.add(R.drawable.edificio8);
        buildingsList.add(R.drawable.edificio9);
        buildingsList.add(R.drawable.edificio10);

        return buildingsList;
    }
    private void drawMap(){
        List<Integer> list = houseList();
        car = BitmapFactory.decodeResource(getResources(), list.get(0));
        Bitmap scaledCar= Bitmap.createScaledBitmap(car, 80, 80, false);
        canvas.drawBitmap(scaledCar, 0, 0, null);

    }

}
