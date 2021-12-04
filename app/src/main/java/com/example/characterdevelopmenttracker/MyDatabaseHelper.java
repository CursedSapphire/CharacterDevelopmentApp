package com.example.characterdevelopmenttracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "StoryLibrary.db";
    public static final int DATABASE_VERSION = 1;

    //Table Names
    public static final String TABLE_STORIES = "stories";
    public static final String TABLE_CHARACTERS = "characters";
    public static final String TABLE_EVENTS = "events";
    public static final String TABLE_LINKED_EVENTS = "linked_events";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_STORY_ID = "story_id";

    // STORIES Table - column names
    private static final String KEY_TITLE = "title";
    private static final String KEY_CHARACTERS_ID = "characters_id";
    private static final String KEY_EVENTS_ID = "events_id";

    // CHARACTER Table - column names
    private static final String KEY_LINKED_EVENTS_ID = "linked_events_id";
    private static final String KEY_STAT1 = "first_stat";
    private static final String KEY_STAT2 = "second_stat";
    private static final String KEY_STAT3 = "third_stat";
    private static final String KEY_STAT4 = "fourth_stat";
    private static final String KEY_STAT5 = "fifth_stat";

    // EVENT Table - column names
    private static final String KEY_EVENT_POSITION = "event_position";

    //LINKED_EVENTS Table - column names
    private static final String KEY_EVENT_ID = "story_id";
    private static final String KEY_CHAR_ID = "character_id";
    private static final String KEY_STAT1_NAME = "first_stat";
    private static final String KEY_STAT2_NAME = "second_stat";
    private static final String KEY_STAT3_NAME = "third_stat";
    private static final String KEY_STAT4_NAME = "fourth_stat";
    private static final String KEY_STAT5_NAME = "fifth_stat";
    private static final String KEY_STAT1_VAL = "first_stat_val";
    private static final String KEY_STAT2_VAL = "second_stat_val";
    private static final String KEY_STAT3_VAL = "third_stat_val";
    private static final String KEY_STAT4_VAL = "fourth_stat_val";
    private static final String KEY_STAT5_VAL = "fifth_stat_val";

    public static final String COLEMN_ID = "_id";
    public static final String COLEMN_TITLE = "story_title";
    public static final String COLEMN_CHARACTER_TABLE_ID = "character_id";
    public static final String COLEMN_EVENT_TABLE_ID = "event_id";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String storyQuery =
                "CREATE TABLE " + TABLE_STORIES +
                    " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                    KEY_TITLE + " TEXT )";

        String characterQuery =
                "CREATE TABLE "  + TABLE_CHARACTERS + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                        KEY_NAME + " TEXT, " + KEY_STORY_ID + " INTEGER, " +
                        KEY_STAT1 + " TEXT, " +
                        KEY_STAT2 + " TEXT, " + KEY_STAT3 + " TEXT, " +KEY_STAT4 + " TEXT, "
                        + KEY_STAT5 + " TEXT )";

        String eventQuery = "CREATE TABLE " + TABLE_EVENTS + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                KEY_NAME + " TEXT, " + KEY_EVENT_ID + " INTEGER, " +
                KEY_EVENT_POSITION + " INTEGER )";

        String linkedEventQuery = "CREATE TABLE " + TABLE_LINKED_EVENTS + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                KEY_EVENT_ID + " INTEGER, " + KEY_CHAR_ID + " INTEGER, " +
                KEY_STAT1_NAME + " TEXT, " + KEY_STAT2_NAME + " TEXT, " + KEY_STAT3_NAME + " TEXT, " +
                KEY_STAT4_NAME + " TEXT, " + KEY_STAT5_NAME + " TEXT, " + KEY_STAT1_VAL + " INTEGER, " +
                KEY_STAT2_VAL + " INTEGER, " + KEY_STAT3_VAL + " INTEGER, " + KEY_STAT4_VAL + " INTEGER, "
                + KEY_STAT5_VAL + " INTEGER )";

        database.execSQL(storyQuery);
        database.execSQL(characterQuery);
        database.execSQL(eventQuery);
        database.execSQL(linkedEventQuery);


    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_CHARACTERS);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_STORIES);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_LINKED_EVENTS);

        onCreate(database);

    }

    public void addStory(String title){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_TITLE, title);
        long result = db.insert(TABLE_STORIES, null, cv);
        if(result == -1)
        {
            Toast.makeText(context, "Failed to add story.", Toast.LENGTH_SHORT).show();
        }
        addEvent("Beginning", (int) result);
    }

    public void removeStory(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Story story = getStory(Integer.toString(id));
        ArrayList<Event> events = getStoryEvents(Integer.toString(id));
        ArrayList<Character> characters = getStoryCharacters(Integer.toString(id));

        db.delete(TABLE_STORIES, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });

        db.delete(TABLE_EVENTS, KEY_STORY_ID + " = ?",
                new String[] { String.valueOf(id) });

        db.delete(TABLE_CHARACTERS, KEY_STORY_ID + " = ?",
                new String[] { String.valueOf(id) });

    }

    public void removeCharacter(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_CHARACTERS, KEY_ID + " = ?",
                new String[] { String.valueOf(id)});
        removeStatRecordChar(id);
    }

    public void removeEvent(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EVENTS, KEY_ID + " = ?", new String[] {String.valueOf(id)});
        removeStatRecordEvent(id);
    }

    public void removeStatRecordChar(int characterID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LINKED_EVENTS, KEY_CHAR_ID + " = ?",
                new String[] {String.valueOf(characterID)});
    }

    public void removeStatRecordEvent(int eventID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LINKED_EVENTS, KEY_EVENT_ID + " = ?",
                new String[] {String.valueOf(eventID)});
    }

    public Cursor readStoryData(){
        String query = "SELECT * FROM " + TABLE_STORIES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor readCharacterData(){
        String query = "SELECT * FROM " + TABLE_CHARACTERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Story getStory(String id){
        Story story = new Story();
        SQLiteDatabase myDB = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_STORIES + " WHERE "
                + KEY_ID + " = " + id;
        Cursor c = myDB.rawQuery(selectQuery, null);
        System.out.println(selectQuery);
        if (c != null && c.moveToFirst()) {
            c.moveToFirst();
            int story_id = c.getInt(c.getColumnIndexOrThrow(KEY_ID));
            String story_title = c.getString(c.getColumnIndexOrThrow(KEY_TITLE));
            story = new Story(story_title, story_id);
            story.setCharacters(getStoryCharacters(id));
            story.setEvents(getStoryEvents(id));
        }

        return story;
    }

    public ArrayList<Character> getStoryCharacters(String id){
        ArrayList<Character> characters = new ArrayList<>();
        int char_id;
        String name;
        Character character;
        String stat1;
        String stat2;
        String stat3;
        String stat4;
        String stat5;

        SQLiteDatabase myDB = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_CHARACTERS + " WHERE "
                + KEY_STORY_ID + " = " + id;
        Cursor c = myDB.rawQuery(selectQuery, null);

        if(c.moveToFirst()){
         do{
             name = c.getString(c.getColumnIndexOrThrow(KEY_NAME));
             char_id = c.getInt(c.getColumnIndexOrThrow(KEY_ID));
             stat1 = c.getString(c.getColumnIndexOrThrow(KEY_STAT1));
             stat2 = c.getString(c.getColumnIndexOrThrow(KEY_STAT2));
             stat3 = c.getString(c.getColumnIndexOrThrow(KEY_STAT3));
             stat4 = c.getString(c.getColumnIndexOrThrow(KEY_STAT4));
             stat5 = c.getString(c.getColumnIndexOrThrow(KEY_STAT5));
             character = new Character(name, Integer.parseInt(id), char_id);
             character.setStats(stat1, stat2, stat3, stat4, stat5);
             characters.add(character);
         }while (c.moveToNext());
        }

        return characters;
    }

    public ArrayList<Event> getStoryEvents(String id){
        ArrayList<Event> events = new ArrayList<>();
        int event_id;
        int event_posn;
        String name;
        Event event;

        SQLiteDatabase myDB = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_EVENTS + " WHERE "
                + KEY_STORY_ID + " = " + id;
        Cursor c = myDB.rawQuery(selectQuery, null);

        if(c.moveToFirst()){
            do{
                name = c.getString(c.getColumnIndexOrThrow(KEY_NAME));
                event_id = c.getInt(c.getColumnIndexOrThrow(KEY_ID));
                event_posn = c.getInt(c.getColumnIndexOrThrow(KEY_EVENT_POSITION));
                event = new Event(name, event_id, Integer.parseInt(id), event_posn);
                events.add(event);
                System.out.println("In getStoryEvents: ______________________" + name + " " + event_id + " " + id);
            }while (c.moveToNext());
        }

        return events;
    }

    public void addCharacter(String name, String stat1, String stat2, String stat3, String stat4,
                             String stat5, int stat1val, int stat2val, int stat3val, int stat4val,
                             int stat5val, String storyId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        cv.put(KEY_STAT1, stat1);
        cv.put(KEY_STAT2, stat2);
        cv.put(KEY_STAT3, stat3);
        cv.put(KEY_STAT4, stat4);
        cv.put(KEY_STAT5, stat5);
        cv.put(KEY_STORY_ID, storyId);

        long result = db.insert(TABLE_CHARACTERS, null, cv);
        if(result == -1)
        {
            Toast.makeText(context, "Failed to add character.", Toast.LENGTH_SHORT);
        }
        Story story = getStory(storyId);
        ArrayList<Character> characters = getStoryCharacters(storyId);
        int eventID = 0;
        int characterID = characters.get(characters.size() - 1).getId();
        if(getStoryEvents(storyId).size() > 0) {
            eventID = getStoryEvents(storyId).get(0).getId();
        }

        addStatRecord(Integer.toString(characterID), Integer.toString(eventID), stat1val, stat2val,
                stat3val, stat4val, stat5val);
    }

    public Character getCharacter(String charID){
        Character character= new Character();
        SQLiteDatabase myDB = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_CHARACTERS + " WHERE "
                + KEY_ID + " = " + charID;
        Cursor c = myDB.rawQuery(selectQuery, null);

        if (c != null && c.moveToFirst()) {
            c.moveToFirst();
            int character_id = c.getInt(c.getColumnIndexOrThrow(KEY_ID));
            String character_name = c.getString(c.getColumnIndexOrThrow(KEY_NAME));
            int story_id = c.getInt((c.getColumnIndexOrThrow(KEY_STORY_ID)));
            String stat1 = c.getString(c.getColumnIndexOrThrow(KEY_STAT1));
            String stat2 = c.getString(c.getColumnIndexOrThrow(KEY_STAT2));
            String stat3 = c.getString(c.getColumnIndexOrThrow(KEY_STAT3));
            String stat4 = c.getString(c.getColumnIndexOrThrow(KEY_STAT4));
            String stat5 = c.getString(c.getColumnIndexOrThrow(KEY_STAT5));
            character = new Character(character_name, story_id, character_id);
            character.setStats(stat1, stat2, stat3, stat4, stat5);
        }
        c.close();
        return character;
    }

    public void addStatRecord(String charID, String eventID, int stat1val, int stat2val,
                              int stat3val, int stat4val, int stat5val){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_CHAR_ID, charID);
        cv.put(KEY_EVENT_ID, eventID);
        cv.put(KEY_STAT1_VAL, stat1val);
        cv.put(KEY_STAT2_VAL, stat2val);
        cv.put(KEY_STAT3_VAL, stat3val);
        cv.put(KEY_STAT4_VAL, stat4val);
        cv.put(KEY_STAT5_VAL, stat5val);

        long result = db.insert(TABLE_LINKED_EVENTS, null, cv);
        if(result == -1)
        {
            Toast.makeText(context, "Failed to add story.", Toast.LENGTH_SHORT).show();
        }
    }

    public void addEvent(String name, int storyID){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        int posn = 0;
        if(! getStoryEvents(Integer.toString(storyID)).isEmpty()) {
            posn = getMostRecentStoryEvent(Integer.toString(storyID)).getPosition() + 1;
            System.out.println("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY" + posn);
            System.out.println(name);
        }
        else{
            name = "Beginning";
        }
        cv.put(KEY_EVENT_POSITION, posn);
        cv.put(KEY_NAME, name);
        cv.put(KEY_STORY_ID, storyID);

        long result = db.insert(TABLE_EVENTS, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed to add character", Toast.LENGTH_SHORT).show();
        }

    }

    public Event getEvent(String eventID){
        Event event = new Event();
        SQLiteDatabase myDB = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_EVENTS + " WHERE "
                + KEY_ID + " = " + eventID;
        Cursor c = myDB.rawQuery(selectQuery, null);

        if (c != null && c.moveToFirst()) {
            c.moveToFirst();
            int event_id = c.getInt(c.getColumnIndexOrThrow(KEY_ID));
            String event_name = c.getString(c.getColumnIndexOrThrow(KEY_NAME));
            int story_id = c.getInt((c.getColumnIndexOrThrow(KEY_STORY_ID)));
            int event_posn = c.getInt(c.getColumnIndexOrThrow(KEY_EVENT_POSITION));
            event = new Event(event_name, event_id, story_id, event_posn);
        }
        c.close();
        return event;
    }

    public Event getMostRecentStoryEvent(String storyID){
        ArrayList<Event> events = getStoryEvents(storyID);
        Event mostRecentEvent = new Event();

        if(! events.isEmpty()) {
            mostRecentEvent = events.get(0);
            for (int i = 1; i < events.size(); i++) {
                if (events.get(i).getPosition() > mostRecentEvent.getPosition())
                    mostRecentEvent = events.get(i);
            }
        }
        return mostRecentEvent;
    }

    public ArrayList<StatRecord> getCharacterStatRecords(String charId){
        ArrayList<StatRecord> records = new ArrayList<>();
        int event_id;
        int[] stats = new int[5];

        SQLiteDatabase myDB = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_LINKED_EVENTS + " WHERE "
                + KEY_CHAR_ID + " = " + charId;
        Cursor c = myDB.rawQuery(selectQuery, null);

        if(c.moveToFirst()){
            do{
                event_id = c.getInt(c.getColumnIndexOrThrow(KEY_STORY_ID));
                stats[0] = c.getInt(c.getColumnIndexOrThrow(KEY_STAT1_VAL));
                stats[1] = c.getInt(c.getColumnIndexOrThrow(KEY_STAT2_VAL));
                stats[2] = c.getInt(c.getColumnIndexOrThrow(KEY_STAT3_VAL));
                stats[3] = c.getInt(c.getColumnIndexOrThrow(KEY_STAT4_VAL));
                stats[4] = c.getInt(c.getColumnIndexOrThrow(KEY_STAT5_VAL));
                StatRecord record = new StatRecord(Integer.parseInt(charId), event_id, stats[0],
                        stats[1], stats[2], stats[3], stats[4]);
                records.add(record);
                System.out.println("In getStatRecords: ______________________" + charId + " "
                        + event_id + " " + stats[0]);
            }while (c.moveToNext());
        }

        return records;
    }

    public ArrayList<Event> getCharacterEvents(String charID){
        ArrayList<Event> events = new ArrayList<>();
        ArrayList<StatRecord> records = getCharacterStatRecords(charID);
        for(int i = 0; i < records.size(); i++){
            int eventid = records.get(i).getEventID();
            events.add(getEvent(Integer.toString(eventid)));
        }
        return events;
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_CHARACTERS, null, null);
        db.delete(TABLE_STORIES, null, null);
        db.delete(TABLE_EVENTS, null, null);
        db.delete(TABLE_LINKED_EVENTS, null, null);
    }

    public Event getMostRecentCharacterEvent(String charID) {
        ArrayList<StatRecord> records = getCharacterStatRecords(charID);

        System.out.println("---------------Inside getMostRecentCharacterEvent: " +
                records.size() + " " + records.get(0).toString());
        int mostRecentEventID = 0;
        for(int i = 0; i < records.size(); i++){
            if(records.get(i).getEventID() > mostRecentEventID)
                mostRecentEventID = records.get(i).getEventID();
        }
        Event event = getEvent(Integer.toString(mostRecentEventID));
        return event;
    }

    public StatRecord getStatRecord(String charID, String eventID)
    {
        StatRecord record = new StatRecord();
        SQLiteDatabase myDB = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_LINKED_EVENTS + " WHERE "
                + KEY_STORY_ID + " = " + eventID + " AND " + KEY_CHAR_ID + " = " + charID;
        Cursor c = myDB.rawQuery(selectQuery, null);

        if (c != null && c.moveToFirst()) {
            c.moveToFirst();
            int char_id = Integer.parseInt(charID);
            int event_id = Integer.parseInt(eventID);
            int stat1 = c.getInt(c.getColumnIndexOrThrow(KEY_STAT1_VAL));
            int stat2 = c.getInt(c.getColumnIndexOrThrow(KEY_STAT2_VAL));
            int stat3 = c.getInt(c.getColumnIndexOrThrow(KEY_STAT3_VAL));
            int stat4 = c.getInt(c.getColumnIndexOrThrow(KEY_STAT4_VAL));
            int stat5 = c.getInt(c.getColumnIndexOrThrow(KEY_STAT5_VAL));
            record = new StatRecord(char_id, event_id, stat1, stat2, stat3, stat4, stat5);
        }
        return record;
    }

}