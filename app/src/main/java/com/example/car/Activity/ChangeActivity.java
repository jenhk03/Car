package com.example.car.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
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

public class ChangeActivity extends AppCompatActivity
{
    private String yId, yName, yCountry, yEst, yFounder, yDescription;
    private EditText etName, etCountry, etEst, etFounder, etDescription;
    private Button btnChange;
    private String name, country, est, founder, description;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        Intent take = getIntent();
        yId = take.getStringExtra("xId");
        yName = take.getStringExtra("xName");
        yCountry = take.getStringExtra("xCountry");
        yEst = take.getStringExtra("xEst");
        yFounder = take.getStringExtra("xFounder");
        yDescription = take.getStringExtra("xDescription");
        etName = findViewById(R.id.et_name);
        etCountry = findViewById(R.id.et_country);
        etEst = findViewById(R.id.et_est);
        etFounder = findViewById(R.id.et_founder);
        etDescription = findViewById(R.id.et_description);
        btnChange = findViewById(R.id.btn_change);
        etName.setText(yName);
        etCountry.setText(yCountry);
        etEst.setText(yEst);
        etFounder.setText(yFounder);
        etDescription.setText(yDescription);
        btnChange.setOnClickListener(new View.OnClickListener()
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
                    changeCar();
                }
            }
        }
        );
    }
    private void changeCar()
    {
        APIRequestData ard = RetroServer.connectRetrofit().create(APIRequestData.class);
        Call<ResponseModel> process = ard.ardUpdate(yId, name, country, est, founder, description);
        process.enqueue(new Callback<ResponseModel>()
        {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response)
            {
                String code = response.body().getCode();
                String message = response.body().getMessage();
                Toast.makeText(ChangeActivity.this, "Kode: " + code + ", Pesan: " + message, Toast.LENGTH_SHORT).show();
                finish();
            }
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t)
            {
                Toast.makeText(ChangeActivity.this, "Gagal Menghubungi Server: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        );
    }
}