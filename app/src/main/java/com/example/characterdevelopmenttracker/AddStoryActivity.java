package com.example.characterdevelopmenttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStoryActivity extends AppCompatActivity {

    EditText title_input;
    Button add_story_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story);

        title_input = findViewById(R.id.input_title);
        add_story_button = findViewById(R.id.save_story_button);

        add_story_button.setOnClickListener(view -> {
            if(TextUtils.isEmpty(title_input.getText().toString()))
            {
                Toast.makeText(AddStoryActivity.this, "Empty fields not allowed.",
                        Toast.LENGTH_SHORT).show();
            }
            else {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddStoryActivity.this);
                myDB.addStory(title_input.getText().toString());
                Intent intent = new Intent(AddStoryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}