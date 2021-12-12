package com.example.characterdevelopmenttracker;

import java.util.ArrayList;

public class Story {
    private ArrayList<Character> characters;
    private ArrayList<Event> events;
    private String title;
    private int id;

    public Story(String title, int id){
        this.characters = new ArrayList<>();
        this.events = new ArrayList<>();
        this.title = title;
        this.id = id;
    }

    public Story(){}

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public ArrayList<Event> getEvents() {
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

    public void addCharacter(Character character){
        characters.add(character);
    }

    public void setCharacters(ArrayList<Character> characters){this.characters = characters;}

    public void addEvent(Event event){
        events.add(event);
    }

    public void setEvents(ArrayList<Event> events){this.events = events;}
}