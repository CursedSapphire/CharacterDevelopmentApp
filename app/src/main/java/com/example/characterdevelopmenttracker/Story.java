package com.example.characterdevelopmenttracker;

import java.util.ArrayList;

public class Story {
    private ArrayList<String> characters;
    private ArrayList<String> events;
    private String title;
    private int id;

    public Story(String title, int id){
        this.characters = new ArrayList<>();
        this.events = new ArrayList<>();
        this.title = title;
        this.id = id;
    }

    public ArrayList<String> getCharacters() {
        return characters;
    }

    public ArrayList<String> getEvents() {
        return events;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addCharacter(String character){
        characters.add(character);
    }

    public void addEvent(String event){
        characters.add(event);
    }
}
