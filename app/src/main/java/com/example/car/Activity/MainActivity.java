package com.example.car.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.car.API.APIRequestData;
import com.example.car.API.RetroServer;
import com.example.car.Adapter.CarAdapter;
import com.example.car.Model.CarModel;
import com.example.car.Model.ResponseModel;
import com.example.car.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView rvCar;
    private FloatingActionButton fabAdd;
    private ProgressBar pbCar;
    private RecyclerView.Adapter adCar;
    private RecyclerView.LayoutManager lmCar;
    private List<CarModel> listCar = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvCar = findViewById(R.id.rv_car);
        fabAdd = findViewById(R.id.fab_add);
        pbCar = findViewById(R.id.pb_car);
        lmCar = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvCar.setLayoutManager(lmCar);
        fabAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        }
        );
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        retrieveCar();
    }

    public void retrieveCar()
    {
        pbCar.setVisibility(View.VISIBLE);
        APIRequestData ard = RetroServer.connectRetrofit().create(APIRequestData.class);
        Call<ResponseModel> process = ard.ardRetrieve();
        process.enqueue(new Callback<ResponseModel>()
        {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response)
            {
                String code = response.body().getCode();
                String message = response.body().getMessage();
                listCar = response.body().getData();
                adCar = new CarAdapter(MainActivity.this, listCar);
                rvCar.setAdapter(adCar);
                adCar.notifyDataSetChanged();
                pbCar.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t)
            {
                Toast.makeText(MainActivity.this, "Gagal Menghubungi Server" + t.getMessage(), Toast.LENGTH_SHORT).show();
                pbCar.setVisibility(View.GONE);
            }
        }
        );
    }
}