package com.example.characterdevelopmenttracker;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.characterdevelopmenttracker.databinding.ActivityAddEventBinding;

import java.util.ArrayList;

public class AddEventActivity extends AppCompatActivity {

    EditText event_name_input;
    Button addEventButton;
    MyDatabaseHelper myDB;
    String storyID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        event_name_input = findViewById(R.id.event_name_input_field);
        addEventButton = findViewById(R.id.add_event_name);
        myDB = new MyDatabaseHelper(AddEventActivity.this);
        getIntentData();

        getSupportActionBar().setTitle("Add Event");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName = event_name_input.getText().toString();
                myDB.addEvent(eventName, Integer.parseInt(storyID));
                Intent intent = new Intent(AddEventActivity.this, ViewStoryActivity.class);
                intent.putExtra("id", Integer.parseInt(storyID));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            Intent intent = new Intent(AddEventActivity.this, ViewStoryActivity.class);
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