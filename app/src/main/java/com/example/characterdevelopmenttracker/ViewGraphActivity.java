package com.example.characterdevelopmenttracker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.characterdevelopmenttracker.databinding.ActivityViewGraphBinding;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ViewGraphActivity extends AppCompatActivity {

    private String characterID;
    private Character character;
    private ArrayList<StatRecord> records;
    private MyDatabaseHelper myDB;
    private ArrayList<Integer> stat1data;
    private ArrayList<Integer> stat2data;
    private ArrayList<Integer> stat3data;
    private ArrayList<Integer> stat4data;
    private ArrayList<Integer> stat5data;
    private TextView stat1view, stat2view, stat3view, stat4view, stat5view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_graph);

        stat1data = new ArrayList<>();
        stat2data = new ArrayList<>();
        stat3data = new ArrayList<>();
        stat4data = new ArrayList<>();
        stat5data = new ArrayList<>();

        getIntentData();
        myDB = new MyDatabaseHelper(ViewGraphActivity.this);
        character = myDB.getCharacter(characterID);
        records = myDB.getCharacterStatRecords(characterID);

        getSupportActionBar().setTitle(character.getName() + "'s Stats");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Collections.sort(records, new CustomComparator());

        stat1view = findViewById(R.id.stat1_label);
        stat2view = findViewById(R.id.stat2_label);
        stat3view = findViewById(R.id.stat3_label);
        stat4view = findViewById(R.id.stat4_label);
        stat5view = findViewById(R.id.stat5_label);

        String [] labelNames = character.getStats();

        stat1view.setText(labelNames[0]);
        stat2view.setText(labelNames[1]);
        stat3view.setText(labelNames[2]);
        stat4view.setText(labelNames[3]);
        stat5view.setText(labelNames[4]);


        for(int i = 0; i < records.size(); i++){
            int[] statvals = records.get(i).getStatVals();
            stat1data.add(statvals[0]);
            stat2data.add(statvals[1]);
            stat3data.add(statvals[2]);
            stat4data.add(statvals[3]);
            stat5data.add(statvals[4]);
        }

        GraphView graphView=(GraphView) findViewById(R.id.graph);

        LineGraphSeries<DataPoint> lseries1 = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> lseries2 = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> lseries3 = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> lseries4 = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> lseries5 = new LineGraphSeries<>();

        int y1;
        int y2;
        int y3;
        int y4;
        int y5;

        for(int x=0; x< records.size(); x++){
            y1 = stat1data.get(x);
            y2 = stat2data.get(x);
            y3 = stat3data.get(x);
            y4 = stat4data.get(x);
            y5 = stat5data.get(x);

            lseries1.appendData(new DataPoint(x, y1), true, records.size() + 1);
            lseries2.appendData(new DataPoint(x, y2), true, records.size() + 1);
            lseries3.appendData(new DataPoint(x, y3), true, records.size() + 1);
            lseries4.appendData(new DataPoint(x, y4), true, records.size() + 1);
            lseries5.appendData(new DataPoint(x, y5), true, records.size() + 1);
        }

        lseries1.setColor(Color.rgb(214,98,98));
        lseries2.setColor(Color.rgb(75,91,171));
        lseries3.setColor(Color.rgb(112,177,124));
        lseries4.setColor(Color.rgb(221,166,110));
        lseries5.setColor(Color.rgb(173,83,183));


        graphView.addSeries(lseries1);
        graphView.addSeries(lseries2);
        graphView.addSeries(lseries3);
        graphView.addSeries(lseries4);
        graphView.addSeries(lseries5);

        graphView.getViewport().setScalable(true);

    }

    public void getIntentData(){
        if(getIntent().hasExtra("id")){
            int id = getIntent().getIntExtra("id", 0);
            characterID = Integer.toString(id);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            Intent intent = new Intent(ViewGraphActivity.this, ViewCharacterActivity.class);
            intent.putExtra("id", Integer.parseInt(characterID));
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

class CustomComparator implements Comparator<StatRecord> {
    @Override
    public int compare(StatRecord o1, StatRecord o2) {
        return o1.getEventIDInteger().compareTo(o2.getEventIDInteger());
    }
}