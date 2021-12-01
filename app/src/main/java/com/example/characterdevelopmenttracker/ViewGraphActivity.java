package com.example.characterdevelopmenttracker;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.characterdevelopmenttracker.databinding.ActivityViewGraphBinding;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class ViewGraphActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityViewGraphBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityViewGraphBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        GraphView graphView=(GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series1 = new LineGraphSeries<>();

    }

}