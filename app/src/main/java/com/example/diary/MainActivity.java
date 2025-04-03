package com.example.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toStr2(View v){
        Intent intent = new Intent(this, Str2.class);
        startActivity(intent);
    }

    public void toEntry(View v){
        Intent intent = new Intent(this, Entry.class);
        startActivity(intent);
    }
}