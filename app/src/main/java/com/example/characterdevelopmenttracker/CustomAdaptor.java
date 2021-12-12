package com.example.characterdevelopmenttracker;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdaptor extends RecyclerView.Adapter<CustomAdaptor.MyViewHolder> {

    private Context context;
    private ArrayList<String> story_ids, story_titles;
    private MainActivity mainActivity;

    public CustomAdaptor(Context context, ArrayList story_ids, ArrayList story_titles,
                         MainActivity mainActivity){
        this.mainActivity = mainActivity;
        this.context = context;
        this.story_ids = story_ids;
        this.story_titles = story_titles;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);

        //delete_button = findViewById(R.id.delete_button);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.story_title_txt.setText(String.valueOf(story_titles.get(position)));
    }

    @Override
    public int getItemCount() {
        return story_ids.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView story_title_txt;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            story_title_txt = itemView.findViewById(R.id.story_title_txt);
            mainLayout = itemView.findViewById(R.id.rowLayout);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
        }


        public void onClick (View view) {
            int posn = getAdapterPosition();
            int id = Integer.parseInt(story_ids.get(posn));
            mainActivity.viewStory(id);
        }
    }
}