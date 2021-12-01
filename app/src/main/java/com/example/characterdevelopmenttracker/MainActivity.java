package com.example.characterdevelopmenttracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_story_button;

    MyDatabaseHelper myDB;
    ArrayList<String> story_ids, story_titles;
    CustomAdaptor customAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_story_button = findViewById(R.id.add_story_button);


        add_story_button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddStoryActivity.class);
            startActivity(intent);
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        story_ids = new ArrayList<>();
        story_titles = new ArrayList<>();

        storeDataInArrays();
        customAdaptor = new CustomAdaptor(MainActivity.this, story_ids, story_titles, this);
        recyclerView.setAdapter(customAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        System.out.println("Executed Successfully");
    }

    public void storeDataInArrays(){
        Cursor cursor = myDB.readStoryData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Story Data.", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                story_ids.add(cursor.getString(0));
                story_titles.add(cursor.getString(1));
            }
        }
    }

    public void viewStory(int storyID)
    {
        Intent intent = new Intent(MainActivity.this, ViewStoryActivity.class);
        intent.putExtra("id", storyID);
        startActivity(intent);
    }

}