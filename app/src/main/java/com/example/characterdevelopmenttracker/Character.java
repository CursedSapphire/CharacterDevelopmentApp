package com.example.characterdevelopmenttracker;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Character {
    private int storyId;
    private String name;
    private int id;
    private ArrayList<Event> events;
    private String[] stats;

    public Character(String name, int storyId, int id){
        this.storyId = storyId;
        this.name = name;
        this.id = id;
        this.events = new ArrayList<>();
        this.stats = new String[5];
    }

    public Character(){}

    public void setStats(String s1, String s2, String s3, String s4, String s5){
        stats[0] = s1;
        stats[1] = s2;
        stats[2] = s3;
        stats[3] = s4;
        stats[4] = s5;
    }

    public int getId(){return this.id;}

    public String getName(){return this.name;}

    @NonNull
    @Override
    public String toString() {
        String string = name + " " + stats[0] + " " + stats[1] + " " + stats[2] + " " + stats[3] +
                " " + stats[4];
        return string;
    }
}
