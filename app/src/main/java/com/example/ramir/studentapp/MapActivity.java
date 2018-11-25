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
    public DoubleArray<Sprite, Sprite> locationsSelected = new DoubleArray<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        drawer = findViewById(R.id.drawer);
        drawer.setActivity(this);

        locationText = findViewById(R.id.locationText);
        bFindRide = findViewById(R.id.bFindRide);
    }

    public void selectLocation(Sprite sprite) {
        locationText.setText(R.string.please_select_your_location);
        if (locationsSelected.size() == 0) {
            locationText.setText(R.string.please_select_your_destiny);
            locationsSelected.setFirst(sprite);
        } else if (locationsSelected.size() == 1 && !locationsSelected.getFirst().equals(sprite)) {
            locationsSelected.setSecond(sprite);
            selectDestiny(sprite);
        } else if (locationsSelected.size() == 2) {
            if (locationsSelected.getFirst().equals(sprite)) locationsSelected.setFirst(null);
            if (locationsSelected.getSecond().equals(sprite)) locationsSelected.setSecond(null);
            locationText.setText(R.string.please_select_your_destiny);
        }
    }

    @SuppressLint("SetTextI18n")
    public void selectDestiny(Sprite sprite) {
        bFindRide.setVisibility(View.VISIBLE);
        String first = locationsSelected.getFirst().getNode().getElement();
        String second = locationsSelected.getSecond().getNode().getElement();
        locationText.setText("From: " + first + " To: " + second);
    }
}
