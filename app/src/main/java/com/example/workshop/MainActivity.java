package com.example.workshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BoxFragment boxFragment = new BoxFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, boxFragment).commit();
    }
}