package com.example.characterdevelopmenttracker;

import android.content.Context;
import android.os.Bundle;

import com.example.characterdevelopmenttracker.databinding.ActivityAddCharacterBinding;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class AddCharacterActivity extends AppCompatActivity {

    private ActivityAddCharacterBinding binding;

    EditText name_input;
    EditText stat1_input;
    EditText stat2_input;
    EditText stat3_input;
    EditText stat4_input;
    EditText stat5_input;
    EditText stat1_val_input;
    EditText stat2_val_input;
    EditText stat3_val_input;
    EditText stat4_val_input;
    EditText stat5_val_input;
    Button add_character_button;
    String storyId;

    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_character);
        context = getApplicationContext();

        name_input = findViewById(R.id.character_name_input);
        stat1_input= findViewById(R.id.stat_name_input1);
        stat2_input= findViewById(R.id.stat_name_input2);
        stat3_input= findViewById(R.id.stat_name_input3);
        stat4_input= findViewById(R.id.stat_name_input4);
        stat5_input= findViewById(R.id.stat_name_input5);
        stat1_val_input=findViewById(R.id.stat_val_input1);
        stat2_val_input=findViewById(R.id.stat_val_input2);
        stat3_val_input=findViewById(R.id.stat_val_input3);
        stat4_val_input=findViewById(R.id.stat_val_input4);
        stat5_val_input=findViewById(R.id.stat_val_input5);

        getIntentData();

        add_character_button = findViewById(R.id.create_character_button);
        System.out.println("=========================" + add_character_button);

        add_character_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("DREAM DREAM DREAM DREAM DREAM DREAM DREAM");


                if(TextUtils.isEmpty(stat1_input.getText().toString()) ||
                        TextUtils.isEmpty(stat2_input.getText().toString()) ||
                        TextUtils.isEmpty(stat3_input.getText().toString()) ||
                        TextUtils.isEmpty(stat4_input.getText().toString()) ||
                        TextUtils.isEmpty(stat5_input.getText().toString()) ||
                        TextUtils.isEmpty(stat1_val_input.getText().toString()) ||
                        TextUtils.isEmpty(stat2_val_input.getText().toString()) ||
                        TextUtils.isEmpty(stat3_val_input.getText().toString()) ||
                        TextUtils.isEmpty(stat4_val_input.getText().toString()) ||
                        TextUtils.isEmpty(stat5_val_input.getText().toString()) ||
                        TextUtils.isEmpty(name_input.getText().toString()))
                {
                    Toast.makeText(AddCharacterActivity.this, "Empty fields not allowed.",
                    Toast.LENGTH_SHORT).show();
                }
                else {
                    String characterName = name_input.getText().toString();

                    String stat1 = stat1_input.getText().toString();
                    String stat2 = stat2_input.getText().toString();
                    String stat3 = stat3_input.getText().toString();
                    String stat4 = stat4_input.getText().toString();
                    String stat5 = stat5_input.getText().toString();


                    int stat1val = Integer.parseInt(stat1_val_input.getText().toString());
                    int stat2val = Integer.parseInt(stat2_val_input.getText().toString());
                    int stat3val = Integer.parseInt(stat3_val_input.getText().toString());
                    int stat4val = Integer.parseInt(stat4_val_input.getText().toString());
                    int stat5val = Integer.parseInt(stat5_val_input.getText().toString());

                    if (stat1.equals("") || stat2.equals("") || stat3.equals("") || stat4.equals("")
                            || stat5.equals("") || characterName.equals("")) {
                        Toast.makeText(context, "Blank names not allowed.",
                                Toast.LENGTH_SHORT).show();
                    }
                    else {
                        MyDatabaseHelper myDB = new MyDatabaseHelper(AddCharacterActivity.this);
                        myDB.addCharacter(characterName, stat1, stat2, stat3, stat4, stat5, stat1val, stat2val,
                                stat3val, stat4val, stat5val, storyId);
                        Character character = myDB.getStoryCharacters(storyId).get(myDB.getStoryCharacters(storyId).size() - 1);
                        System.out.println("DREAM DREAM DREAM DREAM DREAM DREAM DREAM" + character.toString());
                    }
                }
            }
        });

    }

    public void getIntentData(){
        if(getIntent().hasExtra("id")){
            int id = getIntent().getIntExtra("id", 0);
            storyId = Integer.toString(id);
        }
    }
}