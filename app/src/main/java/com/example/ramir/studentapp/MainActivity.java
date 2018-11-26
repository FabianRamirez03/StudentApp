package com.example.ramir.studentapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openScannerActivity(View view) {
        Intent intent = new Intent(this, Scanner2Activity.class);
        startActivity(intent);
    }
    public void openFacebookActivity(View view) {
        if(Scanner2Activity.scanResult == null){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                setContentView(R.layout.activity_main);
            }

        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                setContentView(R.layout.activity_main);
            }
        });
        builder.setMessage("Must first scan student Id barcode");
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }else {
        Intent intent = new Intent(this, FacebookActivity.class);
        startActivity(intent);
        }
    }
}
