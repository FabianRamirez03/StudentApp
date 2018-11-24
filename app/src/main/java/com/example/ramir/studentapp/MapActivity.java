package com.example.ramir.studentapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.studentApp.draw.Drawer;

public class MapActivity extends AppCompatActivity {

    private Drawer drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        drawer = new Drawer(this);
    }
}
