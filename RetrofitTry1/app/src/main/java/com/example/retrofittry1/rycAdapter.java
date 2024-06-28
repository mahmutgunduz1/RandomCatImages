package com.example.retrofittry1;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.List;

public class rycAdapter extends RecyclerView.Adapter<rycAdapter.rowHolder> {
    public rycAdapter(List<Models> modelsLists) {
        this.modelsLists = modelsLists;
    }

    List<Models> modelsLists;
    private String[] colors ={

            "#402E7A","#4C3BCF","#4B70F5","#3DC2EC","#FC4100","#80C4E9","#B784B7","#F7F9F2"
    };

    @NonNull
    @Override
    public rowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rcv_row, parent, false);
        return new rowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull rowHolder holder, int position) {
        holder.bind(modelsLists.get(position),colors,position);
    }

    @Override
    public int getItemCount() {
        return modelsLists.size();
    }

    public class rowHolder extends RecyclerView.ViewHolder {
        TextView txt1;
        TextView txt2;
        TextView txt3;
        ImageView img;


        public rowHolder(@NonNull View itemView) {
            super(itemView);
            txt1=itemView.findViewById(R.id.txt1);
            txt2=itemView.findViewById(R.id.txt2);
            txt3=itemView.findViewById(R.id.txt3);
            img=itemView.findViewById(R.id.img);


        }

        public void bind(Models models,String[] colors,Integer position){
            txt1.setText("id :"+models.id);
            txt2.setText("boy :"+models.height);
            txt3.setText("kilo :"+models.width);
            itemView.setBackgroundColor(Color.parseColor(colors[position%8]));
            String imageUrl = models.url;
            Picasso.get().load(imageUrl).into(img);



        }

    }
}
