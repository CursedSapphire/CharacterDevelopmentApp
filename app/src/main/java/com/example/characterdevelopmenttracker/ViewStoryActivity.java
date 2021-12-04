package com.example.characterdevelopmenttracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.Comparator;

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

        getSupportActionBar().setTitle(story.getTitle() + " Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<Event> events = myDB.getStoryEvents(storyId);
        ArrayList<Character> characters = myDB.getStoryCharacters(storyId);
        System.out.println("Length of Events: " + events.size());
        System.out.println("Length of Characters: " +characters.size());
        ArrayList<String> characterNames = new ArrayList<>();
        ArrayList<String> characterIds = new ArrayList<>();
        for(int i = 0; i < characters.size(); i++)
        {
            characterNames.add(characters.get(i).getName());
            characterIds.add(Integer.toString(characters.get(i).getId()));
        }

        customAdaptor = new CustomAdaptorCharacters(ViewStoryActivity.this, characterNames,
                characterIds, ViewStoryActivity.this);
        recyclerView.setAdapter(customAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewStoryActivity.this));

        title.setText(story.getTitle());

        add_event_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewStoryActivity.this, AddEventActivity.class);
                intent.putExtra("id", Integer.parseInt(storyId));
                startActivity(intent);
            }
        });

        add_character_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewStoryActivity.this, AddCharacterActivity.class);
                intent.putExtra("id", Integer.parseInt(storyId));
                startActivity(intent);
            }
        });

        view_events_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewStoryActivity.this, ViewEventActivity.class);
                intent.putExtra("id", Integer.parseInt(storyId));
                startActivity(intent);
            }
        });
    }

    public void getIntentData(){
        if(getIntent().hasExtra("id")){
            int id = getIntent().getIntExtra("id", 0);
            storyId = Integer.toString(id);
        }
    }

    public void viewCharacter(int id){
        Intent intent = new Intent(ViewStoryActivity.this, ViewCharacterActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public Cursor readCharacters(){
        Cursor cursor = myDB.readCharacterData();
        return cursor;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_button){
            myDB.removeStory(Integer.parseInt(storyId));
            Intent intent = new Intent(ViewStoryActivity.this, MainActivity.class);
            startActivity(intent);
        }
        if(item.getItemId() == android.R.id.home){
            Intent intent = new Intent(ViewStoryActivity.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}