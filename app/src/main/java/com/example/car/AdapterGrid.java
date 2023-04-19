package com.example.car;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdapterGrid extends RecyclerView.Adapter<AdapterGrid.GridViewHolder>
{
    private ArrayList<CarModel> carModels;
    public AdapterGrid(ArrayList<CarModel> carModels)
    {
        this.carModels = carModels;
    }
    public interface OnItemClickCallBack
    {
        void onItemClicked(CarModel data);
    }
    private OnItemClickCallBack callBack;
    public void setOnItemClickCallBack(OnItemClickCallBack callBack)
    {
        this.callBack = callBack;
    }
    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        return new GridViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position)
    {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                callBack.onItemClicked();
            }
        }
        );
    }
    @Override
    public int getItemCount()
    {
    }
    public class GridViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivGrid;
        public GridViewHolder(@NonNull View view)
        {
            super(view);
            ivGrid = view.findViewById(R.id.iv_grid);
        }
    }
}