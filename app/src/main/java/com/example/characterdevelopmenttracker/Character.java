package com.example.characterdevelopmenttracker;

import java.util.ArrayList;

public class Character {
    private Story story;
    private String name;
    private int id;
    private ArrayList<Event> events;
    private String[] stats;

    public Character(String name, Story story, int id, String stat1, String stat2, String stat3, String stat4, String stat5){
        this.story = story;
        this.name = name;
        this.id = id;
        this.events = new ArrayList<>();
        this.stats = new String[5];
        stats[0] = stat1;
        stats[1] = stat2;
        stats[2] = stat3;
        stats[3] = stat4;
        stats[4] = stat5;
    }


}
