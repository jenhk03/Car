package com.example.car.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.example.car.R;

public class DetailActivity extends AppCompatActivity
{
    private TextView tvName, tvCountry, tvEst, tvFounder, tvDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        tvName = findViewById(R.id.tv_name);
        tvCountry = findViewById(R.id.tv_country);
        tvEst = findViewById(R.id.tv_est);
        tvFounder = findViewById(R.id.tv_founder);
        tvDescription = findViewById(R.id.tv_description);
        Intent take = getIntent();
        String name = take.getStringExtra("xName");
        String country = take.getStringExtra("xCountry");
        String est = take.getStringExtra("xEst");
        String founder = take.getStringExtra("xFounder");
        String description = take.getStringExtra("xDescription");
        getSupportActionBar().setTitle(name);
        tvName.setText(name);
        tvCountry.setText(country);
        tvEst.setText(est);
        tvFounder.setText(founder);
        tvDescription.setText(description);
    }
}