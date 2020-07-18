package com.droidtech.pythonattandancesystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DataAdapter  extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    ArrayList<Data> list ;
    Context context ;

    public DataAdapter(ArrayList<Data> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_data_layout, parent, false);
        return new DataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.time.setText(list.get(position).time);
        holder.name.setText(list.get(position).id+" , " + list.get(position).name);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name , time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name     = itemView.findViewById(R.id.name);
            time  =itemView.findViewById(R.id.time );

        }
    }
}
