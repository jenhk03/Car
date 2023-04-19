package com.example.car;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity
{
    private ImageView ivPhoto;
    private TextView tvName, tvAbout;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ivPhoto = findViewById(R.id.iv_photo);
        tvName = findViewById(R.id.tv_name);
        tvAbout = findViewById(R.id.tv_about);
        Intent acc = getIntent();
        String name = acc.getStringExtra("varName");
        String about = acc.getStringExtra("varAbout");
        String photo = acc.getStringExtra("varPhoto");
        getSupportActionBar().setTitle(name);
        tvName.setText(name);
        tvAbout.setText(about);
    }
}