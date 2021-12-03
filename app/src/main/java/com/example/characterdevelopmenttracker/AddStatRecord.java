package com.example.characterdevelopmenttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class AddStatRecord extends AppCompatActivity {

    String characterID;
    ArrayList<Event> unusedEvents;
    MyDatabaseHelper myDB;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stat_record);

        getIntentData();
        myDB = new MyDatabaseHelper(AddStatRecord.this);

        Character c = myDB.getCharacter(characterID);
        int storyID = c.getStoryId();
        ArrayList<Event> allStoryEvents = myDB.getStoryEvents(Integer.toString(storyID));
        ArrayList<StatRecord> records = myDB.getCharacterStatRecords(characterID);
        unusedEvents = new ArrayList<>();


        for(int i = 0; i < records.size(); i++)
        {
            if(records.get(i).getCharacterID() != Integer.parseInt(characterID)){
                int eventID = records.get(i).getEventID();
                for(int j = 0; j < allStoryEvents.size(); j++)
                {
                    if(allStoryEvents.get(j).getId() == eventID){
                        unusedEvents.add( allStoryEvents.get(j));
                        break;
                    }
                }
            }
        }

        ArrayList<String> unusedEventNames = new ArrayList<>();
        for(int i = 0; i < unusedEvents.size(); i++)
            unusedEventNames.add(unusedEvents.get(i).getName());

        spinner = findViewById(R.id.event_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(AddStatRecord.this,
                android.R.layout.simple_spinner_dropdown_item, unusedEventNames);
        spinner.setAdapter(adapter);
    }

    public void getIntentData(){
        if(getIntent().hasExtra("id")){
            int id = getIntent().getIntExtra("id", 0);
            characterID = Integer.toString(id);
        }
    }
}