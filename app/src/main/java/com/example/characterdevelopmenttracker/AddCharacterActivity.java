package com.example.characterdevelopmenttracker;

import android.os.Bundle;

import com.example.characterdevelopmenttracker.databinding.ActivityAddCharacterBinding;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.characterdevelopmenttracker.databinding.ActivityAddCharacterBinding;

public class AddCharacterActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityAddCharacterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddCharacterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

}