package com.example.characterdevelopmenttracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
    Button addStatRecordButton;
    Button viewGraphButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_character);
        myDB = new MyDatabaseHelper(ViewCharacterActivity.this);
        getIntentData();

        character = myDB.getCharacter(characterID);

        getSupportActionBar().setTitle(character.getName() + " Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int mostRecentEventID = myDB.getMostRecentCharacterEvent(characterID).getId();
        StatRecord mostRecentStatRecord = myDB.getStatRecord(characterID,
                Integer.toString(mostRecentEventID));

        int [] statVals = mostRecentStatRecord.getStatVals();
        String [] characterStats = character.getStats();

        characterNameView = findViewById(R.id.character_name_title);
        characterNameView.setText(character.getName());

        stat1NameView = findViewById(R.id.stat1_display);
        stat2NameView = findViewById(R.id.stat2_display);
        stat3NameView = findViewById(R.id.stat3_display);
        stat4NameView = findViewById(R.id.stat4_display);
        stat5NameView = findViewById(R.id.stat5_display);

        stat1NameView.setText(characterStats[0]);
        stat2NameView.setText(characterStats[1]);
        stat3NameView.setText(characterStats[2]);
        stat4NameView.setText(characterStats[3]);
        stat5NameView.setText(characterStats[4]);

        stat1ValView = findViewById(R.id.first_stat_field);
        stat2ValView = findViewById(R.id.second_stat_field);
        stat3ValView = findViewById(R.id.third_stat_field);
        stat4ValView = findViewById(R.id.fourth_stat_field);
        stat5ValView = findViewById(R.id.fifth_stat_field);

        stat1ValView.setText(Integer.toString(statVals[0]));
        stat2ValView.setText(Integer.toString(statVals[1]));
        stat3ValView.setText(Integer.toString(statVals[2]));
        stat4ValView.setText(Integer.toString(statVals[3]));
        stat5ValView.setText(Integer.toString(statVals[4]));

        addStatRecordButton = findViewById(R.id.add_stat_record_button);
        addStatRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewCharacterActivity.this, AddStatRecord.class);
                intent.putExtra("id", Integer.parseInt(characterID));
                startActivity(intent);
            }
        });

        viewGraphButton = findViewById(R.id.view_graph_button);
        viewGraphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewCharacterActivity.this, ViewGraphActivity.class);
                intent.putExtra("id", Integer.parseInt(characterID));
                startActivity(intent);
            }
        });
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
            myDB.removeCharacter(Integer.parseInt(characterID));
            Intent intent = new Intent(ViewCharacterActivity.this, ViewStoryActivity.class);
            intent.putExtra("id", character.getStoryId());
            startActivity(intent);
        }
        if(item.getItemId() == android.R.id.home){
            Intent intent = new Intent(ViewCharacterActivity.this, ViewStoryActivity.class);
            intent.putExtra("id", character.getStoryId());
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void getIntentData(){
        if(getIntent().hasExtra("id")){
            int id = getIntent().getIntExtra("id", 0);
            characterID = Integer.toString(id);
        }
    }
}