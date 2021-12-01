package com.example.characterdevelopmenttracker;

import java.util.ArrayList;

public class Event {
    private String name;
    private int id;
    private Story associatedStory;
    private ArrayList<Character> characters;
    private ArrayList<Integer> characterStatVals;

    public Event(String name, int id, Story story){
        this.name = name;
        this.id = id;
        this.associatedStory = story;
        characters = new ArrayList<>();
        characterStatVals = new ArrayList<>();
    }

    public int[] getCharacterStats(String CharacterName){
        int[] returnStats = new int[5];
        return returnStats;
    }
}
