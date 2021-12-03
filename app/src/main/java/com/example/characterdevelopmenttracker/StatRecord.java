package com.example.characterdevelopmenttracker;

public class StatRecord {

    private int characterID;
    private int eventID;
    private int[] statvals;

    public StatRecord(int characterID, int eventID, int stat1val, int stat2val, int stat3val,
                      int stat4val, int stat5val){
        this.characterID = characterID;
        this.eventID = eventID;
        this.statvals = new int[5];
        statvals[0] = stat1val;
        statvals[1] = stat2val;
        statvals[2] = stat3val;
        statvals[3] = stat4val;
        statvals[4] = stat5val;
    }

    public int getEventID() {
        return eventID;
    }

    public int[] getStatvals() {
        return statvals;
    }

    public int getCharacterID() {
        return characterID;
    }
}
