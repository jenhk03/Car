package com.example.car.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.car.API.APIRequestData;
import com.example.car.API.RetroServer;
import com.example.car.Model.ResponseModel;
import com.example.car.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivity extends AppCompatActivity
{
    private EditText etName, etCountry, etEst, etFounder, etDescription;
    private Button btnAdd;
    private String name, country, est, founder, description;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        etName = findViewById(R.id.et_name);
        etCountry = findViewById(R.id.et_country);
        etEst = findViewById(R.id.et_est);
        etFounder = findViewById(R.id.et_founder);
        etDescription = findViewById(R.id.et_description);
        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                name = etName.getText().toString();
                country = etCountry.getText().toString();
                est = etEst.getText().toString();
                founder = etFounder.getText().toString();
                description = etDescription.getText().toString();
                if (name.trim().isEmpty())
                {
                    etName.setError("Nama tidak boleh kosong");
                }
                else if (country.trim().isEmpty())
                {
                    etCountry.setError("Asal negara tidak boleh kosong");
                }
                else if (est.trim().isEmpty())
                {
                    etEst.setError("Tahun berdiri tidak boleh kosong");
                }
                else if (founder.trim().isEmpty())
                {
                    etFounder.setError("Pendiri tidak boleh kosong");
                }
                else if (description.trim().isEmpty())
                {
                    etDescription.setError("Deskripsi tidak boleh kosong");
                }
                else
                {
                    addCar();
                }
            }
        }
        );
    }
    private void addCar()
    {
        APIRequestData ard = RetroServer.connectRetrofit().create(APIRequestData.class);
        Call<ResponseModel> process = ard.ardCreate(name, country, est, founder, description);
        process.enqueue(new Callback<ResponseModel>()
        {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response)
            {
                String code = response.body().getCode();
                String message = response.body().getMessage();
                Toast.makeText(AddActivity.this, "Kode: " + code + ", Pesan: " + message, Toast.LENGTH_SHORT).show();
                finish();
            }
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t)
            {
                Toast.makeText(AddActivity.this, "Gagal Menghubungi Server: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        );
    }
}