package com.example.car.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.example.car.API.APIRequestData;
import com.example.car.API.RetroServer;
import com.example.car.Activity.ChangeActivity;
import com.example.car.Activity.MainActivity;
import com.example.car.Model.CarModel;
import com.example.car.Model.ResponseModel;
import com.example.car.R;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarAdapter
{
    private Context ctx;
    private List<CarModel> listCar;
    public CarAdapter(Context ctx, List<CarModel> listCar)
    {
        this.ctx = ctx;
        this.listCar = listCar;
    }
    @NonNull
    @Override
    public VHCar onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_car, parent, false);
        return new VHCar(view);
    }
    @Override
    public void onBindViewHolder(@NonNull VHCar holder, int position)
    {
        CarModel cm = listCar.get(position);
        holder.tvId.setText(cm.getId());
        holder.tvName.setText(cm.getName());
        holder.tvCountry.setText(cm.getCountry());
        holder.tvEst.setText(cm.getEst());
        holder.tvFounder.setText(cm.getFounder());
        holder.tvDescription.setText(cm.getDescription());
    }
    @Override
    public int getItemCount()
    {
        return listCar.size();
    }
    public class VHCar extends RecyclerView.ViewHolder
    {
        TextView tvId, tvName, tvCountry, tvEst, tvFounder, tvDescription;
        public VHCar(@NonNull View itemView)
        {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvCountry = itemView.findViewById(R.id.tv_country);
            tvEst = itemView.findViewById(R.id.tv_est);
            tvFounder = itemView.findViewById(R.id.tv_founder);
            tvDescription = itemView.findViewById(R.id.tv_description);
            itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    AlertDialog.Builder message = new AlertDialog.Builder(ctx);
                    message.setTitle("Perhatian");
                    message.setMessage("Operasi apa yang akan dilakukan?");
                    message.setCancelable(true);
                    message.setNegativeButton("Hapus", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            delCar(tvId.getText().toString());
                            dialog.dismiss();
                        }
                    }
                    );
                    message.setPositiveButton("Ubah", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Intent move = new Intent(ctx, ChangeActivity.class);
                            move.putExtra("xId", tvId.getText().toString());
                            move.putExtra("xName", tvName.getText().toString());
                            move.putExtra("xCountry", tvCountry.getText().toString());
                            move.putExtra("xEst", tvEst.getText().toString());
                            move.putExtra("xFounder", tvFounder.getText().toString());
                            move.putExtra("xDescription", tvDescription.getText().toString());
                            ctx.startActivity(move);
                        }
                    }
                    );
                    message.show();
                    return false;
                }
            }
            );
        }
        private void delCar(String idCar)
        {
            APIRequestData ard = RetroServer.connectRetrofit().create(APIRequestData.class);
            Call<ResponseModel> process = ard.ardDelete(idCar);
            process.enqueue(new Callback<ResponseModel>()
            {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response)
                {
                    String code = response.body().getCode();
                    String message = response.body().getMessage();
                    Toast.makeText(ctx, "Kode: " + code + ", Pesan: " + message, Toast.LENGTH_SHORT).show();
                    ((MainActivity) ctx).retrieveCar();
                }
                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t)
                {
                    Toast.makeText(ctx, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
                }
            }
            );
        }
    }
}