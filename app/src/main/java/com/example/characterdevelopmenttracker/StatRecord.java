package com.example.characterdevelopmenttracker;

import androidx.annotation.NonNull;

public class StatRecord implements Comparable<StatRecord>{

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

    public StatRecord(){}

    @NonNull
    @Override
    public String toString() {
        String returnString = "CharacterID: " + characterID + " EventID: " + eventID + " 1st stat: ";
        returnString = returnString + statvals[0];
        return  returnString;
    }

    public int getEventID() {
        return eventID;
    }

    public Integer getEventIDInteger(){return eventID;}

    public int[] getStatVals() {
        return statvals;
    }

    public int getCharacterID() {
        return characterID;
    }

    @Override
    public int compareTo(StatRecord record) {
        Integer thisEventId = this.eventID;
        return thisEventId.compareTo(record.getEventID());
    }
}