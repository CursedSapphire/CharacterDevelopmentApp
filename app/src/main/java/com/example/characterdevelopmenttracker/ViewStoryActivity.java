package com.example.characterdevelopmenttracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewStoryActivity extends AppCompatActivity {

    Button add_event_button, add_character_button, view_events_button;
    TextView title;
    MyDatabaseHelper myDB;
    String storyId;
    Story story;
    RecyclerView recyclerView;
    CustomAdaptorCharacters customAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_view);
        add_character_button = findViewById(R.id.add_character_button);
        add_event_button = findViewById(R.id.add_event_button);
        view_events_button = findViewById(R.id.view_event_button);
        title = findViewById(R.id.story_title);
        recyclerView = findViewById(R.id.character_recycler);

        getIntentData();

        myDB = new MyDatabaseHelper(ViewStoryActivity.this);
        story = myDB.getStory(storyId);

        title.setText(story.getTitle());

        add_event_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        add_character_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        view_events_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void getIntentData(){
        if(getIntent().hasExtra("id")){
            int id = getIntent().getIntExtra("id", 0);
            storyId = Integer.toString(id);
        }
    }

    public Cursor readCharacters(){
        Cursor cursor = myDB.readCharacterData();
        return cursor;
    }


}