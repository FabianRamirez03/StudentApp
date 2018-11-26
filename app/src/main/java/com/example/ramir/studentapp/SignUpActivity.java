package com.example.ramir.studentapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
    public void openScannerActivity(View view) {
        Intent intent = new Intent(this, Scanner2Activity.class);
        startActivity(intent);
    }
    public void openFacebookActivity(View view) {
        Intent intent = new Intent(this, FacebookActivity.class);
        startActivity(intent);
    }
}
