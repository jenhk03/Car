package com.example.car;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdapterCard extends RecyclerView.Adapter<AdapterCard.CardViewHolder>
{
    private ArrayList<CarModel> carData;
    public AdapterCard(ArrayList<CarModel> carData)
    {
        this.carData = carData;
    }
    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position)
    {
        CarModel carModel = carData.get(position);
        holder.tvName.setText(carModel.getName());
        holder.tvAbout.setText(carModel.getAbout());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String carName = carData.get(holder.getAdapterPosition()).getName();
                String carAbout = carData.get(holder.getAdapterPosition()).getAbout();
                String carPhoto = carData.get(holder.getAdapterPosition()).getPhoto();
                Intent send = new Intent(holder.itemView.getContext(), DetailActivity.class);
                send.putExtra("varName", carName);
                send.putExtra("varAbout", carAbout);
                send.putExtra("varPhoto", carPhoto);
                holder.itemView.getContext().startActivity(send);
            }
        }
        );
    }
    @Override
    public int getItemCount()
    {
        return carData.size();
    }
    public class CardViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivPhoto;
        TextView tvName, tvAbout;
        public CardViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.iv_photo);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAbout = itemView.findViewById(R.id.tv_about);
        }
    }
}