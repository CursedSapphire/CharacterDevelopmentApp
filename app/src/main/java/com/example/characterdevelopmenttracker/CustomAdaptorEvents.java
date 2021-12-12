package com.example.characterdevelopmenttracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdaptorEvents extends RecyclerView.Adapter<CustomAdaptorEvents.MyViewHolder> {

    private Context context;
    private ArrayList<Event> events;
    ViewEventActivity viewEventActivity;

    public CustomAdaptorEvents(Context context, ArrayList<Event> events,
                               ViewEventActivity viewEventActivity ){
        this.context = context;
        this.events = events;
        this.viewEventActivity = viewEventActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.event_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.eventName.setText(events.get(position).getName());
        String posn = Integer.toString(events.get(position).getPosition() + 1);
        holder.eventPosition.setText(posn);
    }

    @Override
    public int getItemCount() {
        System.out.println("8888888888888888888888888" + events.size());
        return events.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView eventPosition, eventName;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            eventPosition = itemView.findViewById(R.id.event_num_view);
            eventName = itemView.findViewById(R.id.event_name_display);
        }
    }
}