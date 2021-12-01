package com.example.characterdevelopmenttracker;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdaptorCharacters extends RecyclerView.Adapter<CustomAdaptor.MyViewHolder>{

    private Context context;
    private ArrayList<String> character_ids, character_names;
    private MainActivity mainActivity;

    public CustomAdaptorCharacters(Context context, ArrayList<String> character_ids,
                                   ArrayList<String> character_names, MainActivity mainActivity){

    }

    @NonNull
    @Override
    public CustomAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdaptor.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
