package com.example.trianglesqrtnumbercheck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList _number, _type;
    CustomAdapter(Context context, ArrayList _number, ArrayList _type){
        this.context = context;
        this._number = _number;
        this._type = _type;
        Toast.makeText(context, "custom adapter is called", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_show_history, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        Toast.makeText(context, "set text is called", Toast.LENGTH_SHORT).show();
        holder.numberTextView.setText(String.valueOf(_number.get(position)));
        holder.numberTextView.setText(String.valueOf(_type.get(position)));
    }

    @Override
    public int getItemCount() {
        return _number.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView numberTextView, typeTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            numberTextView = itemView.findViewById(R.id.textViewNumber);
            typeTextView = itemView.findViewById(R.id.textViewType);
        }
    }
}
