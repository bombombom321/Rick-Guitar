package com.example.myapplication.core.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.myapplication.R;

public class StartActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        findViewById(R.id.select_guitar_button).setOnClickListener(v ->
                startActivity(new Intent(this, ListActivity.class)));

        findViewById(R.id.search_guitar_button).setOnClickListener(v ->
                startActivity(new Intent(this, SearchActivity.class)));

        findViewById(R.id.insert_guitar_button).setOnClickListener(v ->
                startActivity(new Intent(this, InsertActivity.class)));
    }
}