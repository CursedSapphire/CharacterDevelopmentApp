package com.example.characterdevelopmenttracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddStatRecord extends AppCompatActivity {

    String characterID;
    ArrayList<Event> unusedEvents;
    StatRecord mostRecentStatRecord;
    MyDatabaseHelper myDB;
    Spinner spinner;
    Button addStatRecordButton;
    TextView stat1;
    TextView stat2;
    TextView stat3;
    TextView stat4;
    TextView stat5;
    TextView stat1RecVal;
    TextView stat2RecVal;
    TextView stat3RecVal;
    TextView stat4RecVal;
    TextView stat5RecVal;
    EditText stat1NewVal;
    EditText stat2NewVal;
    EditText stat3NewVal;
    EditText stat4NewVal;
    EditText stat5NewVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stat_record);

        getIntentData();

        myDB = new MyDatabaseHelper(AddStatRecord.this);

        Character c = myDB.getCharacter(characterID);

        getSupportActionBar().setTitle("Create Record");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int storyID = c.getStoryId();
        int mostRecentEventID = myDB.getMostRecentCharacterEvent(characterID).getId();
        mostRecentStatRecord = myDB.getStatRecord(characterID, Integer.toString(mostRecentEventID));

        ArrayList<Event> events = myDB.getStoryEvents(Integer.toString(storyID));
        unusedEvents = new ArrayList<>();
        ArrayList<Event> usedEvents = myDB.getCharacterEvents(characterID);

        System.out.println("###########################   all Events:" + events);
        System.out.println("###########################   used Events:" + usedEvents);
        System.out.println(events.removeAll(usedEvents));
        System.out.println("###########################   Unused Events:" + events);
        unusedEvents = events;
        System.out.println("###########################   Unused Events:" + events);

        ArrayList<String> unusedEventNames = new ArrayList<>();
        for(int i = 0; i < unusedEvents.size(); i++)
            unusedEventNames.add(unusedEvents.get(i).getName());

        spinner = findViewById(R.id.event_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(AddStatRecord.this,
                android.R.layout.simple_spinner_dropdown_item, unusedEventNames);
        spinner.setAdapter(adapter);

        String[] characterStats = c.getStats();

        stat1 = findViewById(R.id.stat1_name);
        stat2 = findViewById(R.id.stat2_name);
        stat3 = findViewById(R.id.stat3_name);
        stat4 = findViewById(R.id.stat4_name);
        stat5 = findViewById(R.id.stat5_name);
        stat1.setText(characterStats[0] + ": ");
        stat2.setText(characterStats[1] + ": ");
        stat3.setText(characterStats[2] + ": ");
        stat4.setText(characterStats[3] + ": ");
        stat5.setText(characterStats[4] + ": ");

        int[] statVals = mostRecentStatRecord.getStatVals();

        stat1RecVal = findViewById(R.id.stat1_rec_val);
        stat2RecVal = findViewById(R.id.stat2_rec_val);
        stat3RecVal = findViewById(R.id.stat3_rec_val);
        stat4RecVal = findViewById(R.id.stat4_rec_val);
        stat5RecVal = findViewById(R.id.stat5_rec_val);
        stat1RecVal.setText(Integer.toString(statVals[0]));
        stat2RecVal.setText(Integer.toString(statVals[1]));
        stat3RecVal.setText(Integer.toString(statVals[2]));
        stat4RecVal.setText(Integer.toString(statVals[3]));
        stat5RecVal.setText(Integer.toString(statVals[4]));

        stat1NewVal = findViewById(R.id.stat_record_val_input1);
        stat2NewVal = findViewById(R.id.stat_record_val_input2);
        stat3NewVal = findViewById(R.id.stat_record_val_input3);
        stat4NewVal = findViewById(R.id.stat_record_val_input4);
        stat5NewVal = findViewById(R.id.stat_record_val_input5);

        addStatRecordButton = findViewById(R.id.create_stat_record_button);
        addStatRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(stat1NewVal.getText().toString()) ||
                        TextUtils.isEmpty(stat2NewVal.getText().toString()) ||
                        TextUtils.isEmpty(stat3NewVal.getText().toString()) ||
                        TextUtils.isEmpty(stat4NewVal.getText().toString()) ||
                        TextUtils.isEmpty(stat5NewVal.getText().toString()))
                {
                    Toast.makeText(AddStatRecord.this, "Empty fields not allowed.",
                            Toast.LENGTH_SHORT).show();
                }
                else
                {
                    int stat1val = Integer.parseInt(stat1NewVal.getText().toString());
                    int stat2val = Integer.parseInt(stat2NewVal.getText().toString());
                    int stat3val = Integer.parseInt(stat3NewVal.getText().toString());
                    int stat4val = Integer.parseInt(stat4NewVal.getText().toString());
                    int stat5val = Integer.parseInt(stat5NewVal.getText().toString());

                    String text = spinner.getSelectedItem().toString();
                    int recordEventID = 0;
                    for(int i = 0; i < unusedEvents.size(); i++){
                        if(unusedEvents.get(i).getName().equals(text)){
                            recordEventID = unusedEvents.get(i).getId();
                            break;
                        }
                    }

                    if(recordEventID != 0)
                        myDB.addStatRecord(characterID, Integer.toString(recordEventID), stat1val,
                                stat2val, stat3val, stat4val, stat5val);

                    Intent intent = new Intent(AddStatRecord.this, ViewCharacterActivity.class);
                    intent.putExtra("id", Integer.parseInt(characterID));
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            Intent intent = new Intent(AddStatRecord.this, ViewCharacterActivity.class);
            intent.putExtra("id", Integer.parseInt(characterID));
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