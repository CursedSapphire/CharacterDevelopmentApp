package com.example.characterdevelopmenttracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

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
                        KEY_LINKED_EVENTS_ID + " INTEGER, " + KEY_STAT1 + " TEXT, " +
                        KEY_STAT2 + " TEXT, " + KEY_STAT3 + " TEXT, " +KEY_STAT4 + " TEXT, "
                        + KEY_STAT5 + " TEXT )";

        String eventQuery = "CREATE TABLE " + TABLE_EVENTS + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                KEY_NAME + " TEXT, " + KEY_STORY_ID + " INTEGER, " +
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
            Toast.makeText(context, "Failed to add story.", Toast.LENGTH_SHORT);
        }

    }

    public void removeStory(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STORIES, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
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
}
