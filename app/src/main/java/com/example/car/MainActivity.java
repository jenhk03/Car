package com.example.car;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView rvCar;
    private ArrayList<CarModel> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Card View Model");
        rvCar = findViewById(R.id.rv_car);
        rvCar.setHasFixedSize(true);
    }
}