package com.example.characterdevelopmenttracker;

import java.util.ArrayList;

public class Event {
    private String name;
    private int id;
    private int storyId;
    private int position;
    private ArrayList<Character> characters;
    private ArrayList<Integer> characterStatVals;

    public Event(String name, int id, int story, int position){
        this.name = name;
        this.id = id;
        this.storyId = story;
        this.position = position;
        characters = new ArrayList<>();
        characterStatVals = new ArrayList<>();
    }

    public Event(){}

    public int[] getCharacterStats(String CharacterName){
        int[] returnStats = new int[5];
        return returnStats;
    }

    public String getName(){return this.name;}

    public int getPosition() {
        return position;
    }

    public int getId(){return this.id;}

    @Override
    public boolean equals(Object anObject) {
        if (!(anObject instanceof Event)) {
            return false;
        }
        Event otherMember = (Event) anObject;
        return otherMember.getId() == getId();
    }
}
