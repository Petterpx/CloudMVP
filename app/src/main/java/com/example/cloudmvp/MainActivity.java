package com.example.cloudmvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.test.TestDefaultFragment;

/**
 * 测试Demo
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.ud_fragment, TestDefaultFragment.newInstance()).commit();
    }
}
