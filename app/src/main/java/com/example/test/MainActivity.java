package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cloudmvp.R;

/**
 * 测试Demo
 */
public class MainActivity extends AppCompatActivity {
    private int i = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,TestFragment.newInstance()).commit();
    }
}
