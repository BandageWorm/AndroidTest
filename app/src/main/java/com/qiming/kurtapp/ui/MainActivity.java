package com.qiming.kurtapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.qiming.kurtapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        runOnUiThread(() -> startActivity(new Intent(MainActivity.this, KotlinActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        );
    }
}
