package com.example.characterdevelopmenttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewCharacterActivity extends AppCompatActivity {

    String characterID;
    TextView characterNameView;
    TextView stat1NameView;
    TextView stat2NameView;
    TextView stat3NameView;
    TextView stat4NameView;
    TextView stat5NameView;
    TextView stat1ValView;
    TextView stat2ValView;
    TextView stat3ValView;
    TextView stat4ValView;
    TextView stat5ValView;
    Character character;
    MyDatabaseHelper myDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_character);
        myDB = new MyDatabaseHelper(ViewCharacterActivity.this);
        getIntentData();

        character = myDB.getCharacter(characterID);

        characterNameView = findViewById(R.id.character_name_display);
        characterNameView.setText(character.getName());

        stat1NameView = findViewById(R.id.stat1_display);
        stat2NameView = findViewById(R.id.stat2_display);
        stat3NameView = findViewById(R.id.stat3_display);
        stat4NameView = findViewById(R.id.stat4_display);
        stat5NameView = findViewById(R.id.stat5_display);

        stat1ValView = findViewById(R.id.first_stat_field);
        stat2ValView = findViewById(R.id.second_stat_field);
        stat3ValView = findViewById(R.id.third_stat_field);
        stat4ValView = findViewById(R.id.fourth_stat_field);
        stat5ValView = findViewById(R.id.fifth_stat_field);


    }

    public void getIntentData(){
        if(getIntent().hasExtra("id")){
            int id = getIntent().getIntExtra("id", 0);
            characterID = Integer.toString(id);
        }
    }
}