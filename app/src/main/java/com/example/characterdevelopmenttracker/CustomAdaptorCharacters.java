package com.example.characterdevelopmenttracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdaptorCharacters extends RecyclerView.Adapter<CustomAdaptorCharacters.MyViewHolder>{

    private Context context;
    private ArrayList<String> character_ids, character_names;
    private MainActivity mainActivity;

    public CustomAdaptorCharacters(Context context, ArrayList<String> character_names){
        this.context = context;
        this.character_names = character_names;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.character_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.character_name.setText(String.valueOf(character_names.get(position)));
    }

    @Override
    public int getItemCount() {
        return character_names.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView character_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.character_name = itemView.findViewById(R.id.character_name_list_view);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
