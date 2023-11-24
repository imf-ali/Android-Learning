package com.learning.volumecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<Shape> shapeArrayList;

    MyCustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);
        shapeArrayList = new ArrayList<>();
        shapeArrayList.add(new Shape(R.drawable.sphere, "sphere"));
        shapeArrayList.add(new Shape(R.drawable.cylinder, "cylinder"));
        shapeArrayList.add(new Shape(R.drawable.cube, "cube"));
        shapeArrayList.add(new Shape(R.drawable.prism, "prism"));

        adapter = new MyCustomAdapter(shapeArrayList, getApplicationContext());
        gridView.setAdapter(adapter);
        gridView.setNumColumns(2);
    }
}