package com.example.characterdevelopmenttracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewEventActivity extends AppCompatActivity {

    MyDatabaseHelper myDB;
    ArrayList<Event> events;
    Story story;
    String storyID;
    RecyclerView recyclerView;
    CustomAdaptorEvents customAdaptor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);

        recyclerView = findViewById(R.id.event_recycler);

        getIntentData();
        myDB = new MyDatabaseHelper(ViewEventActivity.this);
        story = myDB.getStory(storyID);

        getSupportActionBar().setTitle(story.getTitle() + " Events");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        events = myDB.getStoryEvents(storyID);
        customAdaptor = new CustomAdaptorEvents(ViewEventActivity.this, events,
                ViewEventActivity.this);
        System.out.println("00000000000000000000000000000000000000000000" + events);
        recyclerView.setAdapter(customAdaptor);
        System.out.println(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewEventActivity.this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.back_arrow_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            Intent intent = new Intent(ViewEventActivity.this, ViewStoryActivity.class);
            intent.putExtra("id", Integer.parseInt(storyID));
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void getIntentData(){
        if(getIntent().hasExtra("id")){
            int id = getIntent().getIntExtra("id", 0);
            storyID = Integer.toString(id);
        }
    }


}