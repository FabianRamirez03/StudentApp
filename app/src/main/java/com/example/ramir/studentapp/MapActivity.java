package com.example.ramir.studentapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ramir.studentapp.draw.Drawer;
import com.example.ramir.studentapp.draw.Sprite;
import com.example.ramir.studentapp.util.DoubleArray;

public class MapActivity extends AppCompatActivity {

    private Drawer drawer;
    private TextView locationText;
    private Button bFindRide;
    private int textMode = 0;
    public DoubleArray<Sprite, Sprite> locationsSelected = new DoubleArray<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        drawer = new Drawer(this);
        drawer.setActivity(this);

        locationText = findViewById(R.id.locationText);
        bFindRide = findViewById(R.id.bFindRide);
    }

    public void selectLocation(Sprite sprite) {
        if (locationsSelected.getFirst() != null && !locationsSelected.getFirst().equals(sprite) && textMode == 1){
            selectDestiny(sprite);
            textMode = 2;
        } else if (textMode == 0) {
            locationText.setText(R.string.please_select_your_destiny);
            locationsSelected.setFirst(sprite);
            textMode = 1;
        } else if (textMode == 1){
            locationText.setText(R.string.please_select_your_location);
            locationsSelected.setFirst(null);
            textMode = 0;
        } else {
            locationText.setText(R.string.please_select_your_destiny);
            textMode = 1;
        }
    }

    @SuppressLint("SetTextI18n")
    public void selectDestiny(Sprite sprite) {
        bFindRide.setVisibility(View.VISIBLE);
        locationsSelected.setSecond(sprite);
        String first = locationsSelected.getFirst().getNode().getElement();
        String second = locationsSelected.getSecond().getNode().getElement();
        locationText.setText("From: " + first + " To: " + second);
    }
}
